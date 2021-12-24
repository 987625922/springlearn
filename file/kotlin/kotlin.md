# kotlin
### 1.类与集成

- ##### 构造函数

  ```
  class Person(val name: String) {
  	init(){}
      var children: MutableList<Person> = mutableListOf<>()
      constructor(name: String, parent: Person) : this(name) {
          parent.children.add(this)
      }
  }
  ```

  init()方法会在次构造函数前执行

  私有构造函数

  ```
  class DontCreateMe private constructor () { /*……*/ }
  ```

- ##### 继承

  超类是Any，Any有三个方法`equals()`、 `hashCode()` 与 `toString()`。因此，为所有 Kotlin 类都定义了这些方法。Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 `open` 关键字标记它。

  ```
  open class Base(p: Int)
  
  class Derived(p: Int) : Base(p)
  
  class MyView : View {
      constructor(ctx: Context) : super(ctx)
  
      constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
  }
  ```

  果派生类有一个主构造函数，其基类可以（并且必须） 用派生类主构造函数的参数就地初始化。如果派生类没有主构造函数，那么每个次构造函数必须使用 *super* 关键字初始化其基类型，或委托给另一个构造函数做到这一点。 

- ##### 覆盖方法

  ```
  open class Shape {
      open fun draw() { /*……*/ }
      fun fill() { /*……*/ }
  }
  
  class Circle() : Shape() {
      override fun draw() { /*……*/ }
  }
  ```

- ##### 覆盖属性

  ```
  open class Shape {
      open val vertexCount: Int = 0
  }
  
  class Rectangle : Shape() {
      override val vertexCount = 4
  }
  ```

- ##### 调用超类实现

  ```
  class FilledRectangle: Rectangle() {
      fun draw() { /* …… */ }
      val borderColor: String get() = "black"
      
      inner class Filler {
          fun fill() { /* …… */ }
          fun drawAndFill() {
              super@FilledRectangle.draw() // 调用 Rectangle 的 draw() 实现
              fill()
              println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
          }
      }
  }
  ```
覆盖规则

  可以同时继承 `Rectangle` 与 `Polygon`， 但是二者都有各自的 `draw()` 实现，所以我们必须在 `Square` 中

  覆盖 `draw()`， 并提供其自身的实现以消除歧义。
  
  ```
  open class Rectangle {
      open fun draw() { /* …… */ }
  }
  
  interface Polygon {
      fun draw() { /* …… */ } // 接口成员默认就是“open”的
  }
  
  class Square() : Rectangle(), Polygon {
      // 编译器要求覆盖 draw()：
      override fun draw() {
          super<Rectangle>.draw() // 调用 Rectangle.draw()
          super<Polygon>.draw() // 调用 Polygon.draw()
      }
}
  ```
  
- ##### 抽象类

  ```
  open class Polygon {
      open fun draw() {}
  }
  
  abstract class Rectangle : Polygon() {
      abstract override fun draw()
  }
  ```

- ##### 对象表达式（内部类）

  创建一个匿名内部类

  ```
  window.addMouseListener(object : MouseAdapter() {
      override fun mouseClicked(e: MouseEvent) { /*……*/ }
  
      override fun mouseEntered(e: MouseEvent) { /*……*/ }
  })
  ```

  创建一个内部类

  ```
  open class A(x: Int) {
      public open val y: Int = x
  }
  
  interface B { /*……*/ }
  
  val ab: A = object : A(1), B {
      override val y = 15
  }
  //=========
  fun foo() {
      val adHoc = object {
          var x: Int = 0
          var y: Int = 0
      }
      print(adHoc.x + adHoc.y)
  }
  ```

  对象声明（单例模式）

  ```
  object DataProviderManager {
      fun registerDataProvider(provider: DataProvider) {
          // ……
      }
      val allDataProviders: Collection<DataProvider>
          get() = // ……
  }
  ```

- ##### 伴生对象

  ```
  class MyClass {
      companion object { }
  }
  ```

