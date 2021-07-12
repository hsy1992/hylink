package cn.net.hylink.common.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author haosiyuan
 * @date 2020-07-04 10:54
 * info :
 */
@Entity(tableName = "tb_push_code")
public class PushIpCodeEntity {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String ip;

    private String code;

    public PushIpCodeEntity(int id, String ip, String code) {
        this.id = id;
        this.ip = ip;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
