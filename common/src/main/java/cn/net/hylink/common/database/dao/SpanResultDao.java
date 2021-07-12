package cn.net.hylink.common.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import cn.net.hylink.common.database.entity.SpanResultEntity;
import io.reactivex.Flowable;

/**
 * @author haosiyuan
 * @date 2020-03-25 17:30
 * info : 扫描结果表
 */
@Dao
public interface SpanResultDao {

    /**
     * 查找全部
     * @return
     */
    @Query("SELECT * FROM tb_span_result")
    Flowable<List<SpanResultEntity>> loadSpanResult();

    /**
     * 根据时间、类型 查找数据
     */
    @Query("SELECT * FROM tb_span_result WHERE ip = :cameraIp AND type = :type AND time > :startTime AND time < :endTime ORDER BY time DESC LIMIT :pageNo, :pageSize")
    Flowable<List<SpanResultEntity>> loadSpanResult(String cameraIp, int type, int pageNo, int pageSize, long startTime, long endTime);

    /**
     * 根据时间、类型 查找数据
     */
    @Query("SELECT * FROM tb_span_result WHERE ip = :cameraIp AND type = :type AND time > :startTime AND time < :endTime AND attention =:attention ORDER BY time DESC LIMIT :pageNo, :pageSize")
    Flowable<List<SpanResultEntity>> loadSpanResult(String cameraIp, int type, int pageNo, int pageSize, long startTime, long endTime, int attention);

    /**
     * 根据时间、类型 查找数据
     */
    @Query("SELECT * FROM tb_span_result WHERE type = :type AND time > :startTime AND time < :endTime ORDER BY time DESC LIMIT :pageNo, :pageSize")
    Flowable<List<SpanResultEntity>> loadSpanResult(int type, int pageNo, int pageSize, long startTime, long endTime);

    /**
     * 根据时间、类型 查找数据
     */
    @Query("SELECT * FROM tb_span_result WHERE type = :type AND time > :startTime AND time < :endTime AND attention =:attention ORDER BY time DESC LIMIT :pageNo, :pageSize")
    Flowable<List<SpanResultEntity>> loadSpanResult(int type, int pageNo, int pageSize, long startTime, long endTime, int attention);


    @Query("DELETE FROM tb_span_result WHERE picPath IN (:path)")
    void deleteByPath(List<String> path);

    @Insert
    void insert(SpanResultEntity... spanResultEntities);

    @Insert
    long insert(SpanResultEntity spanResultEntities);

    /**
     * 根据时间查找数量
     * @return
     */
    @Query("SELECT COUNT(id) FROM tb_span_result WHERE type = :type AND time > :startTime AND time < :endTime")
    int loadSpanCountByTime(int type, long startTime, long endTime);

    /**
     * 更新关注
     * @param id
     * @param attention
     */
    @Query("UPDATE tb_span_result SET attention = :attention WHERE id = :id")
    void updateAttentionById(int id, int attention);

    /**
     * 更新上传id
     * @param id
     * @param uploadId
     */
    @Query("UPDATE tb_span_result SET uploadId = :uploadId WHERE id = :id")
    void updateUploadIdById(long id, String uploadId);

    /**
     * 查找未上传数据
     */
    @Query("SELECT * FROM tb_span_result WHERE uploadId = :uploadId")
    List<SpanResultEntity> loadSpanResultNoUpload(String uploadId);
}
