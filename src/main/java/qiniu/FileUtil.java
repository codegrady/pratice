package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;


public class FileUtil {
    private static final String ACCESS_KEY = "olkdxPeGLNbXwDr5yfeVaHidWcxcu3GNlMHtm-Ky";
    private static final String SECRET_KEY = "jnDHRJ2tFPBLVv0YSXEbB7fLyX4UwnzH6kvVlIuA";
    private static final String BUCKET ="bugliu";
    private static final String Pipeline = "liubo_test";
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final UploadManager uploadManager = new UploadManager();
    private static String getUploadToken(String key){
        return AUTH.uploadToken(BUCKET,key);
    }

    public static String getUploadToken(){
        return AUTH.uploadToken(BUCKET);
    }
    public static void main(String[] args) {
        uploadMedia();
    }

    static void upload(){
        //上传到七牛后保存的文件名
        String key = "234.png";
        //上传文件的路径
        String FilePath = "d:\\234.png";  //本地要上传文件路径
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUploadToken());
            //打印返回的信息 http://img.urwork.cn/FhHai3nwJSr6AKMfXLCZsP-VdH4H
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }


    static void uploadMedia(){
        String media = "D:\\test.mp4";
        String key = "test.mp4";
        try {
            //调用put方法上传
            Response res = uploadManager.put(media, key, getUploadToken());
            //打印返回的信息 http://img.urwork.cn/FhHai3nwJSr6AKMfXLCZsP-VdH4H
            System.out.println(res.bodyString());

            if(!res.isOK()) {
                return;
            }
            StringMap putPolicy = new StringMap();

            //指令
            String saveMp4Entry = String.format("%s:"+key, BUCKET);
            String vframeJpgFop = String.format("vframe/jpg/offset/1/w/50/h100/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
            putPolicy.put("persistentOps", vframeJpgFop);
//数据处理队列名称，必填
            putPolicy.put("persistentPipeline", Pipeline);
//数据处理完成结果通知地址
            putPolicy.put("persistentNotifyUrl", "http://api.example.com/qiniu/pfop/notify");
            long expireSeconds = 3600;
            String upToken = AUTH.uploadToken(BUCKET, key, expireSeconds, putPolicy);
            System.out.println(upToken);
        }catch (QiniuException e){
            System.out.println(e.response);
        }

    }
}
