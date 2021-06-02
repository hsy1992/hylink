package cn.net.hylink.common.config;

import java.io.Serializable;

/**
 * @ClassName CameraConfigBean
 * @Description 摄像头配置
 * @Author haosiyuan
 * @Date 2021/6/2 10:12
 * @Version 1.0
 */
public class CameraConfigBean implements Serializable {

    /**
     * id : 8
     * name : 左后
     * type : 0
     * ip : 192.168.1.12
     * rtsp : rtsp://admin:admin@192.168.1.12:554/ch1/stream2
     * code : RL12
     * use : true
     */

    private int id;
    private String name;
    private int type;
    private String ip;
    private String rtsp;
    private String code;
    private boolean use;

    public CameraConfigBean(int id, String name, int type, String ip, String rtsp, String code, boolean use) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.ip = ip;
        this.rtsp = rtsp;
        this.code = code;
        this.use = use;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRtsp() {
        return rtsp;
    }

    public void setRtsp(String rtsp) {
        this.rtsp = rtsp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "CameraConfigBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", ip='" + ip + '\'' +
                ", rtsp='" + rtsp + '\'' +
                ", code='" + code + '\'' +
                ", use=" + use +
                '}';
    }
}
