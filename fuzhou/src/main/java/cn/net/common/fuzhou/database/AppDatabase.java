package cn.net.common.fuzhou.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import cn.net.common.fuzhou.database.dao.CameraSettingDao;
import cn.net.common.fuzhou.database.dao.CaptureDao;
import cn.net.common.fuzhou.database.dao.OtherHistoryMQTTDao;
import cn.net.common.fuzhou.database.dao.PushIpCodeDao;
import cn.net.common.fuzhou.database.dao.PushSettingDao;
import cn.net.common.fuzhou.database.dao.SpanHistoryMQTTDao;
import cn.net.common.fuzhou.database.dao.SpanResultDao;
import cn.net.common.fuzhou.database.entity.CameraSettingEntity;
import cn.net.common.fuzhou.database.entity.CaptureEntity;
import cn.net.common.fuzhou.database.entity.OtherHistoryMQTTEntity;
import cn.net.common.fuzhou.database.entity.PushIpCodeEntity;
import cn.net.common.fuzhou.database.entity.PushSettingEntity;
import cn.net.common.fuzhou.database.entity.SpanHistoryMQTTEntity;
import cn.net.common.fuzhou.database.entity.SpanResultEntity;


/**
 * @author haosiyuan
 * @date 2020-03-25 17:18
 * info : 数据库
 */
@Database(version = 7, entities = {SpanResultEntity.class, OtherHistoryMQTTEntity.class, SpanHistoryMQTTEntity.class
        , PushSettingEntity.class, PushIpCodeEntity.class, CameraSettingEntity.class, CaptureEntity.class},
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public static AppDatabase getInstance(Context context) {
        return AppDatabaseManager.getInstance(context);
    }

    static final Migration MIGRATION_1_4 = new Migration(1, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `tb_camera_setting` (`id` INTEGER NOT NULL," +
                    "  `cloud` TEXT NOT NULL, `car` TEXT NOT NULL, `cameraList` TEXT NOT NULL, `nvr` TEXT," +
                    " PRIMARY KEY (`id`))");

            database.execSQL("CREATE TABLE IF NOT EXISTS `tb_capture` (`id` INTEGER NOT NULL," +
                    "  `type` INTEGER NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY (`id`))");

        }
    };

    static final Migration MIGRATION_2_4 = new Migration(2, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `tb_camera_setting` (`id` INTEGER NOT NULL," +
                    "  `cloud` TEXT NOT NULL, `car` TEXT NOT NULL, `cameraList` TEXT NOT NULL, `nvr` TEXT," +
                    " PRIMARY KEY (`id`))");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE `tb_camera_setting` ADD COLUMN `nvr` TEXT");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE `tb_span_result` ADD COLUMN `attention` INTEGER");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE `tb_span_result` ADD COLUMN `uploadId` TEXT");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE `tb_span_result` ADD COLUMN `picPathOriginal` TEXT");
        }
    };

    public abstract PushSettingDao getPushSettingDao();

    public abstract SpanResultDao getSpanResultDao();

    public abstract SpanHistoryMQTTDao getSpanHistoryMQTTDao();

    public abstract OtherHistoryMQTTDao getOtherHistoryMQTTDao();

    public abstract PushIpCodeDao getPushIpCodeDao();

    public abstract CameraSettingDao getCameraSettingDao();

    public abstract CaptureDao getCaptureDao();
}
