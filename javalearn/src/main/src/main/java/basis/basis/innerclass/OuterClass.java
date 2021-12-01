package basis.basis.innerclass;

/**
 * 内部类的使用
 * 内部类只有与外部类相关联的时候才能被创建，
 * 没有外部类前是不可能创建内部类对象的
 * <p>
 * 作用：
 * 1）可以通过外围类继承一个abstract类，内部类继承另一个abstract类的方式
 * 实现类的多重继承,内部类使得多继承的解决方案变得完整.
 * 2) 方便编写线程代码
 *
 * @author LL
 */
public class OuterClass {

    private int index = 1;

    /**
     * 获取InnerClass对象
     *
     * @return
     */
    public InnerClass getInnerClass() {
        return new InnerClass();
    }

    public PInnerClass getPInnerClass() {
        return new PInnerClass();
    }

    /**
     * 局部内部类
     * 你要解决一个复杂的问题，你想要一个类来辅助你的解决方案，但又不希望这个类是公共可用的
     */
    public void localInnerClass() {
        /**
         * 局部内部类
         */
        class LocalInnerClass {
            public void test() {
                System.out.println("局部内部类的方法");
            }
        }
        LocalInnerClass localInnerClass = new LocalInnerClass();
        localInnerClass.test();
    }

    /**
     * 匿名内部类
     */
    public IInnerClass anonymityClass(String str) {
        return new IInnerClass() {
            private String innerStr = str;

            @Override
            public String getValue() {
                return str;
            }
        };
    }

    /***
     * ============================   内部类
     */
    class InnerClass {

        public void test() {
            /**
             * 内部类可以使用外部类的方法和变量
             */
            getInnerClass();
            System.out.println(index);
        }

        /**
         * 返回一个外部对象的引用
         */
        public OuterClass outer() {
            return OuterClass.this;
        }

    }

    /**
     * private内部类，只有内部类代码内可以使用，
     * 外部无法同外部类.new PInnerClass()的方式获取
     */
    private class PInnerClass {

    }

    /**
     * 静态内部类
     */
    static class SInnerClass {

    }
}
