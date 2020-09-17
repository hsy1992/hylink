package cn.net.hylink.common.database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author haosiyuan
 * @date 2020-04-01 11:13
 * info : 记录扫描历史信息
 */
@Entity(tableName = "tb_mqtt_span")
public class SpanHistoryMQTTEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * api url
     */
    private String mqttApi;
    /**
     * 返回存储的json
     */
    private String json;

    private long date;

    public SpanHistoryMQTTEntity(String mqttApi, String json, long date) {
        this.mqttApi = mqttApi;
        this.json = json;
        this.date = date;
    }

    public String getMqttApi() {
        return mqttApi;
    }

    public void setMqttApi(String mqttApi) {
        this.mqttApi = mqttApi;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
