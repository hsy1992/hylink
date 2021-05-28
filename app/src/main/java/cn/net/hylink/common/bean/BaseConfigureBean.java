package cn.net.hylink.common.bean;

/**
 * @author haosiyuan
 * @date 2020-06-09 10:38
 * info : 基础配置
 */
public class BaseConfigureBean {

    private Long _id;

    private String URL;

    private String CAR_NO;

    private String CODE;

    private String NAME;

    private String MQTT_URL;

    public String getMQTT_URL() {
        return MQTT_URL;
    }

    public void setMQTT_URL(String MQTT_URL) {
        this.MQTT_URL = MQTT_URL;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCAR_NO() {
        return CAR_NO;
    }

    public void setCAR_NO(String CAR_NO) {
        this.CAR_NO = CAR_NO;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "BaseConfigureBean{" +
                "_id=" + _id +
                ", URL='" + URL + '\'' +
                ", CAR_NO='" + CAR_NO + '\'' +
                ", CODE='" + CODE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", MQTT_URL='" + MQTT_URL + '\'' +
                '}';
    }
}
