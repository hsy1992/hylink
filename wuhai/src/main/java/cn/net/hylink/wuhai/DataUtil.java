package cn.net.hylink.wuhai;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;


/**
 * @author haosiyuan
 * @date 2020-03-30 09:00
 * info : 获取一些数据
 */
public class DataUtil {


    public static final String AUTHORITY = "com.login.provider.LoginContentProvider";
    public static final Uri CONTENT_URI_FIRST = Uri.parse("content://" + AUTHORITY + "/first");
    public static UserBean userEntity;

    public static UserBean getSignData(Context context) {
        if (userEntity == null) {
            Cursor cursor = context.getApplicationContext().getContentResolver().query(CONTENT_URI_FIRST, null,
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                userEntity = cursor2Model(cursor, UserBean.class);
            } else {
                userEntity = null;
            }
        }
        return userEntity;
    }

    public static void refreshData(Context context) {
        Cursor cursor = context.getApplicationContext().getContentResolver().query(CONTENT_URI_FIRST, null,
                null, null, null);
        userEntity = cursor2Model(cursor, UserBean.class);

    }

    public static <T> T cursor2Model(Cursor cursor, Class<T> classz) {
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

                    }

                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }

        return t;
    }

    public static String getPhoneIMEI() {
        return Build.SERIAL;
    }

}
