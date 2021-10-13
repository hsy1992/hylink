package cn.net.common.fuzhou.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import cn.net.common.fuzhou.database.entity.PushIpCodeEntity;


/**
 * @author haosiyuan
 * @date 2020-06-23 17:19
 * info :
 */
@Dao
public interface PushIpCodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<PushIpCodeEntity> list);

    @Query("SELECT * FROM tb_push_code")
    List<PushIpCodeEntity> loadAll();
}
