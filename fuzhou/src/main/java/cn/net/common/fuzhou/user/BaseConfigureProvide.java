package cn.net.common.fuzhou.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import cn.net.common.fuzhou.bean.BaseConfigureBean;
import cn.net.common.fuzhou.constants.ProvideUrl;
import cn.net.common.fuzhou.util.CursorToBeanUtil;

/**
 * @author haosiyuan
 * @date 2020-06-09 10:31
 * info : 基础配置
 */
public class BaseConfigureProvide {

    private BaseConfigureBean baseConfigureBean;

    private static volatile BaseConfigureProvide instance;

    private boolean hasRegister = false;

    public static BaseConfigureProvide getInstance() {

        if (instance == null) {
            synchronized (BaseConfigureProvide.class) {
                if (instance == null) {
                    instance = new BaseConfigureProvide();
                }
            }
        }
        return instance;
    }

    private BaseConfigureProvide() {}

    public BaseConfigureBean getBaseConfigure(Context context) {

        if (!hasRegister) {
            try {
                final ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
                final Cursor cursor = contentResolver.query(ProvideUrl.CONTENT_URI_URL,
                        null,null,null,null);
                contentResolver.registerContentObserver(ProvideUrl.CONTENT_URI_URL, false,
                        new ContentObserver(new Handler(Looper.getMainLooper())) {
                            @Override
                            public void onChange(boolean selfChange) {
                                final Cursor cursor = contentResolver.query(ProvideUrl.CONTENT_URI_URL,
                                        null,null,null,null);
                                setUserInfo(cursor);
                            }
                        });
                setUserInfo(cursor);
                hasRegister = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseConfigureBean;
    }

    private void setUserInfo(Cursor cursor) {
        if (cursor != null) {
            baseConfigureBean = CursorToBeanUtil.cursor2Model(cursor, BaseConfigureBean.class);
            if (baseConfigureBean != null) {
                Log.i("BaseConfigureProvide", baseConfigureBean.toString());
            }
        } else {
            baseConfigureBean = null;
        }
    }
}
