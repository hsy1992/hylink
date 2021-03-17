package cn.net.hylink.epsprint;

import android.content.Context;
import android.text.TextUtils;

import com.epson.isv.eprinterdriver.Ctrl.EPSetting;
import com.epson.isv.eprinterdriver.Ctrl.EPrintManager;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;

/**
 * @author haosiyuan
 * @date 2021/3/16 2:28 PM
 * info :
 */
public class EspPrinterManager implements IEspPrinter {

    private EspPrinterSearcher espPrinterSearcher;
    private Context mContext;

    private static class EspPrinterManagerInstance {
        private static EspPrinterManager espPrinterManager = new EspPrinterManager();
    }

    public static EspPrinterManager getInstance() {
        return EspPrinterManagerInstance.espPrinterManager;
    }

    static {
        System.loadLibrary("jpeg-9d");
    }

    void initPrinter(Context context) {
        this.mContext = context;
        espPrinterSearcher = new EspPrinterSearcher();
    }

    @Override
    public void print(String imagePath, IPrinterStateListener stateListener) {

        if (stateListener == null) {
            throw new IllegalArgumentException("stateListener can not be null");
        }

        if (TextUtils.isEmpty(imagePath)) {
            stateListener.printerFail(Constant.PRINT_PATH_ERROR,"imagePath can not be null");
            return;
        }

        if (espPrinterSearcher == null || espPrinterSearcher.getPrinter() == null) {
            stateListener.printerFail(Constant.PRINT_FOUND_ERROR,"printer can not found");
            return;
        }

        EPSetting.instance().setSelEpsPrinter(espPrinterSearcher.getPrinter());
        if(EPrintManager.instance().isPrintBusy()) {
            stateListener.printerFail(Constant.PRINT_BUSY_ERROR,"print is busy");
            return;
        }
        EPSetting setting = EPSetting.instance();
        int mediaSize = PageAttribute.MediaSizeID.EPS_MSID_A4;
        int mediaType = PageAttribute.MediaTypeID.EPS_MTID_PLAIN;
        int printQuality = PageAttribute.PrintQuality.EPS_MQID_NORMAL;
        int colorMode = EPSetting.COLOR_MODE_COLOR;
        PageAttribute epPageAttri = new PageAttribute(mediaSize, mediaType, printQuality);
        setting.setSelPageAttri(epPageAttri);
        setting.setPrintDirection(EPSetting.PRINT_DIR_BI);
        setting.setColorMode(colorMode);
        setting.setPaperSource(EPSetting.PAPER_SOURCE_AUTO);
        setting.setBorderless(false);
        setting.setDuplexPrint(false);
        setting.setTotalPages(1);
        setting.setTemporaryImageFilePath(mContext.getCacheDir().getAbsolutePath() + "/temp.jpg");


        EspPrintListener espPrintListener = new EspPrintListener(imagePath, stateListener);
        EPrintManager.instance().addPrintListener(espPrintListener);
        EPrintManager.instance().startPrint();
    }

    @Override
    public void stopPrint() {
        EPrintManager.instance().cancelPrint();
    }

    @Override
    public void findPrint() {
        espPrinterSearcher.findPrinter();
    }
}
