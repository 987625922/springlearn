## 多模块打war包

1. 把要打包的模块下的pom（我这里是learn-spring-web的pom）的配置好依赖（依赖好父模块的pom）

   ```
    <dependencies>
           <dependency>
               <groupId>com.wind.springlearn</groupId>
               <artifactId>learn-common</artifactId>
               <version>2.0</version>
           </dependency>
           <dependency>
               <groupId>com.wind.springlearn</groupId>
               <artifactId>learn-log</artifactId>
               <version>2.0</version>
           </dependency>
   ```

2. 把要打包的pom中修改，没有就写上<packaging>war</packaging>

   ```
    <modelVersion>4.0.0</modelVersion>
       <packaging>war</packaging>
       <artifactId>learn-spring-web</artifactId>
   ```

3. 在编辑器idea右侧的Maven Project中打开‘spring的学习’spring的学习--》lifecycle--》package

4. 打的war就出现在learn-spring-web模块下的taget文件夹中

5. 通过tomcat配置就可以运行这个war包了
