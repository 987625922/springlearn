package basis.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

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

    /**
     * bigDecimal 一般用于浮点型高精度的计算（比如说金额的计算）
     */
    @Test
    public void bigDecimal() {
        //  根据阿里java手册，推荐使用字符串生成BigDecimal,不然会有精度丢失问题
        BigDecimal bigDecimal = new BigDecimal("50.49");
        BigDecimal bigDecimal1 = new BigDecimal("50.49");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.add(new BigDecimal("10")));  //加
        System.out.println(bigDecimal.subtract(new BigDecimal("10")));  //减
        System.out.println(bigDecimal.multiply(new BigDecimal("10")));  //乘
        System.out.println(bigDecimal.divide(new BigDecimal("10")));  //除

        //  BigDecimal 数据比较
        //  equals等于，比较的是对象本身。包括很多东西。
        //  compareTo比较，比较的是对象某一特定的内容。所以通常需要用compare来做比较函数
        if (bigDecimal.compareTo(bigDecimal1) == 0) {
            //  相等
            System.out.println("=========>> 相等");
        }
    }
}
