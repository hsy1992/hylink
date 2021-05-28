package cn.net.hylink.common.util;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import cn.net.hylink.common.bean.ConfigureBean;
import cn.net.hylink.common.constants.Constants;

/**
 * @ClassName PropertiesUtil
 * @Description 配置属性
 * @Author haosiyuan
 * @Date 2021/5/27 17:38
 * @Version 1.0
 */
public class PropertiesUtil {

    private static final String TAG = "PropertiesUtil";

    private static volatile PropertiesUtil instance;

    private static final String CONFIG_PATH = Environment.getExternalStorageDirectory() + "/config.properties";

    private Gson gson;

    private ConfigureBean configureBean;

    private Context context;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public static PropertiesUtil getInstance() {

        if (instance == null) {
            synchronized (PropertiesUtil.class) {
                if (instance == null) {
                    instance = new PropertiesUtil();
                }
            }
        }
        return instance;
    }

    private PropertiesUtil() {
        gson = new Gson();
    }

    /**
     * 配置属性
     */
    public void initData(Context context) {
        this.context = context;
        File file = new File(CONFIG_PATH);
        if (!file.exists()) {
            Log.i(TAG, "未找到配置文件");
            return;
        }
        try {
            ConfigureBean configureBean = new ConfigureBean();
            Properties props = new Properties();
            props.load(new FileInputStream(file));
            //基础配置
            if ("1".equals(props.getProperty(Constants.BaseConfigure.BASE_REGISTER, ""))) {
                configureBean.setBaseConfigBean(new ConfigureBean.BaseConfigBean(
                        props.getProperty(Constants.BaseConfigure.BASE_URL, ""),
                        props.getProperty(Constants.BaseConfigure.BASE_CAR_NO, ""),
                        props.getProperty(Constants.BaseConfigure.BASE_MQTT_URL, ""),
                        props.getProperty(Constants.BaseConfigure.BASE_ORGANIZATION_CODE, ""),
                        props.getProperty(Constants.BaseConfigure.BASE_ORGANIZATION_NAME, "")
                ));
            }
            //上传
            if ("1".equals(props.getProperty(Constants.BaseConfigure.BASE_REGISTER, ""))) {
                //Ftp
                configureBean.setFtpBean(new ConfigureBean.FtpBean(
                        props.getProperty(Constants.FtpConfigure.FTP_IP , ""),
                        Integer.parseInt(props.getProperty(Constants.FtpConfigure.FTP_PORT, "")),
                        props.getProperty(Constants.FtpConfigure.FTP_USER, ""),
                        props.getProperty(Constants.FtpConfigure.FTP_PASSWORD, "")
                ));
            }
            //云台配置
            String cloudIp = props.getProperty(Constants.CloudConfigure.CLOUD_IP, "");
            if (!TextUtils.isEmpty(cloudIp)) {
                configureBean.setCloud(new ConfigureBean.CloudBean(
                        cloudIp,
                        Integer.parseInt(props.getProperty(Constants.CloudConfigure.CLOUD_PORT, "")),
                        props.getProperty(Constants.CloudConfigure.CLOUD_USER, ""),
                        props.getProperty(Constants.CloudConfigure.CLOUD_PASSWORD, ""),
                        props.getProperty(Constants.CloudConfigure.CLOUD_RTSP, ""),
                        props.getProperty(Constants.CloudConfigure.CLOUD_CODE, ""),
                        Integer.parseInt(props.getProperty(Constants.CloudConfigure.CLOUD_TYPE, ""))
                ));
            }
            //NVR配置
            String nvrIp = props.getProperty(Constants.NvrConfigure.NVR_IP, "");
            if (!TextUtils.isEmpty(nvrIp)) {
                configureBean.setNvrBean(new ConfigureBean.NvrBean(
                        nvrIp,
                        Integer.parseInt(props.getProperty(Constants.NvrConfigure.NVR_PORT, "")),
                        props.getProperty(Constants.NvrConfigure.NVR_USER, ""),
                        props.getProperty(Constants.NvrConfigure.NVR_PASSWORD, "")
                ));
            }
            // 抓拍机配置 0 redis 1 海康1803
            String snapType = props.getProperty(Constants.SNAP_TYPE, "");


        } catch (Exception e) {
            e.printStackTrace();
            toast("配置文件信息错误请检查");
        }
    }

    /**
     * 主线程提示
     * @param message
     */
    private void toast(final String message) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
