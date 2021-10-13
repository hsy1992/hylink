package cn.net.common.fuzhou.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Environment;

import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_1_4;
import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_2_4;
import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_3_4;
import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_4_5;
import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_5_6;
import static cn.net.common.fuzhou.database.AppDatabase.MIGRATION_6_7;

/**
 * @author haosiyuan
 * @date 2020/12/10 10:55 AM
 * info :
 */
public class AppDatabaseManager {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Environment.getExternalStorageDirectory() + "/span.db")
                            .addMigrations(MIGRATION_1_4, MIGRATION_2_4, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                                    MIGRATION_6_7)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
