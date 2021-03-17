package cn.net.hylink.epsprint;

/**
 * @author haosiyuan
 * @date 2021/3/16 2:13 PM
 * info : 打印接口
 */
interface IEspPrinter {

    /**
     * 打印
     * @param imagePath 图片路径
     * @param stateListener 打印监听
     */
    void print(String imagePath, IPrinterStateListener stateListener);

    /**
     * 停止打印
     */
    void stopPrint();

    /**
     * 查找打印机
     */
    void findPrint();

}
