package cn.net.hylink.hljpolice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.net.hylink.hljpolice.bean.AddressResponseBean;
import cn.net.hylink.hljpolice.bean.ConfigFileBean;
import cn.net.hylink.hljpolice.bean.CredentialBean;
import cn.net.hylink.hljpolice.bean.UrlConfigBean;

/**
 * @author haosiyuan
 * @date 2020/9/17 3:02 PM
 * info :
 */
public class CredentialUtil {

    private Context context;

    /**
     * 寻址uri
     */
    public static final String CREDENTIAL_URI = "content://com.ydjw.ua.getCredential";

    /**
     * 寻址uri
     */
    public static final String ADDRESS_URI = "content://com.ydjw.rsb.getResourceAddress";

    private AppPreferences appPreferences;

    private CredentialBean thisCredentialBean;

    /**
     * 资源id 的映射
     */
    private Map<String, AddressResponseBean> addressMap;

    private Gson gson;
    private AutoParseResource autoParseResource;
    private ConfigFileBean configFileBean;

    private static class Instance {
        private static CredentialUtil credentialUtil = new CredentialUtil();
    }

    public static CredentialUtil getInstance() {
        return Instance.credentialUtil;
    }

    public void init(Context context, String fileName) {
        this.context = context.getApplicationContext();
        this.appPreferences = new AppPreferences(context.getApplicationContext());
        gson = new Gson();
        //初始化去解析文件 自动配置应用凭证等
        autoParseResource = new AutoParseResource();
        autoParseResource.parse(context.getApplicationContext(), gson, fileName);
    }

    /**
     * 获取应用凭证
     *
     * @param configBean
     * @return
     */
    public CredentialBean getCredential(UrlConfigBean configBean) {

        if (thisCredentialBean != null) {
            return thisCredentialBean;
        }

        thisCredentialBean = appPreferences.getObject(PreferenceKey.CREDENTIAL_KEY, CredentialBean.class);
        if (thisCredentialBean != null && thisCredentialBean.getVersion().equals(configBean.getVersion())) {
            return thisCredentialBean;
        }

        Uri uri = Uri.parse(CREDENTIAL_URI);

        String messageId = "100001";
        Bundle bundle = new Bundle();
        bundle.putString("messageId", messageId);
        bundle.putString("version", configBean.getVersion());
        bundle.putString("appId", configBean.getAppId());
        bundle.putString("orgId", configBean.getOrgId());
        bundle.putString("networkAreaCode", configBean.getNetworkAreaCode());
        bundle.putString("packageName", configBean.getPackageName());

//        Bundle callBack = context.getContentResolver().call(uri, "", null, bundle);
        Bundle callBack = testData1();
        if (callBack == null) {
            Toast.makeText(context, "获取应用凭证失败", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (messageId.equals(callBack.getString("messageId"))) {
            String appCredential = callBack.getString("appCredential");
            String userCredential = callBack.getString("userCredential");
            CredentialBean returnCredentialBean = new CredentialBean();
            returnCredentialBean.setAppCredential(appCredential);
            returnCredentialBean.setUserCredential(userCredential);
            returnCredentialBean.setPackageName(configBean.getPackageName());
            returnCredentialBean.setVersion(configBean.getVersion());
            appPreferences.putObject(PreferenceKey.CREDENTIAL_KEY, returnCredentialBean);
            thisCredentialBean = returnCredentialBean;
            return returnCredentialBean;
        } else {
            Toast.makeText(context, "获取应用凭证失败", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 获取资源地址
     *
     * @return
     */
    public Map<String, AddressResponseBean> getResourceAddressList(CredentialBean credentialBean) {

        if (addressMap != null && addressMap.size() > 0) {
            return addressMap;
        }

        Type type = new TypeToken<Map<String, AddressResponseBean>>() {
        }.getType();

        addressMap = appPreferences.getObject(PreferenceKey.ADDRESS_LIST_KEY, type);
        if (addressMap != null && addressMap.size() > 0) {
            return addressMap;
        }

        Uri uri = Uri.parse(ADDRESS_URI);

        Bundle params = new Bundle();
        String messageId = "100002";
        params.putString("messageId", messageId);
        params.putString("version", credentialBean.getVersion());
        params.putString("appCredential", credentialBean.getAppCredential());

//        Bundle callBack = context.getContentResolver().call(uri, "", null, params);

        Bundle callBack = testData();
        if (callBack == null) {
            Toast.makeText(context, "获取应用资源地址失败", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (messageId.equals(callBack.getString("messageId"))) {

            String resourceList = callBack.getString("resourceList");
            int resultCode = callBack.getInt("resultCode");
            if (resultCode == 0) {
                //寻址成功
                addressMap = new HashMap<>();
                List<AddressResponseBean> addressResponseBeanList = gson.fromJson(resourceList,
                        new TypeToken<List<AddressResponseBean>>() {
                        }.getType());

                for (AddressResponseBean addressResponseBean : addressResponseBeanList) {
                    addressMap.put(addressResponseBean.getResourceId(), addressResponseBean);
                }

                appPreferences.putObject(PreferenceKey.ADDRESS_LIST_KEY, addressMap);
                return addressMap;
            }
        }

        Toast.makeText(context, "获取应用资源地址失败", Toast.LENGTH_SHORT).show();
        return null;
    }

    public CredentialBean getThisCredentialBean() {
        return thisCredentialBean;
    }

    public Map<String, AddressResponseBean> getAddressMap() {
        return addressMap;
    }


    public void setConfigFile(ConfigFileBean configFileBean) {
        this.configFileBean = configFileBean;
    }

    public ConfigFileBean getConfigFileBean() {
        return configFileBean;
    }

    private Bundle testData() {
        Bundle bundle = new Bundle();
        List<AddressResponseBean> addressResponseBeanList = new ArrayList<>();
        AddressResponseBean addressResponseBean = new AddressResponseBean();
        addressResponseBean.setResourceAddress("http://192.168.39.2:8080/endless");
        addressResponseBean.setResourceId("1");
        addressResponseBean.setResourceRegionalismCode("123");
        addressResponseBean.setResourceServiceType("123");
        addressResponseBeanList.add(addressResponseBean);
        bundle.putInt("resultCode", 0);
        bundle.putString("resourceList", gson.toJson(addressResponseBeanList));
        bundle.putString("messageId", "100002");
        return bundle;
    }

    private Bundle testData1() {
        Bundle bundle = new Bundle();
        bundle.putString("messageId", "100001");
        bundle.putString("appCredential", "appCredential");
        bundle.putString("userCredential", "userCredential");
        return bundle;
    }
}
