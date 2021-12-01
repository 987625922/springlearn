package basis.generics;

/**
 * 普通的泛型类
 *
 * 泛型擦除就是会在编译的时候把T转换成Object类型
 * @param <T>
 */
public class GenericsClass<T> {

    T t;

    public GenericsClass(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
