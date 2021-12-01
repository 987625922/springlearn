package basis.basis.classcodeloadingorder;

/**
 * 类的代码加载顺序
 * <p>
 * 执行顺序优先级：静态块>main()>构造块>构造方法
 * @author LL
 */
public class ClassCodeLoadingOrder {

    static {
        System.out.println("这是静态代码块");
        System.out.println("静态块：用static申明，JVM加载类时执行，仅执行一次");
    }

    {
        System.out.println("构造块：类中直接用{}定义，每一次创建对象时执行");
    }

    public ClassCodeLoadingOrder() {
        System.out.println("这是构造方法");
    }

    public static void show() {
        System.out.println("这是静态方法");
    }

    public static void main(String[] args) {
        System.out.println("==============");

        ClassCodeLoadingOrder.show();

        ClassCodeLoadingOrder c = new ClassCodeLoadingOrder();
        c.test();
    }

    public void test() {
        System.out.println("这是类的方法");
    }
}
