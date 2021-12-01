package basis.basis;

/**
 * 函数式接口
 * @param <T>
 * @param <J>
 */
public interface Converter<F, T> {
    public F run(T t);
}
