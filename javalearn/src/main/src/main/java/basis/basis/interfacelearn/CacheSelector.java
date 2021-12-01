package basis.basis.interfacelearn;

/**
 * 被@FunctionalInterface注解的接口可以实例成lamda表达式
 *
 * @param <T>
 */
@FunctionalInterface
public interface CacheSelector<T> {
    T select();
}
