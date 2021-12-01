package encryption;

import java.security.MessageDigest;

public class SHA1 {

        public String encode(String str) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(str.getBytes("utf-8"));
                byte[] digest = md.digest();
                return byteToHexString(digest);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        public static String byteToHexString(byte[] bytes) {
            return String.valueOf(Hex.encodeHex(bytes));
        }
        public static void main(String[] args) {
            SHA1 sha1 = new SHA1();
            String content = "测试test";
            System.out.println(sha1.encode(content));
        }

}
