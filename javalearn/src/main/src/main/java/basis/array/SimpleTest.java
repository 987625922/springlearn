package basis.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 数组的笔记类
 *
 * @author LL
 */
@Slf4j
public class SimpleTest {

    int[] a = {1, 2, 3, 4, 6, 5, 7, 8};

    /**
     * 数组复制
     */
    @Test
    public void arrayCopy() {
        int[] b = Arrays.copyOf(a, 5);
        log.info(Arrays.toString(b));
    }

    /**
     * 数组排序
     */
    @Test
    public void sort() {
        log.info("排序前:" + Arrays.toString(a));
        Arrays.sort(a);
        log.info("排序后:" + Arrays.toString(a));
    }

    /**
     * 搜索字所在的位置
     */
    @Test
    public void binarySearch() {
        log.info("数字7出现的位置:" + Arrays.binarySearch(a, 7));
    }

    /**
     * 数组判断是否相等
     */
    @Test
    public void equals() {
        log.info(String.valueOf(Arrays.equals(a, a)));
    }


}
