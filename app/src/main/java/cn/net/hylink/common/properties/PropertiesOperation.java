package cn.net.hylink.common.properties;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @ClassName PropertiesOperation
 * @Description Properties操作
 * @Author haosiyuan
 * @Date 2021/5/28 14:14
 * @Version 1.0
 */
public class PropertiesOperation implements IProperties {

    private final static String TAG = "PropertiesOperation";

    private String mPath;
    private Properties props;

    public PropertiesOperation(String mPath) {
        this.mPath = mPath;
    }

    @Override
    public void open() {
        File file = new File(mPath);
        if (!file.exists()) {
            Log.i(TAG, "未找到配置文件");
            return;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            props = new Properties();
            props.load(new InputStreamReader(in, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void commit(IPropertiesCommit iPropertiesCommit) {
        try {
            File file = new File(mPath);
            OutputStream os = new FileOutputStream(file);
            props.store(os, "");
            os.close();
            if (iPropertiesCommit != null) {
                iPropertiesCommit.onSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        if (props != null) {
            props.clear();
        }
    }

    @Override
    public void writeString(String name, String value) {
        if (props != null) {
            props.setProperty(name, value);
        }
    }

    @Override
    public String readString(String name, String defaultValue) {
        if (props != null) {
            return props.getProperty(name, defaultValue);
        }
        return defaultValue;
    }

    @Override
    public void writeInt(String name, int value) {
        if (props != null) {
            props.setProperty(name, String.valueOf(value));
        }
    }

    @Override
    public int readInt(String name, int defaultValue) {
        try {
            if (props != null) {
                return Integer.parseInt(props.getProperty(name, String.valueOf(defaultValue)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public Properties getProps() {
        return props;
    }
}
