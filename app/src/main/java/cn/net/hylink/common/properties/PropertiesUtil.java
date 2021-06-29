package cn.net.hylink.common.properties;

import android.os.Environment;

import java.util.HashMap;

/**
 * @ClassName PropertiesUtil
 * @Description Properties 操作
 * @Author haosiyuan
 * @Date 2021/5/28 13:51
 * @Version 1.0
 */
public class PropertiesUtil {

    private final static String TAG = "PropertiesUtil";

    private static volatile PropertiesUtil instance;

    public static final String CONFIG_PATH = Environment.getExternalStorageDirectory() + "/config.properties";

    //缓存 Properties
    private final HashMap<String, PropertiesOperation> propertiesHashMap = new HashMap<>();

    public static PropertiesUtil getInstance() {
        if (instance == null) {
            synchronized (PropertiesUtil.class) {
                if (instance == null) {
                    instance = new PropertiesUtil();
                }
            }
        }
        return instance;
    }

    private PropertiesUtil() {
    }

    public PropertiesOperation getProperties(String key) {
        synchronized (propertiesHashMap) {
            if (propertiesHashMap.containsKey(key)) {
                return propertiesHashMap.get(key);
            }
            PropertiesOperation propertiesOperation = new PropertiesOperation(key);
            propertiesOperation.open();
            propertiesHashMap.put(key, propertiesOperation);

            return propertiesOperation;
        }
    }
}
