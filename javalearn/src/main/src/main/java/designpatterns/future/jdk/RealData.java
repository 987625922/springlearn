package designpatterns.future.jdk;

import java.util.concurrent.Callable;

/**
 * 获取真实数据的
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }
    //模拟长时间获取的真实数据
    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         return sb.toString();
    }
}
