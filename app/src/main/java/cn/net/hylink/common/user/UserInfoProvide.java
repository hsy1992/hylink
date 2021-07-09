package cn.net.hylink.common.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;


import cn.net.hylink.common.bean.SignInResponseBean;
import cn.net.hylink.common.constants.ProvideUrl;
import cn.net.hylink.common.util.CursorToBeanUtil;

/**
 * @author haosiyuan
 * @date 2020-05-07 10:32
 * info : 用户信息提供
 */
public class UserInfoProvide {

    private SignInResponseBean userInfo;

    private static volatile UserInfoProvide instance;

    public static UserInfoProvide getInstance() {

        if (instance == null) {
            synchronized (UserInfoProvide.class) {
                if (instance == null) {
                    instance = new UserInfoProvide();
                }
            }
        }
        return instance;
    }

    private UserInfoProvide() {}

    public SignInResponseBean getUserInfo(Context context) {

        if (userInfo == null) {
            final ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            final Cursor cursor = contentResolver.query(ProvideUrl.USER_CONTENT_URI,
                    null,null,null,null);
            contentResolver.registerContentObserver(ProvideUrl.USER_CONTENT_URI, false, new ContentObserver(new Handler(Looper.getMainLooper())) {
                @Override
                public void onChange(boolean selfChange) {
                    final Cursor cursor = contentResolver.query(ProvideUrl.USER_CONTENT_URI,
                            null,null,null,null);
                    setUserInfo(cursor);
                }
            });
            setUserInfo(cursor);
        }
        return userInfo;
    }

    private void setUserInfo(Cursor cursor) {
        if (cursor != null) {
            userInfo = CursorToBeanUtil.cursor2Model(cursor, SignInResponseBean.class);
        } else {
            userInfo = null;
        }
    }
}
