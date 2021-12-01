package encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * DES也是一种对称加密算法，但是在安全性、效率和灵活性上比AES略差，但是也能保证安全
 * 加密算法
 */
public class DES {
    /**
     * 将传入的明文转换为密文
     *
     * @param str
     * @param pwd
     * @return
     */
    public String encode(String str, String pwd) {
        byte[] result = null;
        try {
            DESKeySpec keySpec = new DESKeySpec(pwd.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = str.getBytes();
            result = cipher.doFinal(byteContent);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return parseByte2HexStr(result);
    }

    /**
     * 将传入的密文转换为明文
     *
     * @param str
     * @param pwd
     * @return
     */
    public String decode(String str, String pwd) {
        byte[] result = null;
        byte[] content = parseHexStr2Byte(str);
        try {
            DESKeySpec keySpec = new DESKeySpec(pwd.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(content);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new String(result);
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        DES des = new DES();
        String content = "测试test";
        // DES的密钥长度必须是8位(小于8位则会报错，8位之后对加密结果不会产生影响)
        String pwd = "javayznb";
        // 加密
        System.out.println("加密前：" + content);
        String encodeResultStr = des.encode(content, pwd);
        System.out.println("加密后：" + encodeResultStr);
        //解密
        String decodeResultStr = des.decode(encodeResultStr, pwd);
        System.out.println("解密后：" + decodeResultStr);
    }

}
