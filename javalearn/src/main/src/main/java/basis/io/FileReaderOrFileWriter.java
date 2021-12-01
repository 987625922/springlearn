package basis.io;

import org.junit.Test;

import java.io.*;

/**
 * 字符流
 * 1字符=2字节
 * <p></p>
 * Reader和Writer都是基于2个字节的Unicode编码
 */
public class FileReaderOrFileWriter {

    @Test
    public void test() {
        try (FileReader reader = new FileReader("d://log.txt")) {
            int len = reader.read();
            while (len != -1) {
                System.out.println(len);
                len = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //一次拷贝一个字符
    private static void copyFile1(String src, String dest) {
        try (Reader reader = new FileReader(src);
             Writer writer = new FileWriter(dest)) {
            //拷贝数据
            int data = reader.read();
            while (data != -1) {
                writer.write(data);
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // 一次拷贝一个 字符数组
    private static void copyFile2(String src, String dest) {
        try (Reader reader = new FileReader(src);
             Writer writer = new FileWriter(dest);) {
            //拷贝数据
            char[] buffer = new char[2048];
            int len = reader.read(buffer);
            while (len != -1) {
                writer.write(buffer, 0, len);
                len = reader.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
