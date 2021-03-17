package cn.net.hylink.epsprint;

/**
 * @author haosiyuan
 * @date 2021/3/16 2:54 PM
 * info : 打印状态监听
 */
public interface IPrinterStateListener {

    void printerFail(int state, String message);

    void printerSuccess();
}