### 2.属性与字段

Kotlin 类中的属性既可以用关键字 *var* 声明为可变的，也可以用关键字 *val* 声明为只读的。

如果只读属性的值在编译期是已知的，那么可以使用 *const* 修饰符将其标记为*编译期常量*。

lateinit 延迟初始化属性与变量，.isInitialized 检测一个 `lateinit var` 是否已经初始化

### 3.泛型

- 使用关键字 `out` 来支持协变，等同于 Java 中的上界通配符 `? extends`。

- 使用关键字 `in` 来支持逆变，等同于 Java 中的下界通配符 `? super`。

  ```
  var textViews: List<out TextView>
  var textViews: List<in TextView>
  class Producer<out T>{}
  ```

- java的无界符?和kotlin的*相等

- java的泛型extends可以换成：

  ```
  //java
  class Monster<T extends Animal & Food>{ 
  }
  //kotlin
  class Monster<T> where T : Animal, T : Food
  ```

### 4.扩展函数和方法

```
//把MutableList扩展一个swap的方法
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}
//扩展属性
var String.s: Int
    get() = this.length
    set(value){
        //set方法并没有field可以用来存储value，
        //其实际作用是使用通过value来操作调用者，即this
        println(this.plus(value))
    }
```
### 5.嵌套类与内部类

```
//嵌套类
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo = Outer.Nested().foo() // == 2
```

```
//内部类
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1
```
### 6.委托

```
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    //使用委托
    Derived(b).print()
}
```

委托属性

```
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
 
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
}
//使用委托属性
e.p = "NEW"
val e = Example()
println(e.p)
```

标准委托

- ##### 延迟属性 Lazy

- ##### 可观察属性 Observable

  每当我们给属性赋值时会调用该处理程序（在赋值*后*执行）。它有三个参数：被赋值的属性、旧值与新值：

  ```
  var name: String by Delegates.observable("<no name>") {
          prop, old, new ->
          println("$old -> $new")
      }
  ```

- ##### 把属性储存在映射中

  ```
  class User(val map: Map<String, Any?>) {
      val name: String by map
      val age: Int     by map
  }
  
  fun main() {
      val user = User(mapOf(
          "name" to "John Doe",
          "age"  to 25
      ))
      println(user.name) // Prints "John Doe"
      println(user.age)  // Prints 25
  ```

- 局部委托属性

  ```
  fun example(computeFoo: () -> Foo) {
      val memoizedFoo by lazy(computeFoo)
  
      if (someCondition && memoizedFoo.isValid()) {
          memoizedFoo.doSomething()
      }
  }
  ```

  

### 7.函数

如果一个函数不返回任何有用的值，它的返回类型是 `Unit`。`Unit` 是一种只有一个值——`Unit` 的类型。这个值不需要显式返回。

##### 可变数量的参数

```
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = asList(1, 2, 3)
```

##### 高阶函数

高阶函数是将函数用作参数或返回值的函数。

```
fun <T, R> Collection<T>.fold(
    initial: R, 
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

val items = listOf(1, 2, 3, 4, 5)

// Lambdas 表达式是花括号括起来的代码块。
items.fold(0, { 
    // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
    acc: Int, i: Int -> 
    print("acc = $acc, i = $i, ") 
    val result = acc + i
    println("result = $result")
    // lambda 表达式中的最后一个表达式是返回值：
    result
})
```

##### 函数实例

```
fun main() {
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!")) 

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // 类扩展调用

}
```

##### 内联函数

直接把`调用的函数里面的代码`放到我调用的地方，省去寻找调用函数的位置的时间。

### 8. 集合

- List

  listOf

  mutableListOf

- set

  setOf

- map

  mapOf

### 9. 协程

| 启动方式           | 说明                                                         |
| :----------------- | :----------------------------------------------------------- |
| runBlocking        | 创建新的协程，运行在当前线程上，所以会堵塞当前线程，直到协程体结束 |
| GlobalScope.launch | 启动一个新的线程，在新线程上创建运行协程，不堵塞当前线程     |
| GlobalScope.asyn   | 启动一个新的线程，在新线程上创建运行协程，并且不堵塞当前线程，支持 通过await**获取返回值** |

