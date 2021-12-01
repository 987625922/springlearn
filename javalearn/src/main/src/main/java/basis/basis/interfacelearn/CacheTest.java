package basis.basis.interfacelearn;

import org.junit.Test;

/**
 * 被注解的@FunctionalInterface的使用
 *
 * @author LL
 */
public class CacheTest {

    @Test
    public void test() {
        CacheUtils.test((CacheSelector<String>) () -> null);
    }
}
