package cn.net.hylink.common.constants;

/**
 * @author haosiyuan
 * @date 2020-06-09 10:22
 * info : 常量
 */
public interface Constants {

    /** 扫描车 **/
    int SNAP_CAR = 0;
    /** 扫描人 **/
    int SNAP_PERSON = 1;
    /** 都扫 **/
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


}
