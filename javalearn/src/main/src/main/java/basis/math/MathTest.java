package basis.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: LL
 * @Description:Math类的使用
 * @Date:Create：in 2020/8/7 11:21
 */
@Slf4j
public class MathTest {

    /**
     * Math四舍五入
     */
    @Test
    public void round() {
        //四舍五入
        log.info(String.valueOf(Math.round(-1.5)));
    }

    /**
     * Math随机数
     */
    @Test
    public void random() {
        //返回0.0到1.0的dobule类型随机数，不包括1.0
        log.info(String.valueOf(Math.random()));
    }

    /**
     * Math绝对值
     * 绝对值：正数的绝对值是正数，负数的绝对值是它的正数
     */
    @Test
    public void abs() {
        log.info(String.valueOf(Math.abs(-1)));
    }
}
