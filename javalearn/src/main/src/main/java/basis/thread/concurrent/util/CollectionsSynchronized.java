package basis.thread.concurrent.util;

import java.util.*;

public class CollectionsSynchronized {

    /**
     * 创建线程安全的集合
     */
    public static Map map = Collections.synchronizedMap(new HashMap<>());
    public static List list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

    }
}
