package aliyun;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import java.io.*;

public class AliyunUtil {
    private static final String ACCESSKEY = "LTAI55555kXjZ00PNOJN2333";
    private static final String SECRET = "R3QvY42nvVk55555aVpdnIDvWoiqhQ0n9nO4444";
    private static final String ENDPOINT = "oss-cn-qingdao.aliyuncs.com";

    public static void main(String[] args) throws IOException {
        upload();
    }

    static void upload() throws IOException {
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEY, SECRET);
        try {
            String bucketName = "urwork";
            String key = "video/796";

            String media = "D:\\796.mp4";
// http://oss.urwork.cn/video/796?x-oss-process=video/snapshot,t_100,f_jpg,w_670,h_376,m_fast
            InputStream inputStream = new FileInputStream(media);
//            File file = new File(media);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            String suffix = media.split("\\.")[1];
            key+=suffix;
            PutObjectResult result =  ossClient.putObject(bucketName, key, new ByteArrayInputStream(bytes));



            System.out.println("Etag = " + result.getETag());
            System.out.println("response = " + result.getResponse());
            System.out.println("getClientCRC = " + result.getClientCRC());
            System.out.println("getServerCRC = " + result.getServerCRC());
            System.out.println("getRequestId = " + result.getRequestId());
            ossClient.shutdown();

        }  catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }

    }

    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();

        reader.close();
    }

    private static void uploadPoint() {
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEY, SECRET);
        String bucketName = "urwork";
        String media = "D:\\test.mp4";
        String key = "test2.mp4";
        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
            // 待上传的本地文件
            uploadFileRequest.setUploadFile(media);
            // 设置并发下载数，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认100KB
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // 开启断点续传，默认关闭
            uploadFileRequest.setEnableCheckpoint(true);

            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

            CompleteMultipartUploadResult multipartUploadResult =
                    uploadResult.getMultipartUploadResult();
            System.out.println(multipartUploadResult.getETag());

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
