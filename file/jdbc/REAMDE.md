## JDBC
1. 建立连接
   ```
      Connection c = DriverManager.getConnection(
        "jdbc:mysql://127.0.0.1:3306/how2java?
        characterEncoding=UTF-8","root", "admin");
   ```
2. 创建Statement
    Statement是用于执行SQL语句的，比如增加，删除
    ```
       // java.sql.Statement
      Statement s = c.createStatement();
      
      //增删改 都是使用execute
      s.execute("sql语句")
      
      // 执行查询语句，并把结果集返回给ResultSet
      ResultSet rs = s.executeQuery("sql语句");
      while (rs.next()) {
       int id = rs.getInt("id");// 可以使用字段名
       String name = rs.getString(2);// 也可以使用字段的顺序
       float hp = rs.getFloat("hp");
       int damage = rs.getInt(4);
       System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
      }
       // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
       // rs.close();
    ```
3. PreparedStatement 预编译Statement
    ```
      PreparedStatement ps = c.prepareStatement(sql);
      // 设置参数
      ps.setString(1, "提莫");
      ps.setFloat(2, 313.0f);
      ps.setInt(3, 50);
      // 执行
      ps.execute();
    ```
4. execute与executeUpdate的区别
    - 相同点：都可以执行增加，删除，修改
    - 不同1：
      execute可以执行查询语句
      然后通过getResultSet，把结果集取出来
      executeUpdate不能执行查询语句
    - 不同2:
      execute返回boolean类型，true表示执行的是查询语句，false表示执行的是insert,delete,update等等
      executeUpdate返回的是int，表示有多少条数据受到了影响
5. 获取自增长id
    ```
     PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
     // 执行插入语句
     ps.execute();
     // 在执行完插入语句后，MySQL会为新插入的数据分配一个自增长id
     // JDBC通过getGeneratedKeys获取该id
     ResultSet rs = ps.getGeneratedKeys();
     if (rs.next()) {
      int id = rs.getInt(1);
      System.out.println(id);
      }
    ```
6. 事务
    ##### 在事务中的多个操作，要么都成功，要么都失败
    ##### 通过 c.setAutoCommit(false);关闭自动提交,通过commit手动提交
    ##### 使用 c.commit();进行手动提交
    ```
     
     c.setAutoCommit(false);
     String sql1 = "update hero set hp = hp +1 where id = 22";
     s.execute(sql1);
     String sql2 = "updata hero set hp = hp -1 where id = 22";
     s.execute(sql2);
     // 手动提交
     c.commit(); 
    ```
7. Connection数据库连接池简单设计
   
    
