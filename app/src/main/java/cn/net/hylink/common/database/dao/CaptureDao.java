package cn.net.hylink.common.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import cn.net.hylink.common.database.entity.CameraSettingEntity;
import cn.net.hylink.common.database.entity.CaptureEntity;

/**
 * @author haosiyuan
 * @date 2020-07-08 10:41
 * info :
 */
@Dao
public interface CaptureDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CaptureEntity entity);

    @Query("SELECT * FROM tb_capture WHERE id = 0 ")
    CaptureEntity getCaptureEntity();
}
