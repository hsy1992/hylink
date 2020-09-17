package cn.net.hylink.common.constants;

import android.net.Uri;

/**
 * @author haosiyuan
 * @date 2020-05-07 10:30
 * info :
 */
public interface ProvideUrl {

    /**
     * 用户登录
     */
    String USER_AUTHORITY = "com.login.provider.LoginContentProvider";

    Uri USER_CONTENT_URI = Uri.parse("content://" + USER_AUTHORITY + "/first");

    /**
     * 基础配置
     */
    String BASE_AUTHORITY = "com.reglink.launcher4.BaseUrlContentProvider";
    Uri CONTENT_URI_URL = Uri.parse("content://" + BASE_AUTHORITY + "/url");
}
