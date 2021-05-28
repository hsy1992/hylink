package cn.net.hylink.common.user;

import android.annotation.SuppressLint;
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
import java.util.ArrayList;
import java.util.List;
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

    private static final String CONFIG_PATH = Environment.getExternalStorageDirectory() + "/config.txt";

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
    @SuppressLint("DefaultLocale")
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

            //上传
            configureBean.setUploadType(props.getProperty(Constants.UPLOAD_TYPE, ""));
            if (Constants.UploadType.FTP.equals(configureBean.getUploadType())) {
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
            configureBean.setSnapType(props.getProperty(Constants.SNAP_TYPE, ""));
            if (Constants.SnapType.HYLINK1.equals(configureBean.getSnapType())) {
                configureBean.setRedis(new ConfigureBean.RedisBean(
                        props.getProperty(Constants.RedisConfigure.REDIS_IP, ""),
                        Integer.parseInt(props.getProperty(Constants.RedisConfigure.REDIS_PORT, ""))
                ));
            } else {
                configureBean.setSnap(new ConfigureBean.SnapBean(
                        props.getProperty(Constants.SnapConfigure.SNAP_IP, ""),
                        Integer.parseInt(props.getProperty(Constants.SnapConfigure.SNAP_PORT, "")),
                        props.getProperty(Constants.SnapConfigure.SNAP_USER, ""),
                        props.getProperty(Constants.SnapConfigure.SNAP_PASSWORD, "")
                ));
            }
            //车内摄像头
            String carIp = props.getProperty(Constants.CarConfigure.CAR_IP, "");
            if (!TextUtils.isEmpty(carIp)) {
                configureBean.setCarBean(new ConfigureBean.CarBean(
                        props.getProperty(Constants.CarConfigure.CAR_IP, ""),
                        props.getProperty(Constants.CarConfigure.CAR_RTSP, "")
                ));
            }
            //小摄像头
            List<ConfigureBean.CameraListBean> cameraListBeans = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                String cameraInfo = props.getProperty(String.format("CAMERA%d", i + 1), "");
                if (!TextUtils.isEmpty(cameraInfo)) {
                    cameraListBeans.add(gson.fromJson(cameraInfo, ConfigureBean.CameraListBean.class));
                }
            }
            configureBean.setCameraList(cameraListBeans);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
