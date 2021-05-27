package cn.net.hylink.common.util;

import android.os.Environment;
import android.util.Log;

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
    public void initData() {

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

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
