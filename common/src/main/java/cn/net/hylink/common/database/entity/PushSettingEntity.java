package cn.net.hylink.common.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author haosiyuan
 * @date 2020-06-23 17:18
 * info :
 */
@Entity(tableName = "tb_push_setting")
public class PushSettingEntity {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String cardCode;

    private String bayonetCode;

    private String bayonetName;

    /**
     * 0警务
     * 1专网
     */
    private String linkType;

    private String faceUrl;

    private String faceType;

    private String plateUrl;

    private String plateType;

    public PushSettingEntity(int id, String cardCode, String bayonetCode, String bayonetName,
                             String linkType, String faceUrl, String faceType, String plateUrl, String plateType) {
        this.id = id;
        this.cardCode = cardCode;
        this.bayonetCode = bayonetCode;
        this.bayonetName = bayonetName;
        this.linkType = linkType;
        this.faceUrl = faceUrl;
        this.faceType = faceType;
        this.plateUrl = plateUrl;
        this.plateType = plateType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getBayonetCode() {
        return bayonetCode;
    }

    public void setBayonetCode(String bayonetCode) {
        this.bayonetCode = bayonetCode;
    }

    public String getBayonetName() {
        return bayonetName;
    }

    public void setBayonetName(String bayonetName) {
        this.bayonetName = bayonetName;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getPlateUrl() {
        return plateUrl;
    }

    public void setPlateUrl(String plateUrl) {
        this.plateUrl = plateUrl;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }
}
