package cn.net.hylink.common.config;

/**
 * @ClassName CarConfigBean
 * @Description 车内摄像头
 * @Author haosiyuan
 * @Date 2021/6/2 10:00
 * @Version 1.0
 */
public class CarConfigBean {

    private String ip;
    private String rtsp;

    public CarConfigBean(String ip, String rtsp) {
        this.ip = ip;
        this.rtsp = rtsp;
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

    @Override
    public String toString() {
        return "CarConfigBean{" +
                "ip='" + ip + '\'' +
                ", rtsp='" + rtsp + '\'' +
                '}';
    }
}
