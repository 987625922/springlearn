package designpatterns.productionandconsumptionpattern;

public class Data {
    private final int intData;

    public Data(int intData) {
        this.intData = intData;
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "Data{" +
                "intData=" + intData +
                '}';
    }
}
