package cn.net.hylink.common.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import cn.net.hylink.common.bean.ConfigureBean;
import cn.net.hylink.common.config.BaseConfigBean;
import cn.net.hylink.common.constants.Constants;
import cn.net.hylink.common.properties.IPropertiesCommit;
import cn.net.hylink.common.properties.PropertiesOperation;
import cn.net.hylink.common.properties.PropertiesUtil;

/**
 * @ClassName ConfigUtil
 * @Description 获取配置文件
 * @Author haosiyuan
 * @Date 2021/5/28 14:42
 * @Version 1.0
 */
public class ConfigUtil {

    private static final String TAG = "ConfigUtil";

    private static volatile ConfigUtil instance;

    private final Gson gson;

    private Context mContext;

    private PropertiesOperation propertiesOperation;

    private BaseConfigBean baseConfigBean;

    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static ConfigUtil getInstance() {

        if (instance == null) {
            synchronized (ConfigUtil.class) {
                if (instance == null) {
                    instance = new ConfigUtil();
                }
            }
        }
        return instance;
    }

    private ConfigUtil() {
        gson = new Gson();
    }

    public void init(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        try {
            propertiesOperation = PropertiesUtil.getInstance().getProperties(PropertiesUtil.CONFIG_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            toast("配置文件信息错误请检查");
        }
    }

    /**
     * 获取基础配置
     *
     * @return
     */
    public BaseConfigBean getBaseConfigBean() {
        synchronized (ConfigUtil.this) {
            if (baseConfigBean == null) {
                //基础配置
                baseConfigBean = new BaseConfigBean(
                        propertiesOperation.readString(Constants.BaseConfigure.BASE_URL, ""),
                        propertiesOperation.readString(Constants.BaseConfigure.BASE_CAR_NO, ""),
                        propertiesOperation.readString(Constants.BaseConfigure.BASE_MQTT_URL, ""),
                        propertiesOperation.readString(Constants.BaseConfigure.BASE_ORGANIZATION_CODE, ""),
                        propertiesOperation.readString(Constants.BaseConfigure.BASE_ORGANIZATION_NAME, ""),
                        "1".equals(propertiesOperation.readString(Constants.BaseConfigure.BASE_REGISTER, ""))
                );
            }
        }
        return baseConfigBean;
    }

    public void saveBaseConfigBean(final BaseConfigBean baseConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_URL, baseConfigBean.getUrl());
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_CAR_NO, baseConfigBean.getCarNo());
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_MQTT_URL, baseConfigBean.getMqttUrl());
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_ORGANIZATION_CODE, baseConfigBean.getCode());
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_ORGANIZATION_NAME, baseConfigBean.getName());
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_REGISTER, "1");
            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.baseConfigBean = baseConfigBean;
                }
            });
        }
    }

    public void clearBaseConfigBean() {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_URL, "");
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_CAR_NO, "");
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_MQTT_URL, "");
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_ORGANIZATION_CODE, "");
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_ORGANIZATION_NAME, "");
            propertiesOperation.writeString(Constants.BaseConfigure.BASE_REGISTER, "0");
            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.baseConfigBean = new BaseConfigBean(
                            "","","","","",false
                    );;
                }
            });
        }
    }


    /**
     * 主线程提示
     *
     * @param message
     */
    private void toast(final String message) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
