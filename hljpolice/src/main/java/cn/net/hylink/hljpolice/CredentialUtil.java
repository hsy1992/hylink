package cn.net.hylink.hljpolice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

import cn.net.hylink.hljpolice.bean.UrlConfigBean;

/**
 * @author haosiyuan
 * @date 2020/9/17 3:02 PM
 * info :
 */
public class CredentialUtil {

    private Context context;

    public void init(Context context) {

        this.context = context.getApplicationContext();
    }

    public void getCredential(UrlConfigBean configBean) {
        Uri uri = Uri.parse("content://com.ydjw.ua.getCredential");

        Bundle params = new Bundle();
        String messageId = getMessageId();
        params.putString("messageId", messageId);
        params.putString("version", configBean.getVersion());
        params.putString("appId", configBean.getAppId());
        params.putString("orgId", configBean.getOrgId());
        params.putString("networkAreaCode", configBean.getNetworkAreaCode());
        params.putString("packageName", configBean.getPackageName());

        Bundle bundle = context.getContentResolver().call(uri, "", null, params);

        if (bundle == null) {
            Toast.makeText(context, "获取应用凭证失败", Toast.LENGTH_SHORT).show();
            return;
        }

        if (messageId.equals(bundle.getString("messageId"))) {
            String appCredential = bundle.getString("appCredential");
            String userCredential = bundle.getString("userCredential");

        }

    }

    private String getMessageId() {
        return UUID.randomUUID().toString();
    }

}
