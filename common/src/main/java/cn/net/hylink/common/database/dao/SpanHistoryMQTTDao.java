package cn.net.hylink.common.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
import cn.net.hylink.common.database.entity.SpanHistoryMQTTEntity;
import io.reactivex.Flowable;

/**
 * @author haosiyuan
 * @date 2020-04-01 11:21
 * info : 记录扫描历史信息
 */
@Dao
public interface SpanHistoryMQTTDao {

    /**
     * 分页查找
     */
    @Query("SELECT * FROM tb_mqtt_span order by id desc limit :pageNo, :pageSize")
    Flowable<List<SpanHistoryMQTTEntity>> findSpanHistory(int pageNo, int pageSize);

    /**
     * @param data
     */
    @Insert
    void insert(SpanHistoryMQTTEntity... data);

    /**
     * @param data
     */
    @Insert
    long insert(SpanHistoryMQTTEntity data);

    @Query("SELECT * FROM TB_MQTT_SPAN WHERE id = :id")
    SpanHistoryMQTTEntity findSpanHistoryById(long id);
}
