## java
##### 1.java线程池
##### 2.java的io
##### 3.java的注解

### 1.线程池
相比new Thread，Java提供的四种线程池的好处在于：
a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。 
b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。 
c. 提供定时执行、定期执行、单线程、并发数控制等功能。

Java通过Executors提供四种线程池，分别为：
- newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
- newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
- newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
- newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

(1)newCachedThreadPool：
创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
```
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
        final int index = i;
    try {
        Thread.sleep(index * 1000);
    } 
        catch (InterruptedException e) {
            e.printStackTrace();
    }

cachedThreadPool.execute(new Runnable() {

@Override
public void run() {
    System.out.println(index);
}
});
}
```

(2)newFixedThreadPool：
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
```
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 10; i++) {
    final int index = i;

    fixedThreadPool.execute(new Runnable() {

@Override
public void run() {
try {
    System.out.println(index);
    Thread.sleep(2000);
} catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
}
});
}
```

(3)newScheduledThreadPool：
创建一个定长线程池，支持定时及周期性任务执行。ScheduledExecutorService比Timer更安全，功能更强大。
```
//表示延迟3秒执行。
ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
 scheduledThreadPool.schedule(new Runnable() {

@Override
public void run() {
    System.out.println("delay 3 seconds");
}
}, 3, TimeUnit.SECONDS);
```
(4)newSingleThreadExecutor：
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
```
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
for (int i = 0; i < 10; i++) {
final int index = i;
singleThreadExecutor.execute(new Runnable() {

@Override
public void run() {
    try {
        System.out.println(index);
    Thread.sleep(2000);
} catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
        }
}
    });
}
```
### 2.java的io
##### io
##### InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
##### OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。
##### nio
##### 读数据和写数据方式:
##### 从通道进行数据读取 ：创建一个缓冲区，然后请求通道读取数据。
##### 从通道进行数据写入 ：创建一个缓冲区，填充数据，并要求通道写入数据。
##### NIO核心组件:
Channels
Buffers
Selectors

IO 类可以分为：
- 1、文件（file）：FileInputStream、FileOutputStream、FileReader、FileWriter
- 2、数组（[]）：
 2.1、字节数组（byte[]）：ByteArrayInputStream、ByteArrayOutputStream
 2.2、字符数组（char[]）：CharArrayReader、CharArrayWriter
- 3、管道操作：PipedInputStream、PipedOutputStream、PipedReader、PipedWriter
- 4、基本数据类型：DataInputStream、DataOutputStream
- 5、缓冲操作：BufferedInputStream、BufferedOutputStream、BufferedReader、BufferedWriter
- 6、打印：PrintStream、PrintWriter
- 7、对象序列化反序列化：ObjectInputStream、ObjectOutputStream
- 8、转换：InputStreamReader、OutputStreWriter


##### FileInputStream 和 FileOutputStream 
```
 byte bWrite[] = { 11, 21, 3, 40, 5 };
            OutputStream os = new FileOutputStream("test.txt");
            for (int x = 0; x < bWrite.length; x++) {
                os.write(bWrite[x]); // writes the bytes
            }
 os.close();
 
 InputStream is = new FileInputStream("test.txt");
             int size = is.available(); 
             for (int i = 0; i < size; i++) {
                 System.out.print((char) is.read() + "  ");
             }
 is.close();

```

##### FileWriter 和 FileReader
```
 FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath()+"/io/test.txt", false); // 关闭追加模式，变为覆盖模式
        fileWriter.write("Hello，world！欢迎来到 java 世界\n");
        fileWriter.write("我来覆盖文件原本的内容");
        fileWriter.append("我是下一行");
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
        
        
          FileReader fileReader = new FileReader(new File("").getAbsolutePath()+"/io/test.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    System.out.println(str);
                }
                fileReader.close();
                bufferedReader.close();

```

##### OutputStreamWriter 和 InputStreamReader 
```
FileOutputStream fileOutputStream = new FileOutputStream(new File("").getAbsolutePath()+"/io/test2.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK"); // 使用 GBK 编码文件
        outputStreamWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        outputStreamWriter.append("另外一行内容");
        outputStreamWriter.flush();
        System.out.println("文件的编码为" + outputStreamWriter.getEncoding());
        outputStreamWriter.close();
        fileOutputStream.close();

 FileInputStream fileInputStream = new FileInputStream(new File("").getAbsolutePath()+"/io/test2.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK"); // 使用 GBK 解码文件
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();

```
### 3.java注解
##### 一.jdk注解
##### 1.@Override 表示继承方法的注解
##### 2.@Deprecated 过时方法的注解
##### 3.@Suppvisewarnings 忽略某个警告的注解 例@Suppvisewarnings("deprecation")忽略过时警告
##### 二.自定义注解
```
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Annotation {
    String desc();

    String author();

    int age() default 18;
}
```
##### @Target说明了Annotation所修饰的对象范围
##### · ElementType.CONSTRUCTOR 构造方法
##### · ElementType.FIELD 字段声明
##### · ElementType.LOCAL_VARIABLE 局部变量声明
##### · ElementType.METHOD 方法声明
##### · ElementType.PACKAGE 包声明
##### · ElementType.PARAMETER 参数声明
##### · ElementType.TYPE 类，接口
###
##### 　@Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
##### · SOURCE:在源文件中有效（即源文件保留）
##### · CLASS:在class文件中有效（即class保留）
##### · RUNTIME:在运行时有效（即运行时保留）
###
##### @Inherited 允许子类继承
###
##### @Documented 生成javadoc时包含注解