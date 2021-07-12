package cn.net.hylink.common.config;

/**
 * @ClassName BaseConfigBean
 * @Description 注册得基础信息
 * @Author haosiyuan
 * @Date 2021/5/28 14:54
 * @Version 1.0
 */
public class BaseConfigBean {
    private String url;
    private String carNo;
    private String code;
    private String name;
    private String mqttUrl;
    private boolean isRegister;

    public BaseConfigBean(String url, String carNo, String code, String name, String mqttUrl, boolean isRegister) {
        this.url = url;
        this.carNo = carNo;
        this.code = code;
        this.name = name;
        this.mqttUrl = mqttUrl;
        this.isRegister = isRegister;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMqttUrl() {
        return mqttUrl;
    }

    public void setMqttUrl(String mqttUrl) {
        this.mqttUrl = mqttUrl;
    }

    @Override
    public String toString() {
        return "BaseConfigBean{" +
                "url='" + url + '\'' +
                ", carNo='" + carNo + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", mqttUrl='" + mqttUrl + '\'' +
                ", isRegister=" + isRegister +
                '}';
    }
}
