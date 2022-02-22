package basis.collection;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        //<<      :     左移运算符，num << 1,相当于num乘以2
        //
        //>>      :     右移运算符，num >> 1,相当于num除以2
        //
        //>>>    :     无符号右移，忽略符号位，空位都以0补齐

        HashMap hashMap;
        String s = "222222";
        //  15 & hash(s) 等于 hash(s) % 16 ,就是算值在数组中的下标位置
        System.out.println(15 & hash(s));
        System.out.println(hash(s) % 16);

        System.out.println(6 & hash(s));
        System.out.println(hash(s) % 8);
    }

    static final int hash(Object key) {
        int h;
        /**先获取到key的hashCode，然后进行移位再进行异或运算，为什么这么复杂，不用想肯定是为了减少hash冲突**/
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

