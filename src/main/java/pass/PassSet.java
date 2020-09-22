package pass;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class PassSet {
    public static String getMySQLPassword(String plainText) throws UnsupportedEncodingException {
        byte[] utf8 = plainText.getBytes("UTF-8");
        byte[] test = DigestUtils.sha(DigestUtils.sha(utf8));
        return "*" + convertToHex(test).toUpperCase();
    }


    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String encryptPassword(String password,String username,String salt){
        String updateSalt = username + salt;
        byte[] encryptedPwd = DigestUtils.updateDigest(DigestUtils.getMd5Digest(), updateSalt).digest(password.getBytes());

        return DigestUtils.md5Hex(encryptedPwd);
    }

    public static String generateSalt(){
        return UUID.randomUUID().toString();
    }

    public static boolean isCorrectPwd(String correctPwd,String username,String salt,String inputPwd){
        String encryptPassword = encryptPassword(inputPwd,username,salt);
        boolean isCorrect = encryptPassword.equals(correctPwd);
        return isCorrect;
    }

    public static String initPassword(){
        return RandomStringUtils.randomNumeric(6);
    }

    public static void main(String[] args) {
//        String salt = generateSalt();
        String salt =  "e2d025b8-58c3-419a-9d8f-2ca285aeedc1";
        System.out.println("salt:" +salt);
        System.out.println(encryptPassword("123456789", "18621294892", salt));
//        System.out.println(isCorrectPwd("2d34cb9a76b2be9280832632359f44d7","13811574217",salt,"123456"));
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        try {
//            System.out.println(getMySQLPassword("Urwork_123!"));
//        } catch (UnsupportedEncodingException e) {
//        // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}