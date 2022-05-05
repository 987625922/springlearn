# RabbitMQ如何防止消息丢失和重复消费

1. 生产者没有成功把消息发送到MQ
2. RabbitMQ接收到消息之后丢失了消息
3. 消费者弄丢了消息

##### 一. 生产者没有成功把消息发送到MQ

- 丢失的原因：因为网络传输的不稳定性，当生产者在向MQ发送消息的过程中，MQ没有成功接收到消息，但是生产者却以为MQ成功接收到了消息，不会再次重复发送该消息，从而导致消息的丢失。
- 解决办法： 有两个解决办法：事务机制和confirm机制，最常用的是confirm机制。

事务机制：RabbitMQ 提供了事务功能，生产者发送数据之前开启 RabbitMQ 事务`channel.txSelect`，然后发送消息，如果消息没有成功被 RabbitMQ 接收到，那么生产者会收到异常报错，此时就可以回滚事务`channel.txRollback`，然后重试发送消息；如果收到了消息，那么可以提交事务`channel.txCommit`。

```
ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.79.141");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");

        Connection connection=null;
        Channel channel=null;
        try {
            connection=factory.newConnection();
            channel=connection.createChannel();
            channel.queueDeclare("transactionQueue",true,
                    false,false,null);
            /**创建交换机
             * */
            channel.exchangeDeclare("directTransactionExchange",
                    "direct",true);
            /**将交换机和队列进行绑定
             * */
            channel.queueBind("transactionQueue",
                    "directTransactionExchange","transactionRoutingKey");
            /**发送消息
             * */
            String message="事务测试消息";
            /**启动事务，启动事务以后所有写入队列
             * 中的消息，必须显示调用事务的txCommit()函数
             * 来提交事务获取txRollback()回滚事务
             * */
            /**开启事务
             * */
            channel.txSelect();
            /**发送消息到队列
             * */
            channel.basicPublish("directTransactionExchange",
                    "transactionRoutingKey",null,
                    message.getBytes("utf-8"));
            /**提交事务
             * */
            channel.txCommit();
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally{
            if(channel!=null){
                try {
                    /**回滚事务
                     * */
                    channel.txRollback();
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
```

confirm机制：RabbitMQ可以开启 `confirm` 模式，在生产者那里设置开启 `confirm` 模式之后，生产者每次写的消息都会分配一个唯一的 id，如果消息成功写入 RabbitMQ 中，RabbitMQ 会给生产者回传一个 `ack` 消息，告诉你说这个消息 ok 了。如果 RabbitMQ 没能处理这个消息，会回调你的一个 `nack` 接口，告诉你这个消息接收失败，生产者可以发送。而且你可以结合这个机制自己在内存里维护每个消息 id 的状态，如果超过一定时间还没接收到这个消息的回调，那么可以重发。

生产者：

```
public static void main(String[] args) throws IOException, TimeoutException
{
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setVirtualHost("/");
    connectionFactory.setHost("47.104.128.12");
    connectionFactory.setPort(5672);
    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();
    // 开启confirm 确认机制 
    channel.confirmSelect();
    //设置confirm 监听 
    channel.addConfirmListener(new AngleConfirmListerner());
    //生产消息 
    channel.basicPublish("test.confirm.exchange", "test.confirm.key", null, "测试confirm消息".getBytes());
}
```

消费者：

```
public static void main(String[] args) throws IOException, TimeoutException, InterruptedException
{
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setVirtualHost("/");
    connectionFactory.setHost("47.104.128.12");
    connectionFactory.setPort(5672);
    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();
    //声明交换机队列以及绑定关系 
    channel.exchangeDeclare("test.confirm.exchange", "topic", true, true, false, null);
    channel.queueDeclare("test.confirm.queue", true, false, true, null);
    channel.queueBind("test.confirm.queue", "test.confirm.exchange", "test.confirm.key");
    QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
    channel.basicConsume("test.confirm.queue", true, queueingConsumer);
    while(true)
    {
        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        System.out.println(new String(delivery.getBody()));
    }
}
```

confirm消息监听器代码

```
public class AngleConfirmListerner implements ConfirmListener
{
  	@Override 
  	public void handleAck(long deliveryTag, boolean multiple) throws IOException
    {
        System.out.println("消息deliveryTag" + deliveryTag + "被正常签收");
    }
    @Override 
    public void handleNack(long deliveryTag, boolean multiple) throws IOException
    {
        System.out.println("消息deliveryTag" + deliveryTag + "没被签收");
    }
}
```

***RabbitMQ的事务机制是同步的，很耗型能，会降低RabbitMQ的吞吐量***。***confirm机制是异步的，生成者发送完一个消息之后，不需要等待RabbitMQ的回调，就可以发送下一个消息，当RabbitMQ成功接收到消息之后会自动异步的回调生产者的一个接口返回成功与否的消息。***



##### 二.RabbitMQ接收到消息之后丢失了消息

- 丢失的原因：RabbitMQ接收到生产者发送过来的消息，是存在内存中的，如果没有被消费完，此时RabbitMQ宕机了，那么再次启动的时候，原来内存中的那些消息都丢失了。
- 解决办法：开启RabbitMQ的持久化。当生产者把消息成功写入RabbitMQ之后，RabbitMQ就把消息持久化到磁盘。结合上面的说到的confirm机制，只有当消息成功持久化磁盘之后，才会回调生产者的接口返回ack消息，否则都算失败，生产者会重新发送。存入磁盘的消息不会丢失，就算RabbitMQ挂掉了，重启之后，他会读取磁盘中的消息，不会导致消息的丢失。

持久化的配置：

- 第一点是创建 queue 的时候将其设置为持久化，这样就可以保证 RabbitMQ 持久化 queue 的元数据，但是它是不会持久化 queue 里的数据的。
- 第二个是发送消息的时候将消息的 `deliveryMode` 设置为 2，就是将消息设置为持久化的，此时 RabbitMQ 就会将消息持久化到磁盘上去。

注意：持久化要起作用必须同时设置这两个持久化才行，RabbitMQ 哪怕是挂了，再次重启，也会从磁盘上重启恢复 queue，恢复这个 queue 里的数据。



##### 三.消费者弄丢了消息

- 丢失的原因：如果RabbitMQ成功的把消息发送给了消费者，那么RabbitMQ的ack机制会自动的返回成功，表明发送消息成功，下次就不会发送这个消息。但如果就在此时，消费者还没处理完该消息，然后宕机了，那么这个消息就丢失了。
- 解决的办法：简单来说，就是必须关闭 RabbitMQ 的自动 `ack`，可以通过一个 api 来调用就行，然后每次在自己代码里确保处理完的时候，再在程序里 `ack` 一把。这样的话，如果你还没处理完，不就没有 `ack`了？那 RabbitMQ 就认为你还没处理完，这个时候 RabbitMQ 会把这个消费分配给别的 consumer 去处理，消息是不会丢的。