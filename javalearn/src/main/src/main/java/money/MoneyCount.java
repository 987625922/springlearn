package money;

import java.math.BigDecimal;

/**
 * java对钱的计算
 */
public class MoneyCount {

    public static void main(String[] args) {
        //注意：不能使用float类型去构建BigDecimal
        //通过代码就可以看出来了，使用float构造方法，转换出来的数据依旧是不精确的
        float a = 50.49f;
        String a2 = String.valueOf(a);
        BigDecimal bigDecimal22 = new BigDecimal(a);
        System.out.println(bigDecimal22);  //输出50.490001678466796875
        BigDecimal bigDecimal2 = new BigDecimal(a2);
        System.out.println(bigDecimal2); //输出50.49
        //============ 以上的是错误示范

        //============ 以下正确示范
        BigDecimal bigDecimal = new BigDecimal("50.49");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.add(new BigDecimal("10")));  //加
        System.out.println(bigDecimal.subtract(new BigDecimal("10")));  //减
        System.out.println(bigDecimal.multiply(new BigDecimal("10")));  //乘
        System.out.println(bigDecimal.divide(new BigDecimal("10")));  //除
    }

    /**
     * Mysql中如何存储金额
     *
     * 在Mysql中，可以用decimal字段记录金额：
     *
     * `money` DECIMAL(18,2) not null comment '金额'
     *
     * 同时也能用varchar以字符串的方式保存
     *
     * 或者通过int或者bigint来存储以分为单位的金额。
     */
}
