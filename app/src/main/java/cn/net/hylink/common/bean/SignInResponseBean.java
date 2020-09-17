package cn.net.hylink.common.bean;

import java.util.List;

/**
 * @author haosiyuan
 * @date 2020-03-27 15:33
 * info : 登录返回
 */
public class SignInResponseBean {

    /**
     * reason : null
     * result : {"list":[{"xh":1,"user_id":"230125199209250518","gxdwdm":"230000000000","gxdwmc":"黑龙江省","kqrbm":"33222006","kqrxm":"张泽恒","rltpdz":"http://192.168.41.249:9000/img/2,05573a8dc7fd20","idcard":"230125199209250518","sign_identification":"qd","sign_time":"2019-12-12 11:20:30","imei":"tempHylinkCid","carno":"黑A66666","effective":"1","lat":"0","lon":"0"}]}
     */

    private Object reason;
    private ResultBean result;

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * xh : 1
             * user_id : 230125199209250518
             * gxdwdm : 230000000000
             * gxdwmc : 黑龙江省
             * kqrbm : 33222006
             * kqrxm : 张泽恒
             * rltpdz : http://192.168.41.249:9000/img/2,05573a8dc7fd20
             * idcard : 230125199209250518
             * sign_identification : qd
             * sign_time : 2019-12-12 11:20:30
             * imei : tempHylinkCid
             * carno : 黑A66666
             * effective : 1
             * lat : 0
             * lon : 0
             */

            private int xh;
            private String user_id;
            private String gxdwdm;
            private String gxdwmc;
            private String kqrbm;
            private String kqrxm;
            private String rltpdz;
            private String idcard;
            private String sign_identification;
            private String sign_time;
            private String imei;
            private String carno;
            private String effective;
            private String lat;
            private String lon;

            public int getXh() {
                return xh;
            }

            public void setXh(int xh) {
                this.xh = xh;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getGxdwdm() {
                return gxdwdm;
            }

            public void setGxdwdm(String gxdwdm) {
                this.gxdwdm = gxdwdm;
            }

            public String getGxdwmc() {
                return gxdwmc;
            }

            public void setGxdwmc(String gxdwmc) {
                this.gxdwmc = gxdwmc;
            }

            public String getKqrbm() {
                return kqrbm;
            }

            public void setKqrbm(String kqrbm) {
                this.kqrbm = kqrbm;
            }

            public String getKqrxm() {
                return kqrxm;
            }

            public void setKqrxm(String kqrxm) {
                this.kqrxm = kqrxm;
            }

            public String getRltpdz() {
                return rltpdz;
            }

            public void setRltpdz(String rltpdz) {
                this.rltpdz = rltpdz;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getSign_identification() {
                return sign_identification;
            }

            public void setSign_identification(String sign_identification) {
                this.sign_identification = sign_identification;
            }

            public String getSign_time() {
                return sign_time;
            }

            public void setSign_time(String sign_time) {
                this.sign_time = sign_time;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getCarno() {
                return carno;
            }

            public void setCarno(String carno) {
                this.carno = carno;
            }

            public String getEffective() {
                return effective;
            }

            public void setEffective(String effective) {
                this.effective = effective;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }
        }
    }
}
