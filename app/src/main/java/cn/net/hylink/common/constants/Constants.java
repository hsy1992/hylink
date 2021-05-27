package cn.net.hylink.common.constants;

/**
 * @author haosiyuan
 * @date 2020-06-09 10:22
 * info : 常量
 */
public interface Constants {

    /**
     * 扫描车
     **/
    int SNAP_CAR = 0;
    /**
     * 扫描人
     **/
    int SNAP_PERSON = 1;
    /**
     * 都扫
     **/
    int SNAP_ALL = 2;

    int PAGE_SIZE = 10;

    String CAMERA_ALL = "全部";
    String CAMERA_CLOUD = "云台";

    /**
     * 是否选中
     */
    String UN_SELECT = "0";

    String SELECT = "1";

    String SP_NAME = "hylink";

    /**
     * 抓拍机器
     */
    String SP_CAPTURE_TYPE = "CAPTURE_TYPE";

    String SP_CAPTURE_CONTENT = "SP_CAPTURE_CONTENT";

    int HYLINK1 = 1;

    int HYLINK1803 = 2;

    /**
     * mqtt推送 url
     */
    String FLYING_MQTT_PUSH_URL = "/mqtt/push";

    /**
     * 抓拍 url
     */
    String FLYING_SNAP_URL = "/snap/push";

    interface BaseConfigure {

        String BASE_REGISTER = "BASE_REGISTER";

        String BASE_URL = "BASE_URL";

        String BASE_CAR_NO = "BASE_CAR_NO";

        String BASE_MQTT_URL = "BASE_MQTT_URL";

        String BASE_ORGANIZATION_CODE = "BASE_ORGANIZATION_CODE";

        String BASE_ORGANIZATION_NAME = "BASE_ORGANIZATION_NAME";
    }

    /**
     * 上传类型 0 正常  1  ftp上传
     */
    String UPLOAD_TYPE = "UPLOAD_TYPE";

    interface FtpConfigure {

        String FTP_IP = "FTP_IP";
        String FTP_USER = "FTP_USER";
        String FTP_PORT = "FTP_PORT";
        String FTP_PASSWORD = "FTP_PASSWORD";
    }

    interface CloudConfigure {
        String CLOUD_IP = "CLOUD_IP";
        String CLOUD_PORT = "CLOUD_PORT";
        String CLOUD_USER = "CLOUD_USER";
        String CLOUD_PASSWORD = "CLOUD_PASSWORD";
        String CLOUD_RTSP = "CLOUD_RTSP";
        String CLOUD_CODE = "CLOUD_CODE";
        String CLOUD_TYPE = "CLOUD_TYPE";
    }

    interface NvrConfigure {
        String NVR_IP = "NVR_IP";
        String NVR_PORT = "NVR_PORT";
        String NVR_USER = "NVR_USER";
        String NVR_PASSWORD = "NVR_PASSWORD";
    }

    /**
     * 抓拍机配置
     */
    String SNAP_TYPE = "SNAP_TYPE";

    interface SnapConfigure {
        String SNAP_IP = "SNAP_IP";
        String SNAP_PORT = "SNAP_PORT";
        String SNAP_USER = "SNAP_USER";
        String SNAP_PASSWORD = "SNAP_PASSWORD";
    }

    interface RedisConfigure {
        String REDIS_IP = "REDIS_IP";
        String REDIS_PORT = "REDIS_PORT";
    }

    interface CarConfigure {
        String CAR_IP = "CAR_IP";
        String CAR_RTSP = "CAR_RTSP";
    }

}
