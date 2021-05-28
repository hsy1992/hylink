package cn.net.hylink.zhejiang;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Appkey
 * @Description app id
 * @Author haosiyuan
 * @Date 2021/5/28 15:50
 * @Version 1.0
 */
public interface AppKey {

    //钱塘 0
    String QIAN_TANG = "f6080108f33a3333aa258610648f1824";

    //拱墅 1
    String GONG_SHU = "ef4174f518263858bf154ecbd362950d";

    //萧山 2
    String XIAO_SHAN = "8316f9c1c3a4398391e48fae52881214";

    //温州 3
    String WEN_ZHOU = "7f74181fdeb3304999df01bd3a20c754";

    //嘉兴 4
    String JIA_XING = "9ad1d48f248331bca71b4c0933dd9dfd";

    //长兴 5
    String CHANG_XING = "dfad4ae9ff023ad48fbb3a521a3220c8";

    //柯城 6
    String KE_CHENG = "c6fd8e0fd53c3605bde31fe16c79c9eb";

    List<String> APP_IDS = Arrays.asList(
            QIAN_TANG, GONG_SHU, XIAO_SHAN, WEN_ZHOU, JIA_XING, CHANG_XING, KE_CHENG
    );

}
