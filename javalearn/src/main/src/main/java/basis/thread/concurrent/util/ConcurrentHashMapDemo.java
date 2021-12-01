package basis.thread.concurrent.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程安全的HashMap
 */
public class ConcurrentHashMapDemo {
    static ConcurrentHashMap cMap = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {
        cMap.put("1", "1");
    }
}
