package cn.net.endless.hljtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        String params = "{\"name\", \"haosiyuan\"}";
        OkGo.<String>post( "http://www.baidu.com/endless").headers("Authorization", "token")
                .upJson(params).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                Log.e("test", response.body());
            }

            @Override
            public void onError(Response<String> response) {
                Log.e("test", "onError");
            }
        });

    }
}
