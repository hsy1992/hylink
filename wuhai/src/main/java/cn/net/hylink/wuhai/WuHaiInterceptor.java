package cn.net.hylink.wuhai;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author haosiyuan
 * @date 2020/12/29 2:17 PM
 * info :
 */
public class WuHaiInterceptor implements Interceptor {

    public static final String TAG = WuHaiInterceptor.class.getSimpleName();

    private File file = new File(Environment.getExternalStorageDirectory() + "/wuhai.config");

    private String appToken;

    private Context context;

    /**
     * 签到地址
     */
    public static final String AUTHORITY = "com.login.provider.LoginContentProvider";
    public static final Uri CONTENT_URI_FIRST = Uri.parse("content://" + AUTHORITY + "/first");

    private static final String DEFAULT_CODE = "030163";

    public WuHaiInterceptor(Context context) {
        this.context = context.getApplicationContext();
        try {
            context.getContentResolver().registerContentObserver(CONTENT_URI_FIRST,
                    true, new DataObserver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if (DataUtil.getSignData(context) == null) {
//            return new Response.Builder()
//                    .code(500)
//                    .message("未登录")
//                    .protocol(Protocol.HTTP_2)
//                    .body(ResponseBody.create(MediaType.get("application/json; charset=utf-8"), ""))
//                    .request(chain.request())
//                    .build();

            //未登录使用默认token
            DataUtil.userEntity = new UserBean();
            DataUtil.userEntity.setKqrbm(DEFAULT_CODE);
        }

        if (appToken == null || "".equals(appToken)) {

            Log.i(TAG, "appToken == null");

            if (! file.exists()) {
                Log.i(TAG, "file.exists()---" + file.exists());
                file.createNewFile();
                updateAppToken();
            } else {

                String json = FileUtil.readFileContent(file);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String appToken = jsonObject.getString("appToken");
                    String date = jsonObject.getString("date");
                    if (Long.parseLong(date) + 24 * 60 * 60 * 1000L > System.currentTimeMillis() ) {
                        this.appToken = appToken;
                        Log.i(TAG, "复用---token" + appToken);
                    } else {
                        updateAppToken();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    updateAppToken();
                }
            }
        } else {
            Log.i(TAG, "复用---token" + appToken);
        }

        if (appToken == null || "".equals(appToken)) {
            return new Response.Builder()
                    .code(500)
                    .message("未登录")
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), ""))
                    .request(chain.request())
                    .build();

        }

        request = request.newBuilder()
                .addHeader("appToken", this.appToken)
                .build();

        return chain.proceed(request);
    }

    private void updateAppToken() {
        if (DataUtil.getSignData(context) != null) {
            Log.i(TAG, "更新---token" + appToken);
            WuHaiConfig wuHaiConfig = createConfig(DataUtil.getSignData(context).getKqrbm());
            this.appToken = wuHaiConfig.getAppToken();
            FileUtil.writeFile(file, wuHaiConfig);
        }
    }

    private WuHaiConfig createConfig(String userName) {
        Calendar calendar = Calendar.getInstance();
        Date issuedAt = new Date();
        Calendar issuedAtCalendar = Calendar.getInstance();
        issuedAtCalendar.setTime(issuedAt);
        issuedAtCalendar.add(Calendar.MINUTE, -30);

        calendar.setTime(issuedAt);
        calendar.add(Calendar.DATE, 3);
        String appToken = "";
        Date expiresAt = calendar.getTime();
        try {
            appToken = createAppToken("0QuQw6Zj", "e60bd52ac43e4e76a4988f4c40c44f7a", userName,
                    "1", "2", "2", issuedAtCalendar.getTime(), expiresAt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WuHaiConfig wuHaiConfig = new WuHaiConfig();
        wuHaiConfig.setAppToken(appToken);
        wuHaiConfig.setDate(String.valueOf(issuedAtCalendar.getTime().getTime()));
        Log.i(TAG, "issuedAtTime:" + issuedAtCalendar.getTime().getTime());
        return wuHaiConfig;
    }

    public String createAppToken(String appKey, String appSecret, String username, String appVersion,
                                  String appZone, String appType, Date issuedAt, Date expiresAt) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("appKey", appKey);
        map.put("appVersion", appVersion);
        map.put("appZone", appZone);
        map.put("appType", appType);

        String key = Base64.encodeToString(appSecret.getBytes(), 0);
        String appToken = Jwts.builder().addClaims(map).setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS256, key).setId(UUID.randomUUID().toString()).setIssuer("hebmpp.org")
                .setIssuedAt(issuedAt).setExpiration(expiresAt).compact();
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(appToken);
        }
        catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return appToken;
    }

    private class DataObserver extends ContentObserver {

        public DataObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            DataUtil.refreshData(context);
            updateAppToken();
        }
    }
}
