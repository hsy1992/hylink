package cn.net.hylink.wuhai;

/**
 * @author haosiyuan
 * @date 2020/12/29 2:22 PM
 * info :
 */
public class WuHaiConfig {

    private String appToken;

    /**
     * 申请时间
     */
    private String date;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
