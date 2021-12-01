package basis.reflex;

import basis.annotation.Component;
import basis.annotation.ComponentTwo;

/**
 * 被反射的目标类
 *
 * @author LL
 */
@ComponentTwo
public class TargetClass implements ITargetClass {

    public int[] indexArray = {1, 2, 1, 2, 1};
    int index = 101;
    String str = "11";

    public TargetClass() {

    }

    @Component
    public TargetClass(String str) {
        this.str = str;
    }

    public TargetClass(int index) {
        this.index = index;
    }

    private TargetClass(int index, String str) {
        this.index = index;
        this.str = str;
    }

    @Component
    @Override
    public void find() {
        System.out.println("===>find()");
    }

    public int[] getIndexArray() {
        return indexArray;
    }

    public void setIndexArray(int[] indexArray) {
        this.indexArray = indexArray;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    private class innerClass {

    }

    public interface innerInterface {

    }
}
