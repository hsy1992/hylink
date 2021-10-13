package cn.net.common.fuzhou.bean;

import java.util.List;

/**
 * @author haosiyuan
 * @date 2020-06-09 10:04
 * info : 摄像头配置信息
 */
public class CameraConfigureBean {

    /**
     * cloud : {"ip":"192.168.104.64","port":8000,"user":"admin","password":"a1234567"}
     * snap : {"ip":"192.168.104.65","port":8000,"user":"admin","password":"a1234567"}
     * redis : {"ip":"192.168.1.100","port":6379}
     * cameraList : [{"name":"右方","type":0,"ip":"192.168.1.15","rtsp":"rtsp://admin:admin@192.168.1.15:554/c=0&s=1"},{"name":"左后","type":0,"ip":"192.168.1.12","rtsp":"rtsp://admin:admin@192.168.1.12:554/c=0&s=1"},{"name":"右后","type":0,"ip":"192.168.1.14","rtsp":"rtsp://admin:admin@192.168.1.14:554/c=0&s=1"},{"name":"后方","type":0,"ip":"192.168.1.13","rtsp":"rtsp://admin:admin@192.168.1.13:554/c=0&s=1"},{"name":"左前","type":0,"ip":"192.168.1.11","rtsp":"rtsp://admin:admin@192.168.1.11:554/c=0&s=1"},{"name":"右前","type":0,"ip":"192.168.1.16","rtsp":"rtsp://admin:admin@192.168.1.16:554/c=0&s=1"}]
     */

    private CloudBean cloud;
    private SnapBean snap;
    private RedisBean redis;
    private CarBean carBean;
    private List<CameraListBean> cameraList;

    public CloudBean getCloud() {
        return cloud;
    }

    public void setCloud(CloudBean cloud) {
        this.cloud = cloud;
    }

    public SnapBean getSnap() {
        return snap;
    }

    public void setSnap(SnapBean snap) {
        this.snap = snap;
    }

    public RedisBean getRedis() {
        return redis;
    }

    public void setRedis(RedisBean redis) {
        this.redis = redis;
    }

    public List<CameraListBean> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<CameraListBean> cameraList) {
        this.cameraList = cameraList;
    }

    public CarBean getCarBean() {
        return carBean;
    }

    public void setCarBean(CarBean carBean) {
        this.carBean = carBean;
    }

    public static class CarBean {

        private String ip;
        private String rtsp;

        public CarBean(String ip, String rtsp) {
            this.ip = ip;
            this.rtsp = rtsp;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getRtsp() {
            return rtsp;
        }

        public void setRtsp(String rtsp) {
            this.rtsp = rtsp;
        }
    }

    public static class CloudBean {
        /**
         * ip : 192.168.104.64
         * port : 8000
         * user : admin
         * password : a1234567
         */

        private String ip;
        private int port;
        private String user;
        private String password;
        private String rtsp;
        private String code;
        private int type;

        public CloudBean(String ip, int port, String user, String password, String rtsp, String code, int type) {
            this.ip = ip;
            this.port = port;
            this.user = user;
            this.password = password;
            this.rtsp = rtsp;
            this.code = code;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRtsp() {
            return rtsp;
        }

        public void setRtsp(String rtsp) {
            this.rtsp = rtsp;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SnapBean {
        /**
         * ip : 192.168.104.65
         * port : 8000
         * user : admin
         * password : a1234567
         */

        private String ip;
        private int port;
        private String user;
        private String password;

        public SnapBean(String ip, int port, String user, String password) {
            this.ip = ip;
            this.port = port;
            this.user = user;
            this.password = password;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RedisBean {
        /**
         * ip : 192.168.1.100
         * port : 6379
         */

        private String ip;
        private int port;

        public RedisBean(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public static class CameraListBean {
        /**
         * name : 右方
         * type : 0
         * ip : 192.168.1.15
         * rtsp : rtsp://admin:admin@192.168.1.15:554/c=0&s=1
         */

        private int id;
        private String name;
        private int type;
        private String ip;
        private String rtsp;
        private String code;
        private boolean use;

        public CameraListBean(int id, String name, int type, String ip, String rtsp, String code, boolean use) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.ip = ip;
            this.rtsp = rtsp;
            this.code = code;
            this.use = use;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isUse() {
            return use;
        }

        public void setUse(boolean use) {
            this.use = use;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getRtsp() {
            return rtsp;
        }

        public void setRtsp(String rtsp) {
            this.rtsp = rtsp;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class NvrBean {
        /**
         * ip : 192.168.104.64
         * port : 8000
         * user : admin
         * password : a1234567
         */

        private String ip;
        private int port;
        private String user;
        private String password;

        public NvrBean(String ip, int port, String user, String password) {
            this.ip = ip;
            this.port = port;
            this.user = user;
            this.password = password;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
