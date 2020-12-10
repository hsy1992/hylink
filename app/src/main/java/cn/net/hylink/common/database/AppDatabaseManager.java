package cn.net.hylink.common.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Environment;

import static cn.net.hylink.common.database.AppDatabase.*;

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
                            .addMigrations(MIGRATION_1_4, MIGRATION_2_4, MIGRATION_3_4)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
