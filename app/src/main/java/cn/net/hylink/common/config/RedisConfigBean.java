package cn.net.hylink.common.config;

/**
 * @ClassName RedisConfigBean
 * @Description redis
 * @Author haosiyuan
 * @Date 2021/6/2 9:36
 * @Version 1.0
 */
public class RedisConfigBean {

    private String ip;
    private int port;

    public RedisConfigBean(String ip, int port) {
        this.ip = ip;
        this.port = port;
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

    @Override
    public String toString() {
        return "RedisConfigBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
