package cn.net.hylink.zhejiang.config;

/**
 * @ClassName IProperties
 * @Description TODO
 * @Author haosiyuan
 * @Date 2021/8/23 17:12
 * @Version 1.0
 */
public interface IProperties {
    void open();

    void commit(IPropertiesCommit iPropertiesCommit);

    void clear();

    void writeString(String name, String value);

    String readString(String name, String defaultValue);

    void writeInt(String name, int value);

    int readInt(String name, int defaultValue);
}
