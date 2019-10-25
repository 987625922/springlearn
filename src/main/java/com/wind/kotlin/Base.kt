package com.wind.kotlin

/**
 * kotlin学习
 */
fun main(args: Array<String>) {
    //方法调用
//    printStr("aa", "bb")
    //类的调用
//    var test = Test()
//    test.getLenght()
    //数组的调用
//    prinArray()
    var testTwo = TestTwo(1)
    testTwo.getLenght()

}

/**
 * 基础的方法创建
 */
fun printStr(str: String, result: String): String {
    print(str + result)
    return str + result
}

/**
 * 方法链
 */
fun printStrTwo(str: String, result: String = "bb") = str + result

/**
 * 数组的使用
 */
val array = intArrayOf(1, 2, 3)

fun prinArray() {
    array.forEach { println(it) }
}

/**
 * if表达式
 */
val a = 1
val b = 2
val max = if (a > b) a else b
val max1 = if (a > b) {
    print("Choose a")
    a
} else {
    print("Choose b")
    b
}

/**
 * when 取代了类 C 语言的 switch 操作符
 */
fun whenUse(x: Int) {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x is neither 1 nor 2")
        }
    }
}

/**
 * return的使用
 */
fun returnUse(x: Int) {
    var number = x ?: return
    println(number)
}


/**
 * 基础的类
 */
open class Test(number: Int) {
    /**
     * 次级构造器
     */
    constructor(number: Int, str: String) : this(number) {
        println(str + number)
    }

    //kotlin变量默认不能为空，加上？表示可以为空
    var lenght: Int? = null

    open fun getLenght() {
        //是null就返回null，否则返回长度，注意它的返回值是一个Int?（又是一个Nullable类型)
        println(lenght)
        //如果为空就返回empty
        println(lenght ?: "empty")
        //如果lenght不为空就执行
        //        lenght = 1
        lenght?.let {
            println("lenght is $lenght is not null!")
        }
    }
}

/**
 * 类的继承
 */
class TestTwo(number: Int) : Test(number) {
    /**
     * 覆盖方法
     */
    override fun getLenght() {
        println("覆盖方法")
    }
}

/**
 * 接口
 */
interface MyInterface {
    fun oneFun()
    fun twoFun() {
        println("==")
    }
}

/**
 * 接口实现
 */
class UseInterface : MyInterface {
    override fun oneFun() {

    }

}