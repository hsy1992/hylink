package cn.net.hylink.hljpolice;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import cn.net.hylink.hljpolice.bean.ConfigFileBean;
import cn.net.hylink.hljpolice.bean.CredentialBean;

/**
 * @author haosiyuan
 * @date 2020/9/18 10:34 AM
 * info :
 */
class AutoParseResource {

    public void parse(Context applicationContext, Gson gson, String fileName) {

        ConfigFileBean configFileBean = getConfig(applicationContext, gson, fileName);
        CredentialUtil.getInstance().setConfigFile(configFileBean);
        if (configFileBean == null) {
            Toast.makeText(applicationContext, "获取配置文件失败", Toast.LENGTH_SHORT).show();
            return;
        }
        CredentialBean credentialBean = CredentialUtil.getInstance().getCredential(configFileBean.getUrlConfigBean());
        if (credentialBean != null) {
            CredentialUtil.getInstance().getResourceAddressList(credentialBean);
        }

    }

    private ConfigFileBean getConfig(Context context, Gson gson, String fileName) {
        try (InputStream is = context.getAssets().open(fileName); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[4 * 1024];
            int len = 0;
            while ((len = is.read(bytes)) != - 1) {
                bos.write(bytes, 0, len);
            }
            final String json = new String(bos.toByteArray());
            final ConfigFileBean configFileBean = gson.fromJson(json, ConfigFileBean.class);

            return configFileBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
