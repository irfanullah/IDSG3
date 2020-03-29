package TestSpecs;

import PageObj.RecentWO;
import Services.AppEnv;
import Services.General;
import TestManager.SuiteListener;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


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



    @Test(priority = 0)
    public void Search_WO_By_WO_Number(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(1000);

    }
    @Test(priority = 1)
    public void Search_WO_By_WO_Number_And_First_Name(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_Search_Button();
        pgRecentWO.Click_More_Filters_Button();
        Utils.StaticWait(1000);
        pgRecentWO.Click_Less_Filters_Button();
        Utils.StaticWait(1000);
    }

    @Test(priority = 2)
    public void Search_WO_By_First_Name_Last_Name_Phone_Number(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Type_Phone_Number(appEnv.getPhoneNumber());
       // pgRecentWO.Click_Search_Button();
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Load_Table_Data();
        Utils.StaticWait(10000);

    }

}
