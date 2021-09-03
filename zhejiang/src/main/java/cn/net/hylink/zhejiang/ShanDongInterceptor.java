package cn.net.hylink.zhejiang;

import android.app.Application;
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

import cn.net.hylink.zhejiang.config.PropertiesOperation;
import cn.net.hylink.zhejiang.config.PropertiesUtil;
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
public class ShanDongInterceptor implements Interceptor {

    private static final String TAG = "ZheJiangInterceptor";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private static String APP_KEY = "";

    private static String SERVICE_KEY = "";

    private static final String ZHEJIANG_PATH = "/platform/redirect";

    private static final String CONFIG_FILE = Environment.getExternalStorageDirectory() + "/sdAppId.txt";

    private Application application;
    private PropertiesOperation propertiesOperation;

    public ShanDongInterceptor(Application application) {
        this.application = application;
        propertiesOperation = PropertiesUtil.getInstance()
                .getProperties(application, PropertiesUtil.CONFIG_PATH);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();

        if (path.contains(ZHEJIANG_PATH)) {
            if ((TextUtils.isEmpty(APP_KEY) || TextUtils.isEmpty(SERVICE_KEY) && propertiesOperation != null)) {
                try {
                    APP_KEY = propertiesOperation.readString("appKey", "");
                    SERVICE_KEY = propertiesOperation.readString("serviceKey", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //判断是否是浙江url
            Request.Builder builder = request.newBuilder()
                    .url("http://" + request.url().host() + ":" + request.url().port() + ZHEJIANG_PATH)
                    .addHeader("appKey", URLEncoder.encode(APP_KEY, "UTF-8"))
                    .addHeader("serviceKey", URLEncoder.encode(SERVICE_KEY, "UTF-8"));
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

}
