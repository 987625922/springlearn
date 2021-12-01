package basis.basis.innerclass;

/**
 * 匿名内部类使用的类
 *
 * @author LL
 */
public class Main {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        /**
         * 内部类只有与外部类相关联的时候才能被创建
         */
        OuterClass.InnerClass innerClass = outerClass.getInnerClass();
        /**
         * 创建一个内部类
         */
        OuterClass.InnerClass innerClass1 = outerClass.new InnerClass();
        /**
         * prvate 的内部类 只能在类中实现
         */
//        OuterClass.PInnerClass innerClass2 = outerClass.new PInnerClass();
        /**
         * 静态内部类
         */
        OuterClass.SInnerClass innerClass2 = new OuterClass.SInnerClass();
        /**
         * 这就是匿名内部类
         */
        new Thread() {
            @Override
            public void run() {
            }
        }.start();
    }
}
