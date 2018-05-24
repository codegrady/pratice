package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.processing.OperationStatus;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;


public class FileUtil {
    private static final String ACCESS_KEY = "olkdxPeGLNbXwDr5yfeVaHidWcxcu3GNlMHtm-Ky";
    private static final String SECRET_KEY = "jnDHRJ2tFPBLVv0YSXEbB7fLyX4UwnzH6kvVlIuA";
    private static final String BUCKET ="bugliu";
    private static final String Pipeline = "liubo_test";
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final UploadManager uploadManager = new UploadManager( new Configuration(Zone.zone0()));
    private static String getUploadToken(String key){
        return AUTH.uploadToken(BUCKET,key);
    }

    public static String getUploadToken(){
        return AUTH.uploadToken(BUCKET);
    }
    public static void main(String[] args) {
        transferType();
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

    /**
     * 媒体转码
     */
    static void transferType(){
        String key = "test1234.MOV";
        StringMap putPolicy = new StringMap();
        //avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/600k/vcodec/libx264/s/480x360/autoscale/1/stripmeta/0|saveas/YnVnbGl1OnRlc3QxMjM0Lm1wNA==
        //指令
        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //新建一个OperationManager对象   61.49.250.90:8083/companyVideo/videoTransferStatus
        OperationManager operater = new OperationManager(AUTH, c);
        //设置要转码的空间和key，并且这个key在你空间中存在
        //设置转码操作参数
        String fops = "avthumb/mp4/ab/128k/ar/44100/acodec/libfaac/r/30/vb/600k/vcodec/libx264/s/480x360/autoscale/0/stripmeta/0";
        String saveMp4Entry = String.format("%s:test12345.mp4", BUCKET);
        String persistentNotifyUrl = "http://61.49.250.90:8083/companyVideo/videoTransferStatus";

        //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        String urlbase64 = UrlSafeBase64.encodeToString(saveMp4Entry);
        String pfops = fops + "|saveas/" + urlbase64;
        //设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("persistentPipeline", Pipeline).putNotEmpty("persistentNotifyUrl",persistentNotifyUrl).put("callbackBody", "{\"id\":12}");
        try {
//            String persistid = operater.pfop(BUCKET, key, pfops,params);
            String persistid = operater.pfop(BUCKET, key, pfops,Pipeline ,persistentNotifyUrl,true);
            //打印返回的persistid
            System.out.println(persistid);

            Thread.sleep(5000);
            OperationStatus operationStatus = operater.prefop(persistid);
            System.out.println(operationStatus.code+"  "+operationStatus.desc);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static void  getFileInfo(){
        String key="lhN9zeUqVroeWSUt5-N9_rhGZLO6";
        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);
        BucketManager bucketManager = new BucketManager(AUTH, c);
        try {
            FileInfo fileInfo = bucketManager.stat(BUCKET, key);
            System.out.println(fileInfo.endUser);
            System.out.println(fileInfo.key);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }
}
