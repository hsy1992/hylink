package cn.net.common.fuzhou.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author haosiyuan
 * @date 2020-07-08 18:56
 * info : 抓拍
 */
@Entity(tableName = "tb_capture")
public class CaptureEntity {

    /**
     * 0
     */
    @PrimaryKey(autoGenerate = false)
    private int id;

    /**
     */
    @NonNull
    private int type;

    /**
     */
    @NonNull
    private String content;

    public CaptureEntity(int id, int type, @NonNull String content) {
        this.id = id;
        this.type = type;
        this.content = content;
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

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }
}
