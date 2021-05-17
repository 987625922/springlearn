## Spring Data Jpa

- 创建实体类，编写实体类和数据库表的关系映射(JPA)
- 编写dao层接口

#### - 创建实体类，编写实体类和数据库表的关系映射(JPA)

JPA笔记

#### - 编写dao层接口

需要继承两个接口

- JpaRepository：封装了增删改查分页排序等基本操作，具体可以看JpaRepository的父类

- JpaSpecificationExecutor：封装了标准查询

  ```
  /**会通过动态代理自动生成相应方法*/
  public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {}
  ```
#### - 操作数据库
- save()方法：传入的实体对象有主键则更新，没有主键则插入
- delete()系列方法
- 查询方法
  - count()：统计
  - exists()：数据库中是否存在
  - find系列方法：立即加载
  - getOne：延迟加载
#### - 语句操作
除了调用spring data jpa内置的api，我们也可以在dao接口中定义我们自己的方法，通过@Query声明jpql或sql语句。
    - @Query
      - value：数据库操作语句
      - nativeQuery：是否是原生查询，默认false，即默认使用jpql查询
    - @Modifying：声明当前是一个更新操作，需要修改数据库数据。
      - 只能用于void或int/Integer的返回类型
#### - 约定规则查询
spring data jpa制定了一些约定，如果按照这些约定来定义方法名，则会自动解析出sql语句。
> findBy + 属性名 + 查询方式 + （And|Or） + 属性名 + 查询方式
#### - 标准查询（Specification）
springdata jpa的dao层一般继承2个接口JpaRepository和JpaSpecificationExecutor。JpaRepository封装了crud、统计、排序、分页的常见操作，而JpaSpecificationExecutor基于JPA的criteria查询封装了另一种查询方式，即标准查询
