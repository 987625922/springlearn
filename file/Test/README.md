## Junit使用
1. 单元测试需要遵循一下规则：
   
   - 每一个测试方法上使用@Test进行修饰
   
   - 每一个测试方法必须使用public void 进行修饰
   
   - 每一个测试方法不能携带参数
   
   - 测试代码和源代码在两个不同的项目路径下
   
   - 测试类的包应该和被测试类保持一致
   
   - 测试单元中的每个方法必须可以独立测试
2. 主要注解的使用
    ```  
    public class JunitFlowText {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.out.println("this is beforeClasss....");
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("this is afterClasss....");
	}
	@Before
	public void setUp() throws Exception {
		System.out.println("this is brfore....");
	}
	@After
	public void tearDown() throws Exception {
		System.out.println("this is after....");
	}
	@Test
	public void test1() {
		System.out.println("this is test1....");
	}
	@Test
	public void test2() {
		System.out.println("this is test2....");
	}
    }
    ```
    输出:
    ```
    this is beforeClass...
    this is brfore...
    this is test1...
    this is after...
    this is brfore...
    this is test2...
    this is after...
    this is afterClass...
    ```
3. 注解
   - @BeforeClass
     ##### 修饰的方法会在所有方法被调用前执行，且该方法时静态的，
     所以当测试类被加载后就接着运行它，而且在内存中他只会存在一份实例，
     他比较适合加载配置文件（针对所有测试，只执行一次 ）
     
   - @AfterClass
     ##### 所修饰的方法通常用来对资源管理，如关闭数据库连接（针对所有测试，只执行一次 ）
     
   - @Before和@After 
     ##### 会在每个测试方法前后各执行一次
    
   - @Test
     ##### 测试方法，在这里可以测试期望异常和超时时间
   - @Test(expected=XX.class)
     ##### 这个参数表示我们期望会出现什么异常，比如说在除法中，
     我们1/0会出现ArithmeticException异常，那这里@Test(expected=ArithmeticException.
     class)。在测试这个除法时候依然能够通过。
   - @Test(timeout=毫秒)
     ##### 这个参数表示如果测试方法在指定的timeout内没有完成，就会强制停止。
   - @Ignore
     ##### 忽略的测试方法  

4. TestSuite
   就是连续运行多个测试类
   ```
    package junit;
     
    import org.junit.runner.RunWith;
    import org.junit.runners.Suite;
     
    @RunWith(Suite.class)
    //这个就是TestSuite
    @Suite.SuiteClasses({TestCase1.class,TestCase2.class})
    public class TestSuite {
    }
   ```