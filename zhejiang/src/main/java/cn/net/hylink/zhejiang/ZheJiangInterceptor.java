package cn.net.hylink.zhejiang;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
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

    private static final String APP_KEY = "fb96bcb8ac6336a5a15a2dec7b7f3931";

    private static final String SERVICE_KEY = "fb96bcb8ac6336a5a15a2dec7b7f3931";

    private static final String ZHEJIANG_PATH = "/platform/redirect";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();

        if (path.contains(ZHEJIANG_PATH)) {
            //判断是否是浙江url
            Request.Builder builder = request.newBuilder().addHeader("appKey", URLEncoder.encode(APP_KEY, "UTF-8"))
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
