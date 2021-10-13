package cn.net.common.fuzhou.util;

import android.database.Cursor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author haosiyuan
 * @date 2020-05-07 10:35
 * info : 游标转实体
 */
public class CursorToBeanUtil {

    public static <T>  T cursor2Model(Cursor cursor, Class<T> classz) {

        if (!cursor.moveToFirst() || cursor.isAfterLast()) {
            return null;
        }

        T t = null;
        Constructor<T> csr;
        try {
            csr = classz.getConstructor();
            try {
                t = csr.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Type type = fields[i].getType();
                    String fieldName = fields[i].getName();
                    fields[i].setAccessible(true);
                    try {
                        if (type == Long.class || (type == Long.TYPE)) {
                            fields[i].set(t,
                                    cursor.getLong(cursor.getColumnIndex(fieldName)));
                        } else if (Integer.class == type || (type == Integer.TYPE)) {
                            fields[i].set(t,
                                    cursor.getInt(cursor.getColumnIndex(fieldName)));
                        } else if (type == String.class) {
                            fields[i].set(t,
                                    cursor.getString(cursor.getColumnIndex(fieldName)));
                        } else if (type == byte[].class) {
                            fields[i].set(t,
                                    cursor.getBlob(cursor.getColumnIndex(fieldName)));
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }

        return t;
    }
}
