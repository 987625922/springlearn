package designpatterns;

/**
 * 不变模式
 * 对象一旦被创建，它的内部状态和数据永远不会改变
 * 注意：
 *  1.清除setter方法
 *  2.所有变量私有并用final标记
 *  3.有一个可以创建完整对象的构造函数
 */
public final class CannotChangePattern {

    private final String name;

    //在创建对象时，指定数据，创建后无修改
    public CannotChangePattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
