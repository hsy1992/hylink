package cn.net.endless.hljtest;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.net.hylink.hljpolice.CredentialUtil;
import cn.net.hylink.hljpolice.interceptors.CredentialInterceptor;
import okhttp3.OkHttpClient;

/**
 * @author haosiyuan
 * @date 2020/9/18 3:56 PM
 * info :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CredentialUtil.getInstance().init(this, "config.json");
        OkGo.getInstance().init(this);
        try {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            loggingInterceptor.setColorLevel(Level.INFO);
            OkHttpClient builder = new OkHttpClient.Builder()
                    .addInterceptor(new CredentialInterceptor())
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .build();

            OkGo.getInstance().setOkHttpClient(builder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
