package basis.io;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流
 * InputStream字节输入流
 * OutputStream字节输出流
 * 用于以字节的形式读取和写入数据
 * <p></p>
 * 1字节=8位(1 byte = 8bit)(0 - 255)
 * 1字=2字节(1 word = 2 byte)
 * 1 KB = 1024 Bytebyte
 * <p></p>
 * UTF-8对数字和字母就使用一个字节，而对汉字就使用3个字节
 * 写在.java源代码中的中文字符采用的编码方式是UNICODE
 */
public class StreamTest {

    @Test
    public void test() throws IOException {
        outPutStreamTest();
//        inputStreamTest();
    }

    //已字节流读取文件
    private void inputStreamTest() {
        File file = new File("d://log.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] all = new byte[(int) file.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outPutStreamTest() {
        File file = new File("d://log.txt");
        String str = "中文的汉字！";
        byte data[] = str.getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
