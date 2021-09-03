package cn.net.hylink.zhejiang.config;
import android.content.Context;
import android.os.Environment;

import java.util.HashMap;

/**
 * @ClassName PropertiesUtil
 * @Description TODO
 * @Author haosiyuan
 * @Date 2021/8/23 17:11
 * @Version 1.0
 */
public class PropertiesUtil {

    private final static String TAG = "PropertiesUtil";

    private static volatile PropertiesUtil instance;

    public static final String CONFIG_PATH = Environment.getExternalStorageDirectory() + "/sdAppId.txt";

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

    public PropertiesOperation getProperties(Context mContext, String key) {
        synchronized (propertiesHashMap) {
            if (propertiesHashMap.containsKey(key)) {
                return propertiesHashMap.get(key);
            }
            PropertiesOperation propertiesOperation = new PropertiesOperation(mContext, key);
            propertiesOperation.open();
            propertiesHashMap.put(key, propertiesOperation);

            return propertiesOperation;
        }
    }
}
