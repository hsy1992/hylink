package cn.net.hylink.common.config;

/**
 * @ClassName NvrConfigBean
 * @Description nvr 配置
 * @Author haosiyuan
 * @Date 2021/6/2 9:21
 * @Version 1.0
 */
public class CommonConfigBean {

    private String ip;
    private int port;
    private String user;
    private String password;

    public CommonConfigBean(String ip, int port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
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
        return "CommonConfigBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
