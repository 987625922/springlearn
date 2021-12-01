package designpatterns.future;

/**
 * 真实数据的代理
 * 用来包装真实数据，过段时间后可以拿这个来获取真实数据
 */
public class FutureData implements Data {
    protected RealData realData = null; //FutereData是RealData的包装
    protected boolean isReady = false;//是否set了真实数据

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        //设置真实数据
        this.realData = realData;
        isReady = true;
        //唤醒所有在wait的线程
        notifyAll();
    }

    public synchronized String getResult() {
        if (!isReady) {
            try {
                //如果真实数据没有获取完成就阻塞住
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }

}
