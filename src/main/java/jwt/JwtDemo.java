package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author gongyu
 * @Title: JwtDemo
 * @ProjectName practice
 * @Description: TODO
 * @date 2019/9/23
 */
public class JwtDemo {

    private static String SIGN = "gongyu-jwt-test";

    public static void main(String[] args) {

//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1Y29tbXVuZSIsImV4cCI6MTU2OTIzNjExMX0.7nMd_yf3VL9aAq07GUKIZ1k3BFJJgBz9ubPkJ_WPOKw";
//        int userId = JWT.require(Algorithm.HMAC256("ucommune-login")).build().verify(token).getClaim("userId").asInt();
//        System.out.println("userId = " + userId);

        System.out.println(createToken());
    }

    static String createToken(){
        String json ="{\"capSeq\":\"2411911012231572766103004\",\"capSysType\":\"ALIPAY\"," +
                "\"createTime\":1574899200000,\"discAmount\":0.00,\"loginAccount\":\"iU5NpBk3\"," +
                "\"mainOrderCode\":\"2011911012231436910696001\",\"mobile\":\"15918837235\"," +
                "\"note\":\"dff\",\"orderStatus\":\"PAYMENT_SUCCEED\",\"payAmount\":299.00," +
                "\"payOper\":\"PAY\",\"payTime\":1574812800000,\"supplierUserAccount\":\"2088102179829184\"," +
                "\"supplierUserName\":\"txk***@sandbox.com\",\"totalAmount\":299.00,\"tradeGoodsOrderRes\"" +
                ":[{\"goodsCount\":2,\"goodsName\":\"众烁智能营销宝\",\"goodsOrderCode\":\"2211911012231436910696003\",\"goodsPrice\":200.00," +
                "\"skuName\":\"众享套餐\",\"skuPropsStr\":\"优鲜集APPbanner投放1周或社区大屏一块1个月\",\"totalAmount\":400.00},{\"goodsCount\":1,\"goodsName\":" +
                "\"众烁智能营销宝\",\"goodsOrderCode\":\"2211911012231436910696004\",\"goodsPrice\":2999.00,\"skuName\":\"霸屏套餐\",\"skuPropsStr\":\"优鲜集APPbanner投放1周或社区大屏一块1个月\",\"totalAmount\":2999.00}]}";
       return JWT.create().withClaim("Json",json).sign(Algorithm.HMAC256(SIGN));
    }
}
