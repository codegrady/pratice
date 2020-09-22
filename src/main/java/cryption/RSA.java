package cryption;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.lettuce.core.codec.Base16;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author gongyu
 * @Title: RSA
 * @ProjectName practice
 * @Description: TODO
 * @date 2019/12/26
 */
public class RSA {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    public static void main(String[] args) throws Exception {

        String plainText = "@sangfor123";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyPk1());
        byte[] enBytes = cipher.doFinal(plainText.getBytes());
        char[] encode = Base16.encode(enBytes, false);
        System.out.println("encode = " + encode.length);
        String str = new String(encode);
        System.out.println(str);

//        String s = HexConvertUtil.bin2hex(str);
//        System.out.println(s);
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//        byte[] input = "abc".getBytes();
//        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
//        SecureRandom random = new SecureRandom();
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
//
//        generator.initialize(256, random);
//
//        KeyPair pair = generator.generateKeyPair();
//        Key pubKey = pair.getPublic();
//        Key privKey = pair.getPrivate();
//
//
//        cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
//        byte[] cipherText = cipher.doFinal(input);
//        System.out.println("cipher: " + new String(cipherText,"utf-8"));
//
//        cipher.init(Cipher.DECRYPT_MODE, privKey);
//        byte[] plainText = cipher.doFinal(cipherText);
//        System.out.println("plain : " + new String(plainText));

    }

    public static void generatePubKeyAndPriKey() throws Exception {
        // RSA算法要求有一个可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();

        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        // 利用上面的随机数据源初始化这个KeyPairGenerator对象
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        // 生成密匙对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        // 公钥
        String publicKeyBase64 = Base64.encode(publicKeyBytes);
        System.out.println("publicKey = " + publicKeyBase64);
        // 私钥
        String privateKeyBase64 = Base64.encode(privateKeyBytes);
        System.out.println("privateKey = " + privateKeyBase64);

        String content = "My name is Grady";
        String sign = encrypt(content, publicKeyBase64, "UTF-8");
        System.out.println("sign = " + sign);

        String con = decrypt(sign, privateKeyBase64, "UTF-8");
        System.out.println("con = " + con);
    }

    /**
     * RSA签名
     *
     * @param content       待签名数据
     * @param privateKey    商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content        待签名数据
     * @param sign           签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset  编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));
            return signature.verify(Base64.decode(sign));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static byte[] Encrypt(String data, org.bouncycastle.asn1.pkcs.RSAPublicKey rsaPublicKey) throws Exception {


        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
        Key publicKey = new java.security.interfaces.RSAPublicKey() {
            @Override
            public BigInteger getPublicExponent() {
                return rsaPublicKey.getPublicExponent();
            }

            @Override
            public String getAlgorithm() {
                return "RSA";
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }

            @Override
            public BigInteger getModulus() {
                return rsaPublicKey.getModulus();
            }
        };
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 对数据分段
        byte[] b = data.getBytes();
        int inputLen = b.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(b, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        return out.toByteArray();
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKeystr
     * @param input_charset
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String publicKeystr,String input_charset) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeystr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes(input_charset).length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return Base64.encode(encryptedData);
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKeyStr
     * @param charset
     * @return
     * @throws Exception
     */
    public static String encryptWithPrivateKey(String data,String privateKeyStr,String charset) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        int inputLen = data.getBytes(charset).length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return Base64.encode(encryptedData);
    }

    /**
     * 解密
     *
     * @param content       密文
     * @param private_key   商户私钥
     * @param input_charset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);
        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }
        return new String(writer.toByteArray(), input_charset);
    }

    public static String decryptWithPublicKey(String content, String publicKey, String input_charset) throws Exception {
        KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(Base64.decode(publicKey));
        PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
        Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }
        return new String(writer.toByteArray(), input_charset);
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);


        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    public static PublicKey getPublicKey(String key)throws Exception{
        byte[] keyBytes;

        keyBytes = Base64.decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;
    }

    public static PublicKey getPublicKeyPk1()throws Exception{
        PemReader r = new PemReader(new InputStreamReader(new FileInputStream(String.valueOf(Paths.get("src/main/resources/", "publicKey.pem")))));
        PemObject pemObject = r.readPemObject();
        byte[] encodedKey = pemObject.getContent();
        System.out.println("encodedKey.length = " + encodedKey.length);


        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(new String(encodedKey));
//        byte[] keyb = Files.readAllBytes();
//        System.out.println("keyb = " + keyb.length);
//        byte[] keyBytes;
//
//        String key = "";
//        keyBytes = Base64.decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        ASN1Primitive primitive = spkInfo.parsePublicKey();
        byte[] publicKeyPKCS1 = primitive.getEncoded();
        return publicKey;
    }
}
