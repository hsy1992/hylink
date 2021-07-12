package cn.net.hylink.common.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * @author haosiyuan
 * @date 2020-03-25 17:25
 * info : 扫描结果表
 */
@Entity(tableName = "tb_span_result")
public class SpanResultEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    /**
     * 扫描的类型
     */
    private int type;
    /**
     * 照片地址
     */
    private String picPath;
    /**
     * 车牌时有
     */
    private String carNum;
    /**
     * 摄像头返回type
     */
    private String cameraName;
    /**
     * 扫描时间
     */
    private long time;

    private String address;

    private String ip;

    /**
     * 0 未关注 1 已关注
     */
    private Integer attention;

    /**
     * 上传返回的比对id
     */
    private String uploadId;

    /**
     * 原图
     */
    private String picPathOriginal;

    public SpanResultEntity(int type, String picPath, String carNum, String cameraName, long time,
                            String address, String ip, Integer attention, String picPathOriginal) {
        this.type = type;
        this.picPath = picPath;
        this.carNum = carNum;
        this.cameraName = cameraName;
        this.time = time;
        this.address = address;
        this.ip = ip;
        this.attention = attention;
        this.picPathOriginal = picPathOriginal;
    }

    public String getPicPathOriginal() {
        return picPathOriginal;
    }

    public void setPicPathOriginal(String picPathOriginal) {
        this.picPathOriginal = picPathOriginal;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
