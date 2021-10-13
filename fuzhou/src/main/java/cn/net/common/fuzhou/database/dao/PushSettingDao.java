package cn.net.common.fuzhou.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import cn.net.common.fuzhou.database.entity.PushSettingEntity;

/**
 * @author haosiyuan
 * @date 2020-06-23 17:19
 * info :
 */
@Dao
public interface PushSettingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PushSettingEntity entity);

    @Query("SELECT * FROM tb_push_setting WHERE id = 0 ")
    PushSettingEntity getPushSetting();
}
