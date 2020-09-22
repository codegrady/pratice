package cryption;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author gongyu
 * @Title: Wechat
 * @ProjectName practice
 * @Description: TODO
 * @date 2018-12-21
 */
public class Wechat {

    private static String decrypt(String appId,String sessionKey,String ecnrptedData,String iv) throws UnsupportedEncodingException {

        String decodeStr =  new String(Base64.getDecoder().decode(sessionKey),"utf-8");
         ecnrptedData = new String(Base64.getDecoder().decode(ecnrptedData),"utf-8");

//        Cipher cipher = Cipher.getInstance()


        return "";
    }
}
