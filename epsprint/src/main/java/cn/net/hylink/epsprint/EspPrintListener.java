package cn.net.hylink.epsprint;

import android.util.Log;

import com.epson.isv.eprinterdriver.Common.EpsStatus;
import com.epson.isv.eprinterdriver.Common.ServiceIntent;
import com.epson.isv.eprinterdriver.Ctrl.EPSetting;
import com.epson.isv.eprinterdriver.Ctrl.IPrintListener;

/**
 * @author haosiyuan
 * @date 2021/3/16 8:41 PM
 * info :
 */
class EspPrintListener implements IPrintListener {

    private static final String TAG = EspPrintListener.class.getSimpleName();

    private String imagePath;

    private IPrinterStateListener stateListener;

    public EspPrintListener(String imagePath, IPrinterStateListener stateListener) {
        this.imagePath = imagePath;
        this.stateListener = stateListener;
    }

    @Override
    public void onPrintBegin() {
        Log.d(TAG, "onPrintBegin");
    }

    @Override
    public String onPageBegin(int pageNum) {
        Log.d(TAG, "onPageBegin : " + pageNum + " : imagePath = " + imagePath);

        System.gc();

        return imagePath;
    }

    @Override
    public boolean onPageFinished(int finishedNum) {
        Log.d(TAG, "onPageFinished : " + finishedNum);

        int totalPageNum = EPSetting.instance().getTotalPages();
        return finishedNum <= totalPageNum;
    }

    @Override
    public void onPrintFinished(int factor) {
        Log.d(TAG, "onPrintFinished : " + factor);

        System.gc();

        switch (factor) {
            case ServiceIntent.StopFactor.PrintSuccess:
                stateListener.printerSuccess();
                break;
            default:
                stateListener.printerFail(Constant.PRINT_ERROR, "print error");
                break;
        }
    }

    @Override
    public void onPrintPause(int curNum, int pauseFactor, EpsStatus pauseStatus) {
        Log.d(TAG, "onPrintPause : " + curNum + " pauseFactor : "
                + pauseFactor + " isJobContinue : " + pauseStatus.isJobContinue() );
    }

    @Override
    public void onPrintResume() {
        Log.d(TAG, "onPrintResume");
    }

    @Override
    public void onPrintAutoContinue() {
        Log.d(TAG, "onPrintResume");
    }

    @Override
    public void onCleaningTime(int seconds) {
        Log.d(TAG, "onCleaningTimeInformation = " + seconds + " seconds");
    }
}
