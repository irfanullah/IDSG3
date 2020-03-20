package TestSpecs;

import PageObj.RecentWO;
import Services.AppEnv;
import Services.General;
import TestManager.SuiteListener;
import org.testng.annotations.Test;


//@Listeners(TestManager.SuiteListener)
public class SearchWO {
    private static AppEnv appEnv = new AppEnv();
    private static General Utils;
    private RecentWO pgRecentWO;

    /**
     * This is constructor class
     */

    public SearchWO() {
        appEnv = SuiteListener.appEnv;
        Utils = General.getInstance(appEnv);
        pgRecentWO = new RecentWO(appEnv);
    }

    @Test
    public void Search_WO_By_First_Name(){
        pgRecentWO.Type_First_Name("Irfanullah");



    }
}
