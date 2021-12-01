package designpatterns.future;

/**
 * 真实数据
 */
public class RealData implements Data {
    protected final String result;
    //模拟长时间获取的真实数据
    public RealData(String para){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }
    //返回真实数据
    @Override
    public String getResult() {
        return result;
    }
}
