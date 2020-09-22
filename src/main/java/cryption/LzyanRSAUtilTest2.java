package cryption;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

/**
 *
 * RSA+Base64加密解密工具类 RSA 一般用在数据传输过程中的加密和解密 先用RSA加密后是字节数组 再用BASE64加密 成字符串进行传输 测试
 * RSA1024产生的公钥字节长度在160-170之间 私钥长度在630-640之间 经过base64加密后长度 公钥字节产度在210-220之间
 * 私钥长度在840-850之间 所以数据库设计时如果存公钥长度设计varchar(256) 私钥长度varchar(1024)
 *
 */
public abstract class LzyanRSAUtilTest2 {
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "rsa_public_key";
    private static final String PRIVATE_KEY = "rsa_private_key";
    private static final String ENCODING = "UTF-8";

    // === Testing ===
    public static void main(String[] args) throws Exception {
        String pk = Base64.encodeBase64String(getPublicKeyPk1());
        String key = encryptByPublicKey("@sangfor123", pk);
        String s = HexConvertUtil.bin2hex(key);
        System.out.println(s);
    }

    public static byte[] getPublicKeyPk1()throws Exception{
        PemReader r = new PemReader(new InputStreamReader(new FileInputStream(String.valueOf(Paths.get("src/main/resources/", "publicKey.pem")))));
        PemObject pemObject = r.readPemObject();
        byte[] encodedKey = pemObject.getContent();
        System.out.println("encodedKey.length = " + encodedKey.length);


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
        return  primitive.getEncoded();
    }
    /**
     *
     * 加密
     * 用公钥加密
     * @param content
     * @param base64PublicKeyStr
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String content, String base64PublicKeyStr) throws Exception {
        byte[] inputBytes = content.getBytes(ENCODING);
        byte[] outputBytes = encryptByPublicKey(inputBytes, base64PublicKeyStr);
        return Base64.encodeBase64String(outputBytes);
    }

    /**
     *
     * 加密
     *
     * 用私钥加密
     * @param content
     * @param base64PrivateKeyStr
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String content, String base64PrivateKeyStr) throws Exception {
        byte[] inputBytes = content.getBytes(ENCODING);
        byte[] outputBytes = encryptByPrivateKey(inputBytes, base64PrivateKeyStr);
        return Base64.encodeBase64String(outputBytes);
    }

    /**
     *
     * 解密
     * 用公钥解密
     * @param content
     * @param base64PublicKeyStr
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String content, String base64PublicKeyStr) throws Exception {
        byte[] inputBytes = Base64.decodeBase64(content);
        byte[] outputBytes = decryptByPublicKey(inputBytes, base64PublicKeyStr);
        return new String(outputBytes, ENCODING);
    }

    /**
     *
     * 解密
     * 用私钥解密
     * @param content
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String content, String base64PrivateKeyStr) throws Exception {
        byte[] inputBytes = Base64.decodeBase64(content);
        byte[] outputBytes = decryptByPrivateKey(inputBytes, base64PrivateKeyStr);
        return new String(outputBytes, ENCODING);

    }

    /**
     *
     * 加密
     * 用公钥加密
     * @param content
     * @param base64PublicKeyStr
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] content, String base64PublicKeyStr) throws Exception {
        // 对公钥解密
        byte[] publicKeyBytes = Base64.decodeBase64(base64PublicKeyStr);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(content);

    }

    /**
     *
     * 加密
     * 用私钥加密
     * @param content
     * @param base64PrivateKeyStr
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] content, String base64PrivateKeyStr) throws Exception {
        // 对密钥解密
        byte[] privateKeyBytes = Base64.decodeBase64(base64PrivateKeyStr);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(content);

    }

    /**
     *
     * 解密
     * 用公钥解密
     * @param content
     * @param base64PublicKeyStr
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] content, String base64PublicKeyStr) throws Exception {
        // 对密钥解密
        byte[] publicKeyBytes = Base64.decodeBase64(base64PublicKeyStr);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(content);

    }

    /**
     * 解密
     * 用私钥解密
     * @param content
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] content, String base64PrivateKeyStr) throws Exception {
        // 对密钥解密
        byte[] privateKeyBytes = Base64.decodeBase64(base64PrivateKeyStr);

        // 取得私钥  for PKCS#1
//        RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(privateKeyBytes));
//        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
//        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
//        PrivateKey priKey= keyFactory.generatePrivate(rsaPrivKeySpec);


        // 取得私钥  for PKCS#8
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);



        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher.doFinal(content);
    }

    /**
     *
     * 取得私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getBase64PrivateKeyStr(Map keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     *
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getBase64PublicKeyStr(Map keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     *
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static Map initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024); // 初始化RSA1024安全些
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 公钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 私钥
        Map keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    private static void test() throws Exception {
        String pubKey =
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgDxka1U8SWI1vBY7pA1UiCVnr" +
                        "dtSgE+PpyTqe2YSEWCgkYQ2YsohZwsaUao7nya7QnBgRiPKEHgS/Eey+L9iwo32S" +
                        "n5fUwb0nJ1+JeXRA6JsDEKpONJojIbF2nfgHLWsNn4bzn5Webc6WZLx0GyLTQuZG" +
                        "adFVuVq2dQqEsrq7HwIDAQAB";

        String str = "123456";
        String content = encryptByPublicKey(str, pubKey);
        System.out.println(content);

        String priKey_pkcs1 = "MIICXQIBAAKBgQDgDxka1U8SWI1vBY7pA1UiCVnrdtSgE+PpyTqe2YSEWCgkYQ2YsohZwsaUao7nya7QnBgRiPKEHgS/Eey+L9iwo32Sn5fUwb0nJ1+JeXRA6JsDEKpONJojIbF2nfgHLWsNn4bzn5Webc6WZLx0GyLTQuZGadFVuVq2dQqEsrq7HwIDAQABAoGAYtaGLo4WWXNywJzlE+kCbwdNAU/kL9FWYtT/5P7zNCZnXtTpWIi5GU+QpfvzmlAfq6qP+3w77wgG8/qGQsd8gGu3mydi0ImmD9sJdhhsJuWZhCMM+qmvSmvG/gvIr+bdEmPhpCQpa3BLveUkFDA/OnwfTVL6ruwZayMzuToB6WECQQD63Gx9DZVhYoSxR7qSmiGf/TjfOJusTcrmc27Z5X5MS36a3Ux9Z+c9EaYFldZ3cPzP521ugNVvZdovKqFKIcQ5AkEA5KYeKBVlkrLaamSEu5WAX3DqJ6iDRqEjzMoVad5B1I7kJHO+NijUxNHaWaSqLOHuk37X+EAjSTozzwOkKwbqFwJBAM1NhhAWBNHtcdEwddWzBJ/N+jRdPLIX/Fz7zZXQRruj8VpGkGn1lf6ZqfjaNuoLcyunKB0OnR6NCbIePl/QIKkCQGgEQjfN9BVWlBJOhCuqCWphvcQo3v+kktq5HCC7YYtHLfZ/SQrubEzVgtXBGUGtzpD+5VUkKGlJtwP4Dhkc3iUCQQCwiFKuQe/OdlkYk1L4mb0H0fzy+/6mYxyUqpTXUw/6BVDOyowvzieh9oh2ZhnQS7YPBWz5ZXzwUH4YVwGqxiwA";
        String priKey_pkcs8_openssl = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOAPGRrVTxJYjW8FjukDVSIJWet21KAT4+nJOp7ZhIRYKCRhDZiyiFnCxpRqjufJrtCcGBGI8oQeBL8R7L4v2LCjfZKfl9TBvScnX4l5dEDomwMQqk40miMhsXad+Actaw2fhvOflZ5tzpZkvHQbItNC5kZp0VW5WrZ1CoSyursfAgMBAAECgYBi1oYujhZZc3LAnOUT6QJvB00BT+Qv0VZi1P/k/vM0Jmde1OlYiLkZT5Cl+/OaUB+rqo/7fDvvCAbz+oZCx3yAa7ebJ2LQiaYP2wl2GGwm5ZmEIwz6qa9Ka8b+C8iv5t0SY+GkJClrcEu95SQUMD86fB9NUvqu7BlrIzO5OgHpYQJBAPrcbH0NlWFihLFHupKaIZ/9ON84m6xNyuZzbtnlfkxLfprdTH1n5z0RpgWV1ndw/M/nbW6A1W9l2i8qoUohxDkCQQDkph4oFWWSstpqZIS7lYBfcOonqINGoSPMyhVp3kHUjuQkc742KNTE0dpZpKos4e6Tftf4QCNJOjPPA6QrBuoXAkEAzU2GEBYE0e1x0TB11bMEn836NF08shf8XPvNldBGu6PxWkaQafWV/pmp+No26gtzK6coHQ6dHo0Jsh4+X9AgqQJAaARCN830FVaUEk6EK6oJamG9xCje/6SS2rkcILthi0ct9n9JCu5sTNWC1cEZQa3OkP7lVSQoaUm3A/gOGRzeJQJBALCIUq5B7852WRiTUviZvQfR/PL7/qZjHJSqlNdTD/oFUM7KjC/OJ6H2iHZmGdBLtg8FbPllfPBQfhhXAarGLAA=";
        String priKey_pkcs8_new = "MIICdwIBADANBgsqhkiG9w0BDAoBAgSCAmEwggJdAgEAAoGBAOAPGRrVTxJYjW8FjukDVSIJWet21KAT4+nJOp7ZhIRYKCRhDZiyiFnCxpRqjufJrtCcGBGI8oQeBL8R7L4v2LCjfZKfl9TBvScnX4l5dEDomwMQqk40miMhsXad+Actaw2fhvOflZ5tzpZkvHQbItNC5kZp0VW5WrZ1CoSyursfAgMBAAECgYBi1oYujhZZc3LAnOUT6QJvB00BT+Qv0VZi1P/k/vM0Jmde1OlYiLkZT5Cl+/OaUB+rqo/7fDvvCAbz+oZCx3yAa7ebJ2LQiaYP2wl2GGwm5ZmEIwz6qa9Ka8b+C8iv5t0SY+GkJClrcEu95SQUMD86fB9NUvqu7BlrIzO5OgHpYQJBAPrcbH0NlWFihLFHupKaIZ/9ON84m6xNyuZzbtnlfkxLfprdTH1n5z0RpgWV1ndw/M/nbW6A1W9l2i8qoUohxDkCQQDkph4oFWWSstpqZIS7lYBfcOonqINGoSPMyhVp3kHUjuQkc742KNTE0dpZpKos4e6Tftf4QCNJOjPPA6QrBuoXAkEAzU2GEBYE0e1x0TB11bMEn836NF08shf8XPvNldBGu6PxWkaQafWV/pmp+No26gtzK6coHQ6dHo0Jsh4+X9AgqQJAaARCN830FVaUEk6EK6oJamG9xCje/6SS2rkcILthi0ct9n9JCu5sTNWC1cEZQa3OkP7lVSQoaUm3A/gOGRzeJQJBALCIUq5B7852WRiTUviZvQfR/PL7/qZjHJSqlNdTD/oFUM7KjC/OJ6H2iHZmGdBLtg8FbPllfPBQfhhXAarGLAA=";
        String priKey = priKey_pkcs8_new;

        String output = decryptByPrivateKey(content, priKey);
        System.out.println(output);
    }



}
