package qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


public class FileUtil {
    private static final String ACCESS_KEY = "9yAU7Ie07vT-OVHNpjV_doddMmO16jrwq7eTgl4o";
    private static final String SECRET_KEY = "E1pnvw2TD1lpF10R0P_olshFThgCmnLUa567sp76";
    private static final String BUCKET ="uwork";
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final UploadManager uploadManager = new UploadManager();
    private static String getUploadToken(String key){
        return AUTH.uploadToken(BUCKET,key);
    }

    public static String getUploadToken(){
        return AUTH.uploadToken(BUCKET);
    }
    public static void main(String[] args) {
        upload();
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
}
