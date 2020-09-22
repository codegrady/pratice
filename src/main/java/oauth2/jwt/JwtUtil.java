package oauth2.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static void main(String[] args) throws InterruptedException{
        String token = createToken("18810227902");
        System.out.println("token = " + token);
        System.out.println();

//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxNTMxMTQzOTYwMSIsInFyY29kZSI6IiQkJE1KOzU0MDtDRDIzQjVEMzRFNEQ7OTQ3MTsyMDE3MjcwMCIsImV4cCI6MTU3MTk5NTY1M30.hKRnwdjorEeQHei1g0hLSnm9iR3zYYihBaV6lOpvZ1s";
//        Map<String, Claim> map = verifyToken(token);
//        map.forEach((k,v)->{
//            System.out.println(k+":"+v.as(String.class));
//        });
//
//        String srt = getAppUID(token);
//        System.out.println("srt = " + srt);

    }

    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj
     */
    public static final String SECRET = "huojian";
    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    public static String createToken(String src) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                // payload
                 // reserved Cliaims
                .withClaim("iss", "Rocket")  //签发者
                .withClaim("aud", "Urwork")  //接收方
                .withClaim("mobile", null == src ? null : src)
                .withClaim("realname", "gongyuu")
                .withClaim("meetingroomId", 19820275)
                .withClaim("startTime", "2018-09-06 14:30")
                .withClaim("endTime", "2018-09-06 16:30")
                .withClaim("meetingTheme", 1)
                .withIssuedAt(iatDate) // sign time 签发时间
                .withExpiresAt(expiresDate) // expire time 过期时间
                .sign(Algorithm.HMAC256(SECRET)); // signature 签名


        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            System.out.println( e.getMessage());
            System.out.println("-----------------------");
            System.out.println( e.getLocalizedMessage());
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return user_id
     */
    public static String getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim user_id_claim = claims.get("mobile");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return user_id_claim.asString();
    }


}