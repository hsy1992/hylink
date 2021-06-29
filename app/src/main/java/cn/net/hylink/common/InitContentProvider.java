package cn.net.hylink.common;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.net.hylink.common.util.ConfigUtil;
import cn.net.hylink.common.util.CrashUtil;

/**
 * @ClassName InitContentProvider
 * @Description 初始化
 * @Author haosiyuan
 * @Date 2021/6/29 11:37
 * @Version 1.0
 */
public class InitContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        ConfigUtil.getInstance().init(getContext());
        CrashUtil.getInstance().init(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
