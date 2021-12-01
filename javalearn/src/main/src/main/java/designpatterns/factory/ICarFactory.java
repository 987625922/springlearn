package designpatterns.factory;

/**
 * 汽车工程的接口
 * @author LL
 */
public interface ICarFactory {
    /**
     * 生产不同的汽车
     */
    Car create();
}

