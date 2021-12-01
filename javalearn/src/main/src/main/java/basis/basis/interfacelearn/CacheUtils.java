package basis.basis.interfacelearn;

/**
 * @FunctionalInterface注解的使用
 * @author LL
 */
public class CacheUtils {

    public static <T> T test(CacheSelector<T> cacheSelector) {
        T t = cacheSelector.select();
        return t;
    }
}
