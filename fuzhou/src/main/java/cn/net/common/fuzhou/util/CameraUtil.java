package cn.net.common.fuzhou.util;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.net.common.fuzhou.bean.CameraConfigureBean;
import cn.net.common.fuzhou.constants.Constants;
import cn.net.common.fuzhou.database.AppDatabase;
import cn.net.common.fuzhou.database.entity.CameraSettingEntity;
import cn.net.common.fuzhou.database.entity.CaptureEntity;


/**
 * @author haosiyuan
 * @date 2020-05-07 17:09
 * info : 摄像头配置
 */
public class CameraUtil {

    private static volatile CameraUtil instance;

    private Gson gson;

    public static CameraUtil getInstance() {

        if (instance == null) {
            synchronized (CameraUtil.class) {
                if (instance == null) {
                    instance = new CameraUtil();
                }
            }
        }
        return instance;
    }

    private CameraUtil() {
        gson = new Gson();
    }

    private CameraConfigureBean cameraConfigureBean;

    private List<CameraConfigureBean.CameraListBean> cameraList = new ArrayList<>();

    public void initData(Context context) {

        cameraConfigureBean = new CameraConfigureBean();

        CaptureEntity captureType = AppDatabase.getInstance(context).getCaptureDao().getCaptureEntity();

        if (captureType == null) {
//            Toast.makeText(context, "抓拍未配置", Toast.LENGTH_SHORT).show();
            return;
        } else if (captureType.getType() == Constants.HYLINK1) {
            String content = captureType.getContent();

            try {
                CameraConfigureBean.RedisBean redisBean = gson.fromJson(content, CameraConfigureBean.RedisBean.class);
                cameraConfigureBean.setRedis(redisBean);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "抓拍配置有误", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (captureType.getType() == Constants.HYLINK1803) {
            String content = captureType.getContent();

            try {
                CameraConfigureBean.SnapBean snapBean = gson.fromJson(content, CameraConfigureBean.SnapBean.class);
                cameraConfigureBean.setSnap(snapBean);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "抓拍配置有误", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        CameraSettingEntity cameraSettingEntity = AppDatabase.getInstance(context).getCameraSettingDao().getCameraSettingEntity();

        if (cameraSettingEntity == null) {
            Toast.makeText(context, "摄像头未配置", Toast.LENGTH_SHORT).show();
            return;
        } else {
            CameraConfigureBean.CloudBean cloudBean = gson.fromJson(cameraSettingEntity.getCloud(),
                    CameraConfigureBean.CloudBean.class);
            CameraConfigureBean.CarBean carBean = gson.fromJson(cameraSettingEntity.getCar(),
                    CameraConfigureBean.CarBean.class);
            List<CameraConfigureBean.CameraListBean> cameraList = gson.fromJson(cameraSettingEntity.getCameraList(),
                    new TypeToken<List<CameraConfigureBean.CameraListBean>>() {
                    }.getType());
            cameraConfigureBean.setCloud(cloudBean);
            cameraConfigureBean.setCarBean(carBean);
            cameraConfigureBean.setCameraList(cameraList);
            this.cameraList = cameraConfigureBean.getCameraList();
        }
    }

    public String getLocationNameByIp(String cameraIp) {

        if (cameraList == null || cameraList.size() == 0) {
            return "";
        }
        for (CameraConfigureBean.CameraListBean key : cameraList) {
            if (key.getIp().equals(cameraIp)) {
                return key.getName();
            }
        }
        return "云台";
    }

    /**
     * rtsp://hylink:a12345678@192.168.1.11:554/c=0&s=1 右前
     * 字符串后面s=1是主码流，s=2是辅码流
     *
     * @param locationName
     * @return
     */
    public String getCameraRtsp(String locationName) {
        if (cameraList == null || cameraList.size() > 0) {
            return "";
        }

        for (CameraConfigureBean.CameraListBean key : cameraList) {
            if (key.getName().equals(locationName)) {
                return key.getRtsp();
            }
        }
        return "";
    }

    public List<CameraConfigureBean.CameraListBean> getCameraList() {
        return cameraList;
    }

    public CameraConfigureBean getCameraConfigureBean() {
        return cameraConfigureBean;
    }
}
