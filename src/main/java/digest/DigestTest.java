package digest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author gongyu
 * @Title: DigestTest
 * @ProjectName practice
 * @Description: TODO
 * @date 2019-04-03
 */
public class DigestTest {

    public static void main(String[] args) {
        String str = "18810227902";
        String salt = "LG32J92h3wT1OsR8ahcsr9uQO6w96qfK";
        String s = md5(str, salt);
        System.out.println(s);
        System.out.println(verify(str,salt,s));
    }

    static String md5(String str,String salt){
        return DigestUtils.md5Hex(str+salt).toLowerCase();
    }

    static boolean verify(String text,String salt,String sign){
        return sign.equals(md5(text,salt));
    }
}
