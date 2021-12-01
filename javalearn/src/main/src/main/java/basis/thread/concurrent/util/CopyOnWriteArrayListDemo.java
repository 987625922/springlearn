package basis.thread.concurrent.util;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 适合读多写少的场合
 */
public class CopyOnWriteArrayListDemo {
    static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

    }
}
