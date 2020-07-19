## Transactional 

- ### 注解的属性信息

  | 属性             | 说明                                                         |
  | ---------------- | ------------------------------------------------------------ |
  | name             | 当在配置文件中有多个 TransactionManager , 可以用该属性指定选择哪个事务管理器。 |
  | propagation      | 事务的传播行为，默认值为 REQUIRED。                          |
  | isolation        | 事务的隔离度，默认值采用 DEFAULT。                           |
  | timeout          | 事务的超时时间，默认值为-1。如果超过该时间限制但事务还没有完成，则自动回滚事务。 |
  | read-only        | 指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。 |
  | rollback-for     | 用于指定能够触发事务回滚的异常类型，如果有多个异常类型需要指定，各类型之间可以通过逗号分隔。 |
  | no-rollback- for | 抛出 no-rollback-for 指定的异常类型，不回滚事务。            |

- ### @Transactional 的 rollbackFor 属性

  如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring 将回滚事务；除此之外，Spring 不会回滚事务。

- ### spring 传播级别

  - **REQUIRED：**支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
  - **SUPPORTS**：支持当前事务，如果当前没有事务，就以非事务方式执行。 
  - **MANDATORY**：支持当前事务，如果当前没有事务，就抛出异常。 
  - **REQUIRES_NEW**：新建事务，如果当前存在事务，把当前事务挂起。 
  - **NOT_SUPPORTED**：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
  - **NEVER**：以非事务方式执行，如果当前存在事务，则抛出异常。
  - **NESTED**：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务。 

- ### spring 隔离级别

  - DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.
  - 未提交读（read uncommited） :脏读，不可重复读，虚读都有可能发生
  - 已提交读 （read commited）:避免脏读。但是不可重复读和虚读有可能发生
  - 可重复读 （repeatable read） :避免脏读和不可重复读.但是虚读有可能发生.
  - 串行化的 （serializable） :避免以上所有读问题.

- ### Spring 的 AOP 的自调用问题

  在 Spring 的 AOP 代理下，只有目标方法由外部调用，目标方法才由 Spring 生成的代理对象来管理，这会造成自调用问题。若同一类中的其他没有@Transactional 注解的方法内部调用有@Transactional 注解的方法，有@Transactional 注解的方法的事务被忽略，不会发生回滚。

  ```
  @Service
  -->public class OrderService {
      private void insert() {
  insertOrder();
  }
  @Transactional
      public void insertOrder() {
          //insert log info
          //insertOrder
          //updateAccount
         }
  }
  ```

  insertOrder 尽管有@Transactional 注解，但它被内部方法 insert 调用，事务被忽略，出现异常事务不会发生回滚。

  上面的两个问题@Transactional 注解只应用到 public 方法和自调用问题，是由于使用 Spring AOP 代理造成的。为解决这两个问题，使用 AspectJ 取代 Spring AOP 代理。

  需要将下面的 AspectJ 信息添加到 xml 配置信息中。

  ```
  <tx:annotation-driven mode="aspectj" />
  <bean id="transactionManager"
  class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource" />
  </bean>
  </bean
  class="org.springframework.transaction.aspectj.AnnotationTransactionAspect"
  factory-method="aspectOf">
  <property name="transactionManager" ref="transactionManager" />
  </bean>
  ```

  同时在 Maven 的 pom 文件中加入 spring-aspects 和 aspectjrt 的 dependency 以及 aspectj-maven-plugin。

  ```
  <dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aspects</artifactId>
  <version>4.3.2.RELEASE</version>
  </dependency>
  <dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
  <version>1.8.9</version>
  </dependency>
  <plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>aspectj-maven-plugin</artifactId>
  <version>1.9</version>
  <configuration>
  <showWeaveInfo>true</showWeaveInfo>
  <aspectLibraries>
  <aspectLibrary>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aspects</artifactId>
  </aspectLibrary>
  </aspectLibraries>
  </configuration>
  <executions>
  <execution>
  <goals>
  <goal>compile</goal>
  <goal>test-compile</goal>
  </goals>
  </execution>
  </executions>
  </plugin>
  ```

  