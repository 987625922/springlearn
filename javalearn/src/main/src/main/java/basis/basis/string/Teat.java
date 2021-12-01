package basis.basis.string;

import org.junit.Test;

/**
 * @Author: 98762
 * @Date: 2020/7/29 22:37
 * @Description:
 * @备注： 1.
 */
public class Teat {
    @Test
    public void test(){
        String s1 = "h w";
        String s2 = "h ";
        String s3 = s2+"w";
        s3.hashCode();
        System.out.println(s1 == s3);
    }
}
