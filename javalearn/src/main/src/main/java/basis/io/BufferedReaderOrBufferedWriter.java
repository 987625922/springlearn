package basis.io;

import java.io.*;

public class BufferedReaderOrBufferedWriter {

    // 一次拷贝一个 字符
    private static void copyFile1(String src, String dest) throws IOException {
        //1. 创建转换流
        Reader reader = new BufferedReader(new FileReader(src));
        Writer writer = new BufferedWriter(new FileWriter(dest));

        //2. 拷贝数据
        int data = reader.read();
        while (data != -1) {
            writer.write(data);
            data = reader.read();
        }
        //3.关闭流
        reader.close();
        writer.close();
    }

    // 一次拷贝一个 字符数组
    private static void copyFile2(String src, String dest) throws IOException {
        //1. 创建转换流
        Reader reader = new BufferedReader(new FileReader(src));
        Writer writer = new BufferedWriter(new FileWriter(dest));

        //2. 拷贝数据
        char[] buffer = new char[2048];
        int len = reader.read(buffer);
        while (len != -1) {
            writer.write(buffer, 0, len);
            len = reader.read(buffer);
        }
        //3.关闭流
        reader.close();
        writer.close();
    }

    // 一次拷贝一个一整行的 字符串
    private static void copyFile3(String src, String dest) throws IOException {
        //1. 创建转换流
        BufferedReader reader = new BufferedReader(new FileReader(src));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));

        //2. 拷贝数据
        String data = reader.readLine();
        while (data != null) {
            writer.write(data);
            writer.newLine();
            data = reader.readLine();
        }
        //3.关闭流
        reader.close();
        writer.close();
    }

}
