package cn.net.hylink.zhejiang;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @ClassName ZheJiangInterceptor
 * @Description 浙江拦截器
 * @Author haosiyuan
 * @Date 2021/5/28 9:47
 * @Version 1.0
 */
public class ZheJiangInterceptor implements Interceptor {

    private static final String TAG = "ZheJiangInterceptor";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private static String APP_KEY = "";

    private static final String ZHEJIANG_PATH = "/platform/redirect";

    private static final String CONFIG_FILE = Environment.getExternalStorageDirectory() + "/appId.txt";

    private static final String CONFIG_FILE_SYSTEM = "/system/etc/appId.txt";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();

        if (path.contains(ZHEJIANG_PATH)) {
            //判断是否是浙江url
            initAppKey();
            Request.Builder builder = request.newBuilder()
                    .url("http://" + request.url().host() + ":" + request.url().port() + ZHEJIANG_PATH)
                    .addHeader("appKey", URLEncoder.encode(APP_KEY, "UTF-8"))
                    .addHeader("serviceKey", URLEncoder.encode(APP_KEY, "UTF-8"));
            Buffer sink = new Buffer();
            request.body().writeTo(sink);
            String value = sink.readString(UTF_8);

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("serviceSuffix", path.replace(ZHEJIANG_PATH, ""));
                jsonObject.put("params", new JSONObject(value));
                Log.i(TAG, jsonObject.toString());
                request = builder.post(RequestBody.create(MEDIA_TYPE, jsonObject.toString())).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chain.proceed(request);
    }

    private void initAppKey() {

        if (TextUtils.isEmpty(APP_KEY)) {
            File file = new File(CONFIG_FILE);
            if (file.exists()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    byte[] bytes = new byte[(int) file.length()];
                    fileInputStream.read(bytes);
                    String content = new String(bytes);
                    APP_KEY = content;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

            File appIdFile = new File(CONFIG_FILE_SYSTEM);
            if (appIdFile.exists()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(appIdFile);
                    byte[] bytes = new byte[(int) appIdFile.length()];
                    fileInputStream.read(bytes);
                    String content = new String(bytes);
                    APP_KEY = content;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

            FileOutputStream fileOutputStream = null;
            try {
                boolean created = file.createNewFile();
                if (created) {
                    fileOutputStream = new FileOutputStream(file);
                    PrintStream ps = new PrintStream(fileOutputStream);
                    //默认萧山
                    ps.print("8316f9c1c3a4398391e48fae52881214");
                    APP_KEY = "8316f9c1c3a4398391e48fae52881214";
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
