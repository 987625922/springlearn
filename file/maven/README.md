## MAVEN

1. [构建的各个环节](#构建的各个环节)

2. [目录结构](#目录结构)

3. [常用maven命令](#常用maven命令)

4. [maven的使用](#使用)

   1. **<a id="构建的各个环节">构建的各个环节</a>**

      - 清理clean：将以前编译得到的旧文件class字节码文件删除
      - 编译compile：将java源程序编译成class字节码文件
      - 测试test：自动测试，自动调用junit程序
      - 报告report：测试程序执行的结果
      - 打包package：动态Web工程打War包，java工程打jar包
      - 安装install：Maven特定的概念-----将打包得到的文件复制到“仓库”中的指定位置
      - 部署deploy：将动态Web工程生成的war包复制到Servlet容器下，使其可以运行
      
   2. **<a id="目录结构">目录结构（maven工程必须按照约定的目录结构创建）</a>**

      根目录：工程名
      |---src：源码
      |---|---main:存放主程序
      |---|---|---java：java源码文件
      |---|---|---resource：存放框架的配置文件
      |---|---test：存放测试程序
      |---pop.xml：maven的核心配置文件

   3. **<a id="常用maven命令">常用maven命令</a>**

      - mvn clean：清理
      - mvn compile：编译主程序
      - mvn test-compile：编译测试程序
      - mvn test：执行测试
      - mvn package：打包
      - mvn install：安装

   4. **<a id="使用">maven的使用</a>**

      ```
      <?xml version="1.0" encoding="UTF-8"?>
      <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
          <modelVersion>4.0.0</modelVersion> 指定了当前的POM模型的版本
          <groupId>com.tengj</groupId> 定义了项目属于哪个组或者公司名，随意命名
          <artifactId>springBootDemo1</artifactId>定义了当前Maven项目在组中唯一的ID
          <version>0.0.1-SNAPSHOT</version>项目版本号
          <name>springBootDemo1</name>项目名
          
          <dependencies>
          <dependency>
      	<groupId>实际项目</groupId>
      	<artifactId>模块</artifactId>
      	<version>版本</version>
      	依赖的类型，对于项目坐标定义的packaging。大部分情况下，该元素不必声明，其默认值为jar
      	<type>依赖类型</type>
      	
      	<scope>依赖范围</scope>
      	
      	optional:标记依赖是否可选
      	<optional>依赖是否可选</optional>
      	
      	exclusions:用来排除传递性依赖
      	<!—主要用于排除传递性依赖-->
      	<exclusions>
      	 <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-logging</artifactId>
          </exclusion>
      	<exclusion>
      	
      <groupId></groupId>
      <artifactId></artifactId>
      </exclusion>
</exclusions>
      </dependency>
<dependencies>
      
</project>
      
```
      
依赖范围：
      
   -  **compile:**编译依赖范围。如果没有指定，就会默认使用该依赖范围。使用此依赖范围的Maven依赖，对于编译、测试、运行三种classpath都有效。典型的例子是spring-code,在编译、测试和运行的时候都需要使用该依赖。
         -  **test:** 测试依赖范围。使用次依赖范围的Maven依赖，只对于测试classpath有效，在编译主代码或者运行项目的使用时将无法使用此依赖。典型的例子是Jnuit,它只有在编译测试代码及运行测试的时候才需要。
   -  **provided:**已提供依赖范围。使用此依赖范围的Maven依赖，对于编译和测试classpath有效，但在运行时候无效。典型的例子是servlet-api,编译和测试项目的时候需要该依赖，但在运行项目的时候，由于容器以及提供，就不需要Maven重复地引入一遍。
         -  **runtime:**运行时依赖范围。使用此依赖范围的Maven依赖，对于测试和运行classpath有效，但在编译主代码时无效。典型的例子是JDBC驱动实现，项目主代码的编译只需要JDK提供的JDBC接口，只有在执行测试或者运行项目的时候才需要实现上述接口的具体JDBC驱动。
         -  **system:**系统依赖范围。该依赖与三种classpath的关系，和provided依赖范围完全一致，但是，使用system范围的依赖时必须通过systemPath元素显示地指定依赖文件的路径。由于此类依赖不是通过Maven仓库解析的，而且往往与本机系统绑定，可能构成构建的不可移植，因此应该谨慎使用。systemPath元素可以引用环境变量
      
      
