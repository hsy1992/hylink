package cn.net.endless.crash;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.net.hylink.common.util.ConfigUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import xcrash.ICrashCallback;
import xcrash.TombstoneManager;
import xcrash.TombstoneParser;
import xcrash.XCrash;

/**
 * @ClassName CrashUtil
 * @Description crash 收集
 * @Author haosiyuan
 * @Date 2021/6/28 16:44
 * @Version 1.0
 */
public class CrashUtil {

    private final String TAG = "CrashUtil";

    private static class SingleHolder {
        private static CrashUtil Instance = new CrashUtil();
    }

    public static CrashUtil getInstance() {
        return SingleHolder.Instance;
    }

    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient okHttpClient;

    private ExecutorService executors = Executors.newSingleThreadExecutor();

    private CrashUtil() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public void init(Application application) {

        ICrashCallback callback = (logPath, emergency) -> {
            Log.d(TAG, "log path: " + (logPath != null ? logPath : "(null)") + ", emergency: " + (emergency != null ? emergency : "(null)"));

            if (emergency != null) {
                sendThenDeleteCrashLog(logPath, emergency);
            }
        };

        XCrash.init(application, new XCrash.InitParameters()
                .setAppVersion(getVersionName(application))
                .setJavaRethrow(true)
                .setJavaLogCountMax(10)
                .setJavaDumpAllThreadsWhiteList(new String[]{"^main$", "^Binder:.*", ".*Finalizer.*"})
                .setJavaDumpAllThreadsCountMax(10)
                .setJavaCallback(callback)
                .setNativeRethrow(true)
                .setNativeLogCountMax(10)
                .setNativeDumpAllThreadsWhiteList(new String[]{"^xcrash\\.sample$", "^Signal Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"})
                .setNativeDumpAllThreadsCountMax(10)
                .setNativeCallback(callback)
                .setAnrRethrow(true)
                .setAnrLogCountMax(10)
                .setAnrCallback(callback)
                .setPlaceholderCountMax(3)
                .setPlaceholderSizeKb(512)
                .setLogDir(application.getExternalFilesDir("xcrash").toString())
                .setLogFileMaintainDelayMs(1000)
        );

        executors.execute(() -> {
            for(File file : TombstoneManager.getAllTombstones()) {
                Log.i(TAG, "异常文件地址:" + file.getAbsolutePath());
                sendThenDeleteCrashLog(file.getAbsolutePath(), null);
            }
        });
    }

    private void sendThenDeleteCrashLog(String logPath, String emergency) {

        if (!ConfigUtil.getInstance().getBaseConfigBean().isRegister() ||
                TextUtils.isEmpty(ConfigUtil.getInstance().getBaseConfigBean().getUrl())) {
            return;
        }
        Map<String, String> map ;
        try {
            map = TombstoneParser.parse(logPath, emergency);
            String crashReport = new JSONObject(map).toString();
            JSONObject requestJson = new JSONObject();
            try {
                requestJson.put("text", crashReport);
                requestJson.put("mei", Build.SERIAL);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Request request = new Request.Builder()
                    .url(ConfigUtil.getInstance().getBaseConfigBean().getUrl() + "log/report")
                    .post(RequestBody.create(mediaType, requestJson.toString()))
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    TombstoneManager.deleteTombstone(logPath);
                }

                @Override
                public void onFailure(Call call, IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取版本号
     * @param application
     * @return
     */
    private String getVersionName(Application application) {
        PackageManager manager = application.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(application.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

}
