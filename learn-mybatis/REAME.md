## MyBatis

- ### spring-mybatis配置

  在spring的xml中配置mybatis的SqlSessionFactoryBean

  ```
  <!-- 1.配置SqlSessionFactory对象（把连接池对象导入到mybatis中） -->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!-- 注入数据库连接池 -->
          <property name="dataSource" ref="dataSource"/>
          <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
          <property name="configLocation" value="classpath:mybatis-config.xml"/>
          <!-- 扫描entity包 使用别名 -->
          <property name="typeAliasesPackage" value="org.learn.common.bean"/>
          <!-- 扫描sql配置文件:mapper需要的xml文件 -->
          <property name="mapperLocations" value="classpath:mapper/*.xml"/>
      </bean>
  
      <!-- 2.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
      <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
          <!-- 注入sqlSessionFactory -->
          <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
          <!-- 给出需要扫描Dao接口包 -->
          <property name="basePackage" value="org.learn.mybatis.dao"/>
      </bean>
  ```

  在resources下创建mybatis的config文件，mybatis-config.xml

  ```
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!-- 配置全局属性 -->
      <settings>
          <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->
          <setting name="useGeneratedKeys" value="true"/>
  
          <!-- 使用列别名替换列名 默认:true -->
          <setting name="useColumnLabel" value="true"/>
  
          <!-- 开启驼峰命名转换:Table{create_time} -> Entity{createTime} -->
          <setting name="mapUnderscoreToCamelCase" value="true"/>
      </settings>
      <!-- 类型别名(类型别名可为 Java 类型设置一个缩写名字。
      它仅用于 XML 配置，意在降低冗余的全限定类名书写),在mapper中使用 -->
      <typeAliases>
          <!-- 当这样配置时，Blog 可以用在任何使用 domain.blog.Blog 的地方。 -->
          <!--<typeAlias alias="Author" type="domain.blog.Author"/>-->
          <!--也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean-->
          <!--<package name="domain.blog"/>-->
          <!--每一个在包 domain.blog 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的		非限定类名来作为它的别名。
           比如 domain.blog.Author 的别名为 author；若有注解，则别名为其注解值。见下面的例子： -->
          <!-- @Alias("author")
              public class Author {
              } -->
      </typeAliases>
  </configuration>
  ```