```
fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
    }
    println("Hello,") // 主线程中的代码会立即执行
    //开启一个阻塞的协程
    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    } 
}
```

##### 等待一个协程完成

```
val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
    delay(1000L)
    println("World!")
}
println("Hello,")
job.join() // 等待直到子协程执行结束
```

##### 取消协程的执行

协程的取消是 *协作* 的。一段协程代码必须协作才能被取消。 

```
val job = launch {
    repeat(1000) { i ->
        println("job: I'm sleeping $i ...")
        delay(500L)
    }
}
delay(1300L) // 延迟一段时间
println("main: I'm tired of waiting!")
job.cancel() // 取消该作业
job.join() // 等待作业执行结束
println("main: Now I can quit."
```

##### 协程超时

```
withTimeout(1300L) {
//循环1000次
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}
```

##### async

在概念上，[async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html) 就类似于 [launch](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html)。它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。不同之处在于 `launch` 返回一个 [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) 并且不附带任何结果值，而 `async` 返回一个 [Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html) —— 一个轻量级的非阻塞 future， 这代表了一个将会在稍后提供结果的 promise

```
val time = measureTimeMillis {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("The answer is ${one.await() + two.await()}")
}
println("Completed in $time ms")
```

##### 惰性启动的 async

可选的，[async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html) 可以通过将 `start` 参数设置为 [CoroutineStart.LAZY](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-start/-l-a-z-y.html) 而变为惰性的。 在这个模式下，只有结果通过 [await](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html) 获取的时候协程才会启动，或者在 `Job` 的 [start](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/start.html) 函数调用的时候。

```
val time = measureTimeMillis {
    val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
    val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
    // 执行一些计算
    one.start() // 启动第一个
    two.start() // 启动第二个
    println("The answer is ${one.await() + two.await()}")
}
println("Completed in $time ms")
```

##### 可在全局创建协程的： lauch 与 runBlocking
 lauch 与 runBlocking都能在全局开启一个协程，但 lauch 是非阻塞的 而 runBlocking 是阻塞的

##### 可返回结果的协程：withContext 与 async 

withContext 与 async 都可以返回耗时任务的执行结果。 一般来说，多个 withContext 任务是串行的， 且withContext 可直接返回耗时任务的结果。 多个 async 任务是并行的，async 返回的是一个Deferred<T>，需要调用其await()方法获取结果。

### 10.语言结构

- ##### 类型检测与类型转换

  ##### `is` 与 `!is`

  检测对象是否符合给定类型

  ```
  if (obj is String) {
      print(obj.length)
  }
  
  if (obj !is String) { // 与 !(obj is String) 相同
      print("Not a String")
  }
  else {
      print(obj.length)
  }
  ```

- ##### 转换操作符

  ```
  val x: String = y as String
  ```

  为了避免抛出异常，可以使用*安全*转换操作符 *as?*，它可以在失败时返回 *null*

  ```
  val x: String? = y as? String
  ```

- ##### This 表达式

  ```
  class A { // 隐式标签 @A
      inner class B { // 隐式标签 @B
          fun Int.foo() { // 隐式标签 @foo
              val a = this@A // A 的 this
              val b = this@B // B 的 this
  
              val c = this // foo() 的接收者，一个 Int
              val c1 = this@foo // foo() 的接收者，一个 Int
  
              val funLit = lambda@ fun String.() {
                  val d = this // funLit 的接收者
              }
  
  
              val funLit2 = { s: String ->
                  // foo() 的接收者，因为它包含的 lambda 表达式
                  // 没有任何接收者
                  val d1 = this
              }
          }
      }
  }
  ```

- ##### 相等性

  ==比较的是数值是否相等, 而===比较的是两个对象的地址是否相等。

- ##### 

