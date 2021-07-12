package cn.net.endless.hljtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.reglink.common.ReglinkNativeService;
import com.reglink.common.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.net.hylink.common.config.BaseConfigBean;
import cn.net.hylink.common.config.CameraConfigBean;
import cn.net.hylink.common.config.CarConfigBean;
import cn.net.hylink.common.config.CloudConfigBean;
import cn.net.hylink.common.config.CommonConfigBean;
import cn.net.hylink.common.config.RedisConfigBean;
import cn.net.hylink.common.util.ConfigUtil;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        Log.e("test", "getBaseConfigBean:" + ConfigUtil.getInstance().getBaseConfigBean().toString());
        for (int i = 0; i < ConfigUtil.getInstance().getCameraListConfig().size(); i++) {
            Log.e("test", "CameraList:" + ConfigUtil.getInstance().getCameraListConfig().get(i).toString());
        }

        Log.e("test", ConfigUtil.getInstance().getCarConfig().toString());
        Log.e("test", ConfigUtil.getInstance().getCloudConfig().toString());
        Log.e("test", "getFtpConfig:" + ConfigUtil.getInstance().getFtpConfig().toString());
        Log.e("test", "getNvrConfig:" + ConfigUtil.getInstance().getNvrConfig().toString());
        Log.e("test", ConfigUtil.getInstance().getRedisConfig().toString());
        Log.e("test", "getSnapConfig:" + ConfigUtil.getInstance().getSnapConfig().toString());
    }

    public void write(View view) {
        ConfigUtil.getInstance().saveBaseConfigBean(new BaseConfigBean("http://", "黑A1234",
                "cide","黑龙江", "mqttt", true));

        ConfigUtil.getInstance().saveCarConfig(new CarConfigBean("192.168.1.15", "黑A1234"));

        ConfigUtil.getInstance().saveCloudConfig(new CloudConfigBean("192.168.1.11", 20, "haosiyuan",
                "haosiyuan","rtsp", "code", 2));

        ConfigUtil.getInstance().saveNvrConfig(new CommonConfigBean("192.168.1.111", 21,
                "haosiyuan1","haosiyuan1"));

        ConfigUtil.getInstance().saveUploadType(new CommonConfigBean("192.168.1.111", 21,
                "haosiyuan1","haosiyuan1"));

        ConfigUtil.getInstance().saveSnapConfig(new CommonConfigBean("192.168.1.111", 21,
                "haosiyuan1","haosiyuan1"));

        ConfigUtil.getInstance().saveRedisConfig(new RedisConfigBean("192.168.1.111", 21));

        List<CameraConfigBean> list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            list.add(new CameraConfigBean(i + 1, "z" + i, 1, "ip" + i, "rtsp" + i,
                    "code" + i, false));
        }
        ConfigUtil.getInstance().saveCameraList(list);

    }

    public void crash(View view) {
        throw new RuntimeException("报错了");
    }
}
