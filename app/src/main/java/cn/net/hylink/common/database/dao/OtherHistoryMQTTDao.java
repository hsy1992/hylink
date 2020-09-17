package cn.net.hylink.common.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import cn.net.hylink.common.database.entity.OtherHistoryMQTTEntity;
import cn.net.hylink.common.database.entity.SpanHistoryMQTTEntity;
import io.reactivex.Flowable;

/**
 * @author haosiyuan
 * @date 2020-04-01 11:21
 * info : 记录扫描历史信息
 */
@Dao
public interface OtherHistoryMQTTDao {

    /**
     * 分页查找
     */
    @Query("SELECT * FROM tb_mqtt_other order by id desc limit :pageNo, :pageSize")
    Flowable<List<SpanHistoryMQTTEntity>> findOtherHistory(int pageNo, int pageSize);

    /**
     * @param data
     */
    @Insert
    void insert(OtherHistoryMQTTEntity... data);
}
