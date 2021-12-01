package basis.basis.finallearn;

import common.bean.User;

/**
 * final的使用
 * 修饰类，表示类不能被继承
 *
 * @author LL
 */
public final class FinalMain {
    /**
     * final修饰基本类型，该变量只有一次赋值的机会
     */
    final int i = 1;
    /**
     * final修饰引用,指向对象的引用不可变
     */
    final User user = new User();

    /**
     * final修饰方法，表示方法不能被重写
     */
    public final void test() {

    }
}
