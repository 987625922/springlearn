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

  

