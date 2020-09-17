package cn.net.hylink.common.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import cn.net.hylink.common.database.entity.CameraSettingEntity;

/**
 * @author haosiyuan
 * @date 2020-07-08 10:41
 * info :
 */
@Dao
public interface CameraSettingDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CameraSettingEntity entity);

    @Query("SELECT * FROM tb_camera_setting WHERE id = 0 ")
    CameraSettingEntity getCameraSettingEntity();
}
