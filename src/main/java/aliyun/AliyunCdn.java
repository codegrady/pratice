package aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cdn.model.v20141111.SetReqAuthConfigRequest;
import com.aliyuncs.cdn.model.v20141111.SetReqAuthConfigResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;
import java.util.Date;

public class AliyunCdn {
    private static String endpoint = "oss-cn-qingdao.aliyuncs.com";
    private static String accessKeyId = "LTAIkXjZ00PNOJN2";
    private static String accessKeySecret = "R3QvY42nvVkaVpdnIDvWoiqhQ0n9nO";
    public static String getAuthUrl(String url){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SetReqAuthConfigRequest reqAuthConfigRequest = new SetReqAuthConfigRequest();
        reqAuthConfigRequest.setDomainName(url);

        reqAuthConfigRequest.setAuthType("type_c");
        reqAuthConfigRequest.setKey1("ucommunernd");
        reqAuthConfigRequest.setTimeOut("6000");
        SetReqAuthConfigResponse reqAuthConfigResponse ;
        try {
            reqAuthConfigResponse = client.getAcsResponse(reqAuthConfigRequest);
            String a= reqAuthConfigResponse.getRequestId();
            System.out.println("a = " + a);
        }catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        //https://res.urwork.cn/de0d9c02ffccfb629ed3e6d84deaa5ef/5BBEE3F2/test.mp3
        //c4e8dac7a855b7f88b54b4927fb2a7ba
        String url = "https://res.urwork.cn/";
        String file = "/test.mp3";
        String key = "ucommunernd";
        Long now = Instant.now().getEpochSecond();
        System.out.println("now = " + now);
        String hex = Long.toHexString(now+1).toUpperCase();
        String md5hash = DigestUtils.md5Hex(key+file+hex);
        System.out.println("md5hash = " + md5hash);
        System.out.println("hex = " + hex);
        System.out.println(url+md5hash+"/"+hex+file);


    }
}
