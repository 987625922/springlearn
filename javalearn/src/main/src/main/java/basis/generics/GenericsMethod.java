package basis.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 泛型的使用
 * @author LL
 */
public class GenericsMethod {
    /**
     * 泛型方法
     *
     * @param t
     * @param <T>
     */
    public <T> void f(T t) {
        System.out.println(t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericsMethod gm = new GenericsMethod();
        gm.f("");
        HashSet<String> s2 = new HashSet<String>(Arrays.asList("a", "b", "c"));
        printSet(s2);
    }

    /**
     * ？表示能接收任何类型的参数
     *
     *当你使用泛型(generic type)，但是你又不想关心实际泛型是什么的时候，使用无界通配符。
     *
     * @param s
     */
    public static void printSet(Set<?> s) {
//        s.add("") 因为不知道是什么类型，所以除了null都不能添加
//        s.add(new Object());
        s.add(null);
        for (Object o : s) {
            System.out.println(o);
        }
    }

    /**
     * 因为原生态类型没有限制，
     * 所以它很容易是集合混乱，换句话说，无界通配符是安全的而原生态类型不是安全的。
     * @param s
     */
    public static void printSet1(Set s) {
        s.add("2");
        for (Object o : s) {
            System.out.println(o);
        }
    }

}
