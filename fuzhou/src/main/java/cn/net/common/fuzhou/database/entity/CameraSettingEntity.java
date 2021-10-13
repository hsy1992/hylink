package cn.net.common.fuzhou.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author haosiyuan
 * @date 2020-07-08 10:04
 * info :
 */
@Entity(tableName = "tb_camera_setting")
public class CameraSettingEntity {

    /**
     * 0
     */
    @PrimaryKey(autoGenerate = false)
    private int id;

    /**
     */
    @NonNull
    private String cloud;

    /**
     */
    @NonNull
    private String car;

    /**
     */
    @NonNull
    private String cameraList;

    private String nvr;

    public CameraSettingEntity(int id, String cloud, String car, String cameraList, String nvr) {
        this.id = id;
        this.cloud = cloud;
        this.car = car;
        this.cameraList = cameraList;
        this.nvr = nvr;
    }

    public String getNvr() {
        return nvr;
    }

    public void setNvr(String nvr) {
        this.nvr = nvr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCameraList() {
        return cameraList;
    }

    public void setCameraList(String cameraList) {
        this.cameraList = cameraList;
    }
}
