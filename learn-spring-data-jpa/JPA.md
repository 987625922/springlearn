## JPA

- 实体类和数据库表关系映射注解

#### - 实体类和数据库表关系映射注解

- 类与表的映射
  - @Entity：声明实体类
  - @Table：配置实体类与表的映射关系
    - name：配置数据库表的名称

- 属性与表字段的映射关系

  - @Id：声明主键的配置

  - @GeneratedValue：配置主键的生成策略

    - GenerationType.IDENTITY：自增，使用底层数据库支持的自动增长方式对id自增（比如mysql）
    - GenerationType.SEQUENCE：序列，（比如oracle）
    - GenerationType.TABLE：jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
    - GenerationType.AUTO：由程序自动的帮助我们选择主键生成策略

  - @Column：配置属性与字段的映射关系

    - name：数据库中表字段的名字
- 连表
  - 一对多
    -  @OneToMany：声明一对多
      - targetEntity：目标实体（从表实体）
      - mappedBy：参照映射
    - @ManyToOne：声明多对一
      - targetEntity：目标实体（从表实体）
    - @JoinColumn：配置外键 
      - name：从表上的外键字段名称
      - referencedColumnName：参照的主表主键字段名称
```
  @Entity
  @Table
  public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer studentId;
    @Column(name = "school_id",insertable = false,updatable = false)//外键字段
    private Integer schoolId;
    @ManyToOne(targetEntity = School.class)//配置多对一关系
    @JoinColumn(name = "school_id",referencedColumnName = "schoolId")//配置外键
    private School school;
    ...
  }
  ==================================
  @Entity
  @Table
  public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schoolId;
  //    @OneToMany(targetEntity = Student.class)//声明关系，一对多
  //    @JoinColumn(name ="school_id",referencedColumnName="schoolId")//配置外键
    @OneToMany(mappedBy = "school")//Student里面已经配置了映射关系，表示参照Student里面的school
    private List<Student> students = new ArrayList<>();
    ...
}
```
  - 级联操作
    - 操作一个对象的同时操作它的关联对象
    -  cascade来配置级联关系：
      - CascadeType.ALL：级联所有操作
      - CascadeType.PERSIST：级联保存操作
      - CascadeType.MERGE：级联更新操作
      - CascadeType.REMOVE：级联删除操作
      - CascadeType.REFRESH：级联refresh操作
      - CascadeType.DETACH：级联detach操作
    ```
      @ManyToOne(targetEntity = School.class,cascade = CascadeType.ALL)
      @JoinColumn(name = "school_id",referencedColumnName = "schoolId")
    ```
  - 多对多
    - @ManyToMany：声明多对多
      - targetEntity：目标实体
    - @JoinTable：添加中间表
      - name：中间表名
      - joinColumns：添加一个@JoinColumn数组，表示当前对象在中间表的外键
      - nverseJoinColumns：添加一个@JoinColumn数组，表示对方对象在中间表的外键 
  ```
   @Entity
   @Table
   public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer developerId;
    @ManyToMany(targetEntity = Language.class)
    @JoinTable(name = "middle_develop_language",
            joinColumns = @JoinColumn(name = "develop_id", referencedColumnName = "developerId"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "languageId"))
    private List<Language> languages = new ArrayList<>();
    ...
 }
    ====================
   @Entity
   @Table
   public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer languageId;
    @ManyToMany(mappedBy = "languages")//放弃维护权，参照对方的映射关系
    private List<Developer> developers = new ArrayList<>();
    private List<Developer> developers = new ArrayList<>();
    ...
    }
  ```
- 对象导航查询
  - 对象导航查询并不是一种新的查询方式，而是在多表关系中，通过查询某个对象，可以直接通过get得到相关联的数据信息，这是jpa的一种特性
  - FetchType.EAGER：立即加载
  - FetchType.LAZY：懒加载