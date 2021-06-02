package cn.net.hylink.common.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.net.hylink.common.config.BaseConfigBean;
import cn.net.hylink.common.config.CameraConfigBean;
import cn.net.hylink.common.config.CarConfigBean;
import cn.net.hylink.common.config.CloudConfigBean;
import cn.net.hylink.common.config.CommonConfigBean;
import cn.net.hylink.common.config.RedisConfigBean;
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

    private AtomicBoolean isFtp;
    private CommonConfigBean CommonConfigBean;
    private CloudConfigBean cloudConfigBean;
    private CommonConfigBean nvrConfigBean;
    private RedisConfigBean redisConfigBean;
    private CommonConfigBean snapConfigBean;
    private CarConfigBean carConfigBean;
    private List<CameraConfigBean> cameraConfigList;

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
            if (propertiesOperation.getProps() == null) {
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
            if (propertiesOperation.getProps() == null) {
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
                            "", "", "", "", "", false
                    );
                }
            });
        }
    }

    /**
     * 是否是ftp 上传
     *
     * @return
     */
    public boolean isFtp() {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return false;
            }
            if (isFtp == null) {
                isFtp = new AtomicBoolean(propertiesOperation.readInt(Constants.UPLOAD_TYPE, 0) == 1);
            }
            return isFtp.get();
        }
    }

    public CommonConfigBean getFtpConfig() {
        synchronized (ConfigUtil.this) {
            if (CommonConfigBean == null) {
                //基础配置
                CommonConfigBean = new CommonConfigBean(
                        propertiesOperation.readString(Constants.FtpConfigure.FTP_IP, ""),
                        propertiesOperation.readInt(Constants.FtpConfigure.FTP_PORT, 0),
                        propertiesOperation.readString(Constants.FtpConfigure.FTP_USER, ""),
                        propertiesOperation.readString(Constants.FtpConfigure.FTP_PASSWORD, "")
                );
            }
        }
        return CommonConfigBean;
    }

    public void saveUploadType(final CommonConfigBean ftpBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            isFtp = new AtomicBoolean(ftpBean == null);

            propertiesOperation.writeString(Constants.FtpConfigure.FTP_IP, ftpBean == null ? "" : ftpBean.getIp());
            propertiesOperation.writeString(Constants.FtpConfigure.FTP_USER, ftpBean == null ? "" : ftpBean.getUser());
            propertiesOperation.writeString(Constants.FtpConfigure.FTP_PORT, ftpBean == null ? "" : String.valueOf(ftpBean.getPort()));
            propertiesOperation.writeString(Constants.FtpConfigure.FTP_PASSWORD, ftpBean == null ? "" : ftpBean.getPassword());
            propertiesOperation.writeString(Constants.UPLOAD_TYPE, ftpBean == null ? Constants.UploadType.HTTP :
                    Constants.UploadType.FTP);

            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.CommonConfigBean = ftpBean;
                }
            });
        }
    }

    /**
     * 获取云台配置
     *
     * @return
     */
    public CloudConfigBean getCloudConfig() {
        synchronized (ConfigUtil.this) {
            if (cloudConfigBean == null) {
                //基础配置
                cloudConfigBean = new CloudConfigBean(
                        propertiesOperation.readString(Constants.CloudConfigure.CLOUD_IP, ""),
                        propertiesOperation.readInt(Constants.CloudConfigure.CLOUD_PORT, 8000),
                        propertiesOperation.readString(Constants.CloudConfigure.CLOUD_USER, ""),
                        propertiesOperation.readString(Constants.CloudConfigure.CLOUD_PASSWORD, ""),
                        propertiesOperation.readString(Constants.CloudConfigure.CLOUD_RTSP, ""),
                        propertiesOperation.readString(Constants.CloudConfigure.CLOUD_CODE, ""),
                        propertiesOperation.readInt(Constants.CloudConfigure.CLOUD_TYPE, 2)
                );
            }
        }
        return cloudConfigBean;
    }

    public void saveCloudConfig(final CloudConfigBean cloudConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.CloudConfigure.CLOUD_IP, cloudConfigBean.getIp());
            propertiesOperation.writeInt(Constants.CloudConfigure.CLOUD_PORT, cloudConfigBean.getPort());
            propertiesOperation.writeString(Constants.CloudConfigure.CLOUD_USER, cloudConfigBean.getUser());
            propertiesOperation.writeString(Constants.CloudConfigure.CLOUD_PASSWORD, cloudConfigBean.getPassword());
            propertiesOperation.writeString(Constants.CloudConfigure.CLOUD_RTSP, cloudConfigBean.getRtsp());
            propertiesOperation.writeString(Constants.CloudConfigure.CLOUD_CODE, cloudConfigBean.getCode());
            propertiesOperation.writeInt(Constants.CloudConfigure.CLOUD_TYPE, cloudConfigBean.getType());
            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.cloudConfigBean = cloudConfigBean;
                }
            });
        }
    }

    /**
     * 获取nvr配置
     *
     * @return
     */
    public CommonConfigBean getNvrConfig() {
        synchronized (ConfigUtil.this) {
            if (nvrConfigBean == null) {
                //基础配置
                nvrConfigBean = new CommonConfigBean(
                        propertiesOperation.readString(Constants.NvrConfigure.NVR_IP, ""),
                        propertiesOperation.readInt(Constants.NvrConfigure.NVR_PORT, 8000),
                        propertiesOperation.readString(Constants.NvrConfigure.NVR_USER, ""),
                        propertiesOperation.readString(Constants.NvrConfigure.NVR_PASSWORD, "")
                );
            }
        }
        return nvrConfigBean;
    }

    public void saveNvrConfig(final CommonConfigBean nvrConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.NvrConfigure.NVR_IP, nvrConfigBean.getIp());
            propertiesOperation.writeInt(Constants.NvrConfigure.NVR_PORT, nvrConfigBean.getPort());
            propertiesOperation.writeString(Constants.NvrConfigure.NVR_USER, nvrConfigBean.getUser());
            propertiesOperation.writeString(Constants.NvrConfigure.NVR_PASSWORD, nvrConfigBean.getPassword());
            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.nvrConfigBean = nvrConfigBean;
                }
            });
        }
    }

    /**
     * redis
     *
     * @return
     */
    public CommonConfigBean getSnapConfig() {
        synchronized (ConfigUtil.this) {
            if (snapConfigBean == null) {
                //基础配置
                snapConfigBean = new CommonConfigBean(
                        propertiesOperation.readString(Constants.SnapConfigure.SNAP_IP, ""),
                        propertiesOperation.readInt(Constants.SnapConfigure.SNAP_PORT, 8000),
                        propertiesOperation.readString(Constants.SnapConfigure.SNAP_USER, ""),
                        propertiesOperation.readString(Constants.SnapConfigure.SNAP_PASSWORD, "")

                );
            }
        }
        return snapConfigBean;
    }

    public void saveSnapConfig(final CommonConfigBean snapConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.SnapConfigure.SNAP_IP, snapConfigBean.getIp());
            propertiesOperation.writeInt(Constants.SnapConfigure.SNAP_PORT, snapConfigBean.getPort());
            propertiesOperation.writeString(Constants.SnapConfigure.SNAP_USER, snapConfigBean.getUser());
            propertiesOperation.writeString(Constants.SnapConfigure.SNAP_PASSWORD, snapConfigBean.getPassword());

            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.snapConfigBean = snapConfigBean;
                }
            });
        }
    }

    /**
     * 抓拍配置
     * @return
     */
    public RedisConfigBean getRedisConfig() {
        synchronized (ConfigUtil.this) {
            if (redisConfigBean == null) {
                //基础配置
                redisConfigBean = new RedisConfigBean(
                        propertiesOperation.readString(Constants.RedisConfigure.REDIS_IP, ""),
                        propertiesOperation.readInt(Constants.RedisConfigure.REDIS_PORT, 6379)
                );
            }
        }
        return redisConfigBean;
    }

    public void saveRedisConfig(final RedisConfigBean redisConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.readString(Constants.RedisConfigure.REDIS_IP, redisConfigBean.getIp());
            propertiesOperation.readInt(Constants.RedisConfigure.REDIS_PORT, redisConfigBean.getPort());

            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.redisConfigBean = redisConfigBean;
                }
            });
        }
    }

    /**
     * 车内摄像头配置
     */
    public CarConfigBean getCarConfig() {
        synchronized (ConfigUtil.this) {
            if (carConfigBean == null) {
                //基础配置
                carConfigBean = new CarConfigBean(
                        propertiesOperation.readString(Constants.CarConfigure.CAR_IP, ""),
                        propertiesOperation.readString(Constants.CarConfigure.CAR_RTSP, "")
                );
            }
        }
        return carConfigBean;
    }

    public void saveCarConfig(final CarConfigBean carConfigBean) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }
            propertiesOperation.writeString(Constants.CarConfigure.CAR_IP, carConfigBean.getIp());
            propertiesOperation.writeString(Constants.CarConfigure.CAR_RTSP, carConfigBean.getRtsp());

            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.carConfigBean = carConfigBean;
                }
            });
        }
    }

    /**
     * 摄像头配置
     */
    public List<CameraConfigBean> getCameraListConfig() {
        synchronized (ConfigUtil.this) {
            if (cameraConfigList == null) {
                cameraConfigList = new ArrayList<>();

                for (int i = 0; i < 8; i++) {
                    String cameraStr = propertiesOperation.readString(String.format(Locale.getDefault(),"CAMERA%d", i + 1),
                            "");
                    if (!TextUtils.isEmpty(cameraStr)) {
                        cameraConfigList.add(gson.fromJson(cameraStr, CameraConfigBean.class));
                    }
                }
            }
        }
        return cameraConfigList;
    }

    public void saveCameraList(final List<CameraConfigBean> cameraConfigList) {
        synchronized (ConfigUtil.this) {
            if (propertiesOperation.getProps() == null) {
                toast("配置文件信息错误请检查");
                return;
            }

            for (CameraConfigBean cameraConfigBean : cameraConfigList) {
                if (cameraConfigBean.isUse()) {
                    propertiesOperation.writeString(String.format(Locale.getDefault(),"CAMERA%d", cameraConfigBean.getId()),
                            gson.toJson(cameraConfigBean));
                } else {
                    propertiesOperation.writeString(String.format(Locale.getDefault(),"CAMERA%d", cameraConfigBean.getId()),
                            "");
                }
            }

            propertiesOperation.commit(new IPropertiesCommit() {
                @Override
                public void onSuccess() {
                    instance.cameraConfigList = cameraConfigList;
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
