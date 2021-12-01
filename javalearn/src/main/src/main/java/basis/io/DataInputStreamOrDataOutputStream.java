package basis.io;

import java.io.*;

/**
 * DataInputStream & DataOutputStream，是处理流，
 * 允许程序从读取和操作java的基本数据类型。
 */
public class DataInputStreamOrDataOutputStream {

    // 向文件中写入 java基本数据类型
    private static void write(String dest) throws IOException {
        //1. 创建流对象
        DataOutputStream os = new DataOutputStream(new FileOutputStream(dest));
        //2. 写入数据
        os.writeInt(10);
        os.writeChar('a');
        os.writeChar('b');
        os.writeDouble(12.83);
        //3. 关闭流
        os.close();
    }

    // 从文件中读取 java基本数据类型，要和写入的顺序保持一致
    private static void read(String src) throws IOException {
        //1. 创建数据流对象
        DataInputStream in = new DataInputStream(new FileInputStream(src));
        //2. 读取数据
        int a = in.readInt();
        char b = in.readChar();
        char c = in.readChar();
        double d = in.readDouble();
        //3. 关闭流
        in.close();
    }


}
