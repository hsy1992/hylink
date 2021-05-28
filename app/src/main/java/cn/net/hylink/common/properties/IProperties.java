package cn.net.hylink.common.properties;

/**
 * @ClassName IProperties
 * @Description TODO
 * @Author haosiyuan
 * @Date 2021/5/28 14:12
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
