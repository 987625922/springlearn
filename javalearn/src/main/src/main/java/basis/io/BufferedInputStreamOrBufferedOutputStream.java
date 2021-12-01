package basis.io;

import java.io.*;

/**
 * BufferedInputStream & BufferedOutputStream
 * 为另一个输入输出流流添加一些功能，即缓冲区的作用。
 * 在创建 BufferedInputStream & BufferedOutputStream 时，
 * 会创建一个内部缓冲区数组。
 */
public class BufferedInputStreamOrBufferedOutputStream {

    // 为文件字节流 添加缓冲区功能， 一次读写一个字节数据，但内部缓冲区数组已经填满
    private static void copyFile1(String src, String dest) throws IOException {
        //1. 创建流
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));


        //2. 读写数据
        int data = in.read();
        while (data != -1) {
            os.write(data);
            data = in.read();
        }

        //3. 关闭流
        in.close();
        os.close();
    }

    // 为文件字节流 添加缓冲区功能， 一次读写一个字节数组数据，但内部缓冲区数组已经填满
    private static void copyFile2(String src, String dest) throws IOException {
        //1. 创建流
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));

        //2. 读写数据
        byte[] buffer = new byte[2048];
        int len = in.read(buffer);
        while (len != -1) {
            os.write(buffer, 0, len);
            len = in.read(buffer);
        }
        //3. 关闭流
        in.close();
        os.close();
    }


}
