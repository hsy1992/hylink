package cn.net.endless.hljtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import cn.net.hylink.epsprint.EspPrinterManager;
import cn.net.hylink.epsprint.IPrinterStateListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        String params = "{\"name\", \"haosiyuan\"}";
        //http://20.115.11.145:9000/zhjc/vehicle/findDeptTree
        //http://20.115.11.145:9000/zhjc/sign/getSignList
//        OkGo.<String>post( "http://20.115.11.145:9000/zhjc/sign/getSignList").headers("Authorization", "token")
//                .upJson("{\"carno\":\"蒙C0305\",\"imei\":\"R624422009130017\",\"lat\":0.0,\"lon\":0.0,\"sign_identification\":\"qd\"}")
//                .execute(new StringCallback() {
//
//            @Override
//            public void onSuccess(Response<String> response) {
//                Log.e("test", response.body());
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                Log.e("test", "onError");
//            }
//        });
        EspPrinterManager.getInstance().print("/sdcard/治安调节协议书_1615906146759.jpg", new IPrinterStateListener() {
            @Override
            public void printerFail(int state, String message) {
                Log.e("test", message);
            }

            @Override
            public void printerSuccess() {
                Log.e("test", "printerSuccess");
            }
        });
    }
}
