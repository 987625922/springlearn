## spring基于注解的缓存
1. 缓存声明，spring的缓存提供了一组java注解:

   - @Cacheable:触发缓存写入
   - @CacheEvict:触发缓存清除
   - @CachePut:更新缓存(不会影响到方法的运行)
   - @Caching:重新组合要应用于方法的多个缓存操作
   - @CacheConfig:设置类级别上共享的一些常见缓存设置

2. @Cacheable注解

   - 用来进行缓存的写入，将结果存储在缓存中，以便于在后续调用的时候可以直接返回缓存中的值，而不必再执行实际的方法。

     ```
     最简单的使用
     @Cacheable("books")
         public Book findBook(ISBN isbn) {...}
     ```

   - 缓存名称是可以配置动态参数的，比如选择传入的参数,如下: (以下示例是使用SpEL声明,如果您不熟悉SpEL，可以阅读[Spring Expression Language](https://docs.spring.io/spring/docs/5.2.0.M1/spring-framework-reference/core.html#expressions))

     ```
     @Cacheable(cacheNames="books", key="#isbn")
         public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
     ```

   - @Cacheable还可以设置根据条件判断是否需要缓存

     - condition:取决于给定的参数是否满足条件
     - unless:取决于返回值是否满足条件

     ```
      @Cacheable(cacheNames="book", condition="#name.length() < 32") 
         public Book findBook(String name)
         
         @Cacheable(cacheNames="book", condition="#name.length() < 32", unless="#result.hardback") 
         public Book findBook(String name) 
     ```

   - @Cacheable还可以设置:keyGenerator(指定key自动生成方法),cacheManager(指定使用的缓存管理),cacheResolver(指定使用缓存的解析器)等,这些参数比较适合全局设置

3. @CachePut注解

   @CachePut:当需要更新缓存而不干扰方法的运行时 ，可以使用该注解。也就是说，始终执行该方法，并将结果放入缓存，注解参数与@Cacheable相同

   ```
     @CachePut(cacheNames="book", key="#isbn")
       public Book updateBook(ISBN isbn, BookDescriptor descriptor) 
   ```

4. @CacheEvict注解

   删除缓存的注解,这对删除旧的数据和无用的数据是非常有用的。这里还多了一个参数(allEntries),设置allEntries=true时，可以对整个条目进行批量删除。 

   ```
    @CacheEvict(cacheNames="books") 
       public void loadBooks(InputStream batch)
       
       //对cacheNames进行批量删除
       @CacheEvict(cacheNames="books", allEntries=true) 
       public void loadBooks(InputStream batch) 
   ```

5. @CacheConfig

   - @CacheConfig:缓存提供了许多的注解选项，但是有一些公用的操作，我们可以使用@CacheConfig在类上进行全局设置。 以下是个简单的例子：

   ```
      @CacheConfig("books") 
       public class BookRepositoryImpl implements BookRepository {
       
           @Cacheable
           public Book findBook(ISBN isbn) {...}
       } 
   ```

   