package basis.clone;

import org.junit.Test;

/**
 * 浅拷贝会开辟一个新的内存来存储数据，但是类里面的引用类型还是共用的，只是基础数据类型克隆了
 * 深拷贝一样会开辟一个新的内存空间来存储数据，不过是连里面的引用类型也会重新开辟一个内存空间来存储
 * 不同：只有类内部的引用类型是否会共用
 *
 * @author LL
 * @Description:使用克隆(无论深浅)需要重写Object的clone方法 <p>
 * <p>
 */
public class CloneTest {

    @Test
    public void test() throws CloneNotSupportedException {
        // Student 实现了深浅拷贝
        Major m = new Major("计算机科学与技术", 666666);
        Student s = new Student("CodeSheep", 18, m);
        Student student = (Student) s.clone();
    }

}
