package cn.net.hylink.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import cn.net.hylink.common.bean.ConfigureBean;

/**
 * @author haosiyuan
 * @date 2020-04-07 10:17
 * info : assets 操作
 */
public class AssetsUtil {

    public static void copyAssets(Context context, String path) {
        File model = new File(path);
        File file = new File(Environment.getExternalStorageDirectory(), model.getName());
        if (file.exists() && file.length() > 1) {
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = context.getAssets().open(path);
            int len;
            byte[] b = new byte[2048];
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConfigureBean getAssets(Context context, String path) {

        File model = new File(path);
        Gson gson = new Gson();
        File file = new File(Environment.getExternalStorageDirectory(), model.getName());
        if (file.exists()) {
            try {
                FileInputStream is = new FileInputStream(file);
                InputStreamReader inputReader = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(inputReader);
                StringBuffer content = new StringBuffer();
                //分行读取
                String line;
                while (( line = bufferReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                is.close();
                return gson.fromJson(content.toString(), new TypeToken<ConfigureBean>(){}.getType());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return gson.fromJson(getJson(path, context), new TypeToken<ConfigureBean>(){}.getType());
        }
    }

    /**
     * 读取assets本地json
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
