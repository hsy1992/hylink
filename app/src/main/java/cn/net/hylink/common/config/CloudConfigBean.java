package cn.net.hylink.common.config;

/**
 * @ClassName CloudConfigBean
 * @Description 云台配置
 * @Author haosiyuan
 * @Date 2021/6/1 12:00
 * @Version 1.0
 */
public class CloudConfigBean {

    private String ip;
    private int port;
    private String user;
    private String password;
    private String rtsp;
    private String code;
    private int type;

    public CloudConfigBean(String ip, int port, String user, String password, String rtsp, String code, int type) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
        this.rtsp = rtsp;
        this.code = code;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRtsp() {
        return rtsp;
    }

    public void setRtsp(String rtsp) {
        this.rtsp = rtsp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CloudConfigBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", rtsp='" + rtsp + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                '}';
    }
}
