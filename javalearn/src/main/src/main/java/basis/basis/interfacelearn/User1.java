package basis.basis.interfacelearn;

import org.junit.Test;

/**
 * 接口内实体方法的使用
 *
 * @author LL
 */

public class User1 implements Person, PersonTwo {

    @Override
    public void getName() {
        PersonTwo.super.getName();
        System.out.println("user");
    }

    @Test
    public void test() {
        User1 user1 = new User1();
        user1.getName();
    }
}
