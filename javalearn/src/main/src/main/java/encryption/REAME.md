# 加密技术

1. ##### MD5

   ##### 消息摘要算法

   ```
    String plainText = "Hello , world !";  
       MessageDigest md5 = MessageDigest.getInstance("md5");  
       //将其中的每个字节转成十六进制字符串：byte类型的数据最高位是符号位，通过和0xff进行与操作，转换为int类型的正整数。 
       byte[] cipherData = md5.digest(plainText.getBytes());  
       StringBuilder builder = new StringBuilder();  
       for(byte cipher : cipherData) {  
       	//如果该正数小于16(长度为1个字符)，前面拼接0占位：确保最后生成的是32位字符串。 
           String toHexStr = Integer.toHexString(cipher & 0xff);  
           builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);  
       }  
       System.out.println(builder.toString());  
       //c0bb4f54f1d8b14caf6fe1069e5f93ad
   ```

2. #### Base64

   ##### 使用BASE64算法通常用作对二进制数据进行加密，加密之后的数据不易被肉眼识别。JDK提供了对BASE64的标准支持，每隔76个字符进行换行\r\n，并且包含+、=、/等特殊字符不适合作为url参数传递。因此通常都会使用Commons Codec来进行BASE64的加密和解密。

   ```
   BASE64Encoder encoder = new BASE64Encoder();  
       BASE64Decoder decoder = new BASE64Decoder();  
       String plainText = "Hello , world !";  
       String cipherText = encoder.encode(plainText.getBytes());  
       System.out.println("cipherText : " + cipherText);  
       //cipherText : SGVsbG8gLCB3b3JsZCAh  
       System.out.println("plainText : " +   
           new String(decoder.decodeBuffer(cipherText)));  
   ```

3. ##### DES

   ##### 数据加密标准算法(Data Encryption Standard)，和BASE64最明显的区别就是有一个工作密钥，该密钥既用于加密、也用于解密，并且要求密钥是一个长度至少大于8位的字符串。

   ```
   String plainText = "Hello , world !";  
       String key = "12345678";    //要求key至少长度为8个字符  
         
       SecureRandom random = new SecureRandom();  
       //生成秘钥
       DESKeySpec keySpec = new DESKeySpec(key.getBytes());  
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("des");  
       SecretKey secretKey = keyFactory.generateSecret(keySpec);  
       //进行加密操作
       Cipher cipher = Cipher.getInstance("des");  
       cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);  
       byte[] cipherData = cipher.doFinal(plainText.getBytes());  
       //为了便于观察生成的加密数据，使用BASE64再次加密
       System.out.println("cipherText : " + new BASE64Encoder().encode(cipherData));  
       //PtRYi3sp7TOR69UrKEIicA==  
       //解密
       cipher.init(Cipher.DECRYPT_MODE, secretKey, random);  
       byte[] plainData = cipher.doFinal(cipherData);  
       System.out.println("plainText : " + new String(plainData));  
   ```

4. ##### RSA

   #####  RSA算法是非对称加密算法的典型代表，既能加密、又能解密。

   #####  服务器发送数据给客户端时使用私钥（private key）进行加密，并且使用加密之后的数据和私钥生成数字签名（digital signature）并发送给客户端。客户端接收到服务器发送的数据会使用公钥（public key）对数据来进行解密，并且根据加密数据和公钥验证数字签名的有效性，防止加密数据在传输过程中被第三方进行了修改。

   #####  客户端发送数据给服务器时使用公钥进行加密，服务器接收到加密数据之后使用私钥进行解密。

   ```
     String plainText = "Hello , world !";  
       //创建密钥对KeyPair：
       KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");  
       keyPairGenerator.initialize(1024);  ////密钥长度推荐为1024位.  
       KeyPair keyPair = keyPairGenerator.generateKeyPair();  
       //获取公钥/私钥：
       PublicKey publicKey = keyPair.getPublic();  
       PrivateKey privateKey = keyPair.getPrivate();  
       //服务器数据使用私钥加密：
       Cipher cipher = Cipher.getInstance("rsa");  
       SecureRandom random = new SecureRandom();    
       cipher.init(Cipher.ENCRYPT_MODE, privateKey, random);  
       byte[] cipherData = cipher.doFinal(plainText.getBytes());  
       //base64再加密，让字符串清晰
       System.out.println("cipherText : " + new BASE64Encoder().encode(cipherData));  
       //gDsJxZM98U2GzHUtUTyZ/Ir/NXqRWKUJkl6olrLYCZHY3RnlF3olkWPZ35Dwz9BMRqaTL3oPuyVq  
       //sehvHExxj9RyrWpIYnYLBSURB1KVUSLMsd/ONFOD0fnJoGtIk+T/+3yybVL8M+RI+HzbE/jdYa/+  
       //yQ+vHwHqXhuzZ/N8iNg=  
     	// 用户使用公钥解密
       cipher.init(Cipher.DECRYPT_MODE, publicKey, random);  
       byte[] plainData = cipher.doFinal(cipherData);  
       System.out.println("plainText : " + new String(plainData));  
       //Hello , world !  
       //服务器根据私钥和加密数据生成数字签名：
       Signature signature  = Signature.getInstance("MD5withRSA");  
       signature.initSign(privateKey);  
       signature.update(cipherData);  
       byte[] signData = signature.sign();  
       System.out.println("signature : " + new BASE64Encoder().encode(signData));  
       //ADfoeKQn6eEHgLF8ETMXan3TfFO03R5u+cQEWtAQ2lRblLZw1DpzTlJJt1RXjU451I84v3297LhR  
       //co64p6Sq3kVt84wnRsQw5mucZnY+jRZNdXpcbwh2qsh8287NM2hxWqp4OOCf/+vKKXZ3pbJMNT/4  
       ///t9ewo+KYCWKOgvu5QQ=  
     	//用户根据公钥、加密数据验证数据是否被修改过：
       signature.initVerify(publicKey);  
       signature.update(cipherData);  
       boolean status = signature.verify(signData);  
       System.out.println("status : " + status);  
   ```

   

