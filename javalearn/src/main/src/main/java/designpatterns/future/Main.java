package designpatterns.future;

/**
 * Future模式,
 * 如果一个结果获取很慢,然后又不是急切的需要获取,那就可以使用这个模式
 * 主函数，负责调起Client
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为得到的是FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            //模拟其他操作,因为真实数据不是很急切的需要获取
            //所以可以先去忙其他的，忙完了再回来拿
            Thread.sleep(2000);
        }catch (InterruptedException e){
        }
        //sleep2秒之后获取到真实的数据
        System.out.println("真实数据 = "+data.getResult());
    }
}