- ### XML 映射文件

  - #### 空的mapper

    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="org.learn.mybatis.dao.BookDao">
    </mapper>
    ```

    - ##### namespace 为命名空间（即映射的dao的全类名）

  - #### select
  
    ```
        <select id="queryById" resultType="Book" parameterType="long">
            <!-- 具体的sql -->
            SELECT
            id,
            name,
            number
            FROM
            book
            WHERE
            id = #{id}
        </select>
    ```
  
    - ##### id 在命名空间中唯一的标识符，可以被用来引用这条语句。 
    
    - ##### parameterType  将会传入这条语句的参数的类全限定名或别名。这个属性是可选的，因为 MyBatis,可以通过类型处理器（TypeHandler）推断出具体传入语句的参数，默认值为未设置（unset）。
    
    - ##### resultType 期望从这条语句中返回结果的类全限定名或别名。 注意，如果返回的是集合，那应该设置为集合包含的类型，而不是集合本身的类型。 resultType和 resultMap 之间只能同时使用一个。
    
    - ##### flushCache 将其设置为 true 后，只要语句被调用，都会导致本地缓存和二级缓存被清空，默认值：false。
    
    - ##### useCache 将其设置为 true 后，将会导致本条语句的结果被二级缓存缓存起来，默认值：对 select 元素为 true。
    
    - ##### timeout 这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为未设置（unset）（依赖数据库驱动）。
    
    - ##### resultOrdered 这个设置仅针对嵌套结果 select 语句：如果为 true，将会假设包含了嵌套结果集或是分组，当返回一个主结果行时，就不会产生对前面结果集的引用。这就使得在获取嵌套结果集的时候不至于内存不够用。默认值：false。
    
  - #### select全部的配置
  
    ```
    <select
        id="selectPerson"
        parameterType="int"
        parameterMap="deprecated"
        resultType="hashmap"
        resultMap="personResultMap"
        flushCache="false"
        useCache="true"
        timeout="10"
        fetchSize="256"
        statementType="PREPARED"
        resultSetType="FORWARD_ONLY"></select>
    ```
  
  - #### insert,update和delete
  
    ```
    <insert
      		id="insertAuthor"
      		parameterType="domain.blog.Author"
      		flushCache="true"
      		statementType="PREPARED"
      		keyProperty=""
      		keyColumn=""
      		useGeneratedKeys=""
      		timeout="20">
    	<update
      		id="updateAuthor"
      		parameterType="domain.blog.Author"
      		flushCache="true"
      		statementType="PREPARED"
      		timeout="20">
    	<delete
      		id="deleteAuthor"
      		parameterType="domain.blog.Author"
      		flushCache="true"
      		statementType="PREPARED"
      		timeout="20">
    ```
  
    - ##### id 在命名空间中唯一的标识符，可以被用来引用这条语句。 
  
    - ##### useGeneratedKeys （仅适用于 insert 和 update）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系型数据库管理系统的自动递增字段），默认值：false。
    
  - #### 多行插入(同时插入多个实体)
  
    ```
        <insert id="insertAuthor" useGeneratedKeys="true"
        keyProperty="id">
        insert into Author (username, password, email, bio) values
        <foreach item="item" collection="list" separator=",">
        (#{item.username}, #{item.password}, #{item.email}, #{item.bio})
        </foreach>
        </insert>
    ```
    
  - #### 字符串替换 
  
    ```
     @Select("select * from user where ${column} = #{value}")
    	User findByColumn(@Param("column") String column, @Param("value") String value);
    ```
  
    ##### ${}里面的值会直接设置，不会经过其他处理
  
  - #### 结果映射 resultMap  
  
    - #### 如果数据库列名和属性名不能匹配上，可以在 SELECT 语句中设置列别名 
    
      ```
       	<select id="selectUsers" resultType="User">
      	select
      	user_id             as "id",
      	user_name           as "userName",
      	hashed_password     as "hashedPassword"
      	from some_table
      	where id = #{id}
      	</select>
      	 <!--或者 -->
      	<resultMap id="userResultMap" type="User">
      	<id property="id" column="user_id" />
      	<result property="username" column="user_name"/>
      	<result property="password" column="hashed_password"/>
      	</resultMap>
          <select id="selectUsers" resultMap="userResultMap">
          select user_id, user_name, hashed_password
          from some_table
          where id = #{id}
          </select>
      ```
    
    - #### 高级结果映射
    
      ```
      <!-- 非常复杂的语句 -->
      <select id="selectBlogDetails" resultMap="detailedBlogResultMap">
          select
          B.id as blog_id,
          B.title as blog_title,
          B.author_id as blog_author_id,
          A.id as author_id,
          A.username as author_username,
          A.password as author_password,
          A.email as author_email,
          A.bio as author_bio,
          A.favourite_section as author_favourite_section,
          P.id as post_id,
          P.blog_id as post_blog_id,
          P.author_id as post_author_id,
          P.created_on as post_created_on,
          P.section as post_section,
          P.subject as post_subject,
          P.draft as draft,
          P.body as post_body,
          C.id as comment_id,
          C.post_id as comment_post_id,
          C.name as comment_name,
          C.comment as comment_text,
          T.id as tag_id,
          T.name as tag_name
          from Blog B
          left outer join Author A on B.author_id = A.id
          left outer join Post P on B.id = P.blog_id
          left outer join Comment C on P.id = C.post_id
          left outer join Post_Tag PT on PT.post_id = P.id
          left outer join Tag T on PT.tag_id = T.id
          where B.id = #{id}
          </select>
          <!-- ============================== -->    
          <!-- 非常复杂的结果映射 -->
      <resultMap id="detailedBlogResultMap" type="Blog">
        <constructor>
          <idArg column="blog_id" javaType="int"/>
        </constructor>
        <result property="title" column="blog_title"/>
        <association property="author" javaType="Author">
          <id property="id" column="author_id"/>
          <result property="username" column="author_username"/>
          <result property="password" column="author_password"/>
          <result property="email" column="author_email"/>
          <result property="bio" column="author_bio"/>
          <result property="favouriteSection" column="author_favourite_section"/>
        </association>
        <collection property="posts" ofType="Post">
          <id property="id" column="post_id"/>
          <result property="subject" column="post_subject"/>
          <association property="author" javaType="Author"/>
          <collection property="comments" ofType="Comment">
            <id property="id" column="comment_id"/>
          </collection>
          <collection property="tags" ofType="Tag" >
            <id property="id" column="tag_id"/>
          </collection>
          <discriminator javaType="int" column="draft">
            <case value="1" resultType="DraftPost"/>
          </discriminator>
        </collection>
      </resultMap>
      ```
    
  - #### 关联
  
    - ##### 关联的嵌套 Select 查询（会出现N+1问题）
  
      ```
      <resultMap id="blogResult" type="Blog">
        <association property="author" column="author_id" javaType="Author" select="selectAuthor"/>
      </resultMap>
      
      <select id="selectBlog" resultMap="blogResult">
        SELECT * FROM BLOG WHERE ID = #{id}
      </select>
      
      <select id="selectAuthor" resultType="Author">
        SELECT * FROM AUTHOR WHERE ID = #{id}
      </select>
      ```
    
      ##### 一个用来加载博客（Blog），另外一个用来加载作者（Author），而且博客的结果映射描述了应该使用 `selectAuthor` 语句加载它的 author 属性。
    
    - ##### 关联的嵌套结果映射（解决N+1问题）
    
    - ##### 关联的多结果集（ResultSet）（3.2.3之后另一种解决N+1问题的方法）
    
  - #### 集合
  
  - #### 鉴别器
  
  - #### 自动映射和手动映射结合使用
  
    ##### *id* 和 *userName* 列将被自动映射，*hashed_password* 列将根据配置进行映射。
  
    ```
    <select id="selectUsers" resultMap="userResultMap">
      select
        user_id             as "id",
        user_name           as "userName",
        hashed_password
      from some_table
      where id = #{id}
    </select>
    
    <resultMap id="userResultMap" type="User">
      <result property="password" column="hashed_password"/>
    </resultMap>
    ```
  
- ### 缓存

  ##### 默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

  ```
  <cache/>
  ```

  ##### 这个简单语句的效果如下:

  - 映射语句文件中的所有 select 语句的结果将会被缓存。

  - 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。

  - 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。

  - 缓存不会定时进行刷新（也就是说，没有刷新间隔）。

  - 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。

  - 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。

    #### 修改缓存配置：

    ```
    <cache
      eviction="FIFO"
      flushInterval="60000"
      size="512"
      readOnly="true"/>
    ```

    ##### 创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突

    #### 清除策略：

    - LRU  最近最少使用：移除最长时间不被使用的对象。(默认)
    - FIFO 先进先出：按对象进入缓存的顺序来移除它们。
    - SOFT  软引用：基于垃圾回收器状态和软引用规则移除对象。
    - WEAK 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。

    #### flushInterval （刷新间隔）属性可以被设置为任意的正整数，设置的值应该是一个以毫秒为单位的合理时间量。 默认情况是不设置，也就是没有刷新间隔，缓存仅仅会在调用语句时刷新。

    #### size（引用数目）属性可以被设置为任意正整数，要注意欲缓存对象的大小和运行环境中可用的内存资源。默认值是 1024。

    #### readOnly （只读）属性可以被设置为 true 或 false。只读的缓存会给所有调用者返回缓存对象的相同实例。 因此这些对象不能被修改。这就提供了可观的性能提升。而可读写的缓存会（通过序列化）返回缓存对象的拷贝。 速度上会慢一些，但是更安全，因此默认值是 false。

  - ##### 本地缓存(一级缓存)

    ##### 任何在 session 执行过的查询结果都会被保存在本地缓存中，所以，当再次执行参数相同的相同查询时，就不需要实际查询数据库了。本地缓存将会在做出修改、事务提交或回滚，以及关闭 session 时清空。

  - ##### 二级缓存

- ### 动态SQL

  - #### if

    ```
    <select id="findActiveBlogLike"
         resultType="Blog">
      SELECT * FROM BLOG
      <where>
        <if test="state != null">
             state = #{state}
        </if>
        <if test="title != null">
            AND title like #{title}
        </if>
        <if test="author != null and author.name != null">
            AND author_name like #{author.name}
        </if>
      </where>
    </select>
    ```

  - #### choose、when、otherwise

    ##### choose 类似 switch，when就是 case，otherwise就是 default。

    ```
    <select id="findActiveBlogLike"
         resultType="Blog">
      SELECT * FROM BLOG WHERE state = ‘ACTIVE’
      <choose>
        <when test="title != null">
          AND title like #{title}
        </when>
        <when test="author != null and author.name != null">
          AND author_name like #{author.name}
        </when>
        <otherwise>
          AND featured = 1
        </otherwise>
      </choose>
    </select>
    ```

  - #### #### set

    ```
    <update id="updateAuthorIfNecessary">
      update Author
        <set>
          <if test="username != null">username=#{username},</if>
          <if test="password != null">password=#{password},</if>
          <if test="email != null">email=#{email},</if>
          <if test="bio != null">bio=#{bio}</if>
        </set>
      where id=#{id}
    </update>
    ```

  - #### foreach(对集合进行遍历（尤其是在构建IN条件语句的时候）)

    ```
    <select id="selectPostIn" resultType="domain.blog.Post">
      SELECT *
      FROM POST P
      WHERE ID in
      <foreach item="item" index="index" collection="list"
          open="(" separator="," close=")">
            #{item}
      </foreach>
    </select>
    ```

    ##### 你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象作为集合参数传递给 *foreach*。当使用可迭代对象或者数组时，index 是当前迭代的序号，item 的值是本次迭代获取到的元素。当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。

  - #### script (在带注解的映射器接口类中使用动态SQL)

    ```
       @Update({"<script>",
          "update Author",
          "  <set>",
          "    <if test='username != null'>username=#{username},</if>",
          "    <if test='password != null'>password=#{password},</if>",
          "    <if test='email != null'>email=#{email},</if>",
          "    <if test='bio != null'>bio=#{bio}</if>",
          "  </set>",
          "where id=#{id}",
          "</script>"})
        void updateAuthorValues(Author author);
    ```

- 
