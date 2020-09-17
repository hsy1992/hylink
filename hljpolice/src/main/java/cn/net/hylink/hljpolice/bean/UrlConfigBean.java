package cn.net.hylink.hljpolice.bean;

/**
 * @author haosiyuan
 * @date 2020/9/17 3:13 PM
 * info :
 */
public class UrlConfigBean {

    private String version;
    private String appId;
    private String orgId;
    private String networkAreaCode;
    private String packageName;

    public String getVersion() {
        return version;
    }

    public String getAppId() {
        return appId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getNetworkAreaCode() {
        return networkAreaCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public static final class UrlConfigBeanBuilder {
        private String version;
        private String appId;
        private String orgId;
        private String networkAreaCode;
        private String packageName;

        private UrlConfigBeanBuilder() {
        }

        public static UrlConfigBeanBuilder anUrlConfigBean() {
            return new UrlConfigBeanBuilder();
        }

        public UrlConfigBeanBuilder withVersion(String version) {
            this.version = version;
            return this;
        }

        public UrlConfigBeanBuilder withAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public UrlConfigBeanBuilder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public UrlConfigBeanBuilder withNetworkAreaCode(String networkAreaCode) {
            this.networkAreaCode = networkAreaCode;
            return this;
        }

        public UrlConfigBeanBuilder withPackageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public UrlConfigBean build() {
            UrlConfigBean urlConfigBean = new UrlConfigBean();
            urlConfigBean.appId = this.appId;
            urlConfigBean.orgId = this.orgId;
            urlConfigBean.packageName = this.packageName;
            urlConfigBean.networkAreaCode = this.networkAreaCode;
            urlConfigBean.version = this.version;
            return urlConfigBean;
        }
    }
}
