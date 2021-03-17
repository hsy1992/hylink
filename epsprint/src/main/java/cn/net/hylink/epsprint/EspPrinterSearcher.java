package cn.net.hylink.epsprint;

import android.util.Log;

import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Ctrl.EPrintManager;
import com.epson.isv.eprinterdriver.Ctrl.ISearchPrinterListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haosiyuan
 * @date 2021/3/16 2:34 PM
 * info : 搜索打印机
 */
class EspPrinterSearcher implements ISearchPrinterListener {

    private static final int DEFAULT_TIMEOUT = 30;

    private List<EpsPrinter> printerList;

    private boolean isSearching = false;

    EspPrinterSearcher() {
        printerList = new ArrayList<>();
        EPrintManager.instance().addSearchListener(this);
        EPrintManager.instance().findPrinter(DEFAULT_TIMEOUT);
    }

    void findPrinter() {
        EPrintManager.instance().findPrinter(DEFAULT_TIMEOUT);
    }

    @Override
    public void onSearchBegin() {
        isSearching = true;
    }

    @Override
    public void onFindPrinter(EpsPrinter epsPrinter) {
        Log.i("EspPrinterSearcher", "find printer   " + epsPrinter.getSerialNo());
        printerList.add(epsPrinter);
    }

    @Override
    public void onSearchFinished(int i) {
        isSearching = false;
    }

    EpsPrinter getPrinter() {
        if (printerList != null && printerList.size() > 0) {
            return printerList.get(0);
        }
        return null;
    }
}
