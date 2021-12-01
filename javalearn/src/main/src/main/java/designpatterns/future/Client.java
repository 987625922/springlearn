package designpatterns.future;

/**
 * 开启RealData线程，并快速返回FutereData
 *
 */
public class Client {
   public Data request(final String queryStr){
       final FutureData futureData = new FutureData();
       new Thread(() -> {
           //RealData的构建很慢，所以要在单独的线程中进行
           RealData realData = new RealData(queryStr);
           futureData.setRealData(realData);
       }).start();
       //FutureData会被立即返回
       return futureData;
   }

}
