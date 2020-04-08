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
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.VerifyResult("Work Order Not Found", pgRecentWO.Search_Table_with_String(appEnv.getWorkOrderNumber()));

    }
   @Test(priority = 1)
    public void Search_WO_By_Stock_Number(){
       appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       pgRecentWO.Click_Clear_Button();
       pgRecentWO.Click_More_Filters_Button();
       pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
       pgRecentWO.Click_Search_Button();
       Utils.StaticWait(10000);
       Utils.VerifyResult("No Work Order Found Against Given Stock Number", pgRecentWO.Search_Table_with_String(appEnv.getStockNumber()));
    }
    @Test(priority = 2)
    public void Search_WO_By_First_Name(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.VerifyResult("No Work Order Found against the given First Name ", pgRecentWO.Find_Customer(appEnv.getFirstName()));

    }
    @Test(priority = 3)
    public void Search_WO_By_Last_Name(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.VerifyResult("No Work Order Found against the given Last Name ", pgRecentWO.Find_Customer(appEnv.getLastName()));

    }

    @Test(priority = 4)
    public void Search_WO_By_Customer_Number(){
        appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.VerifyResult("No Work Order Found against the given Customer Number ", pgRecentWO.Find_Customer(appEnv.getCustomerNumber()));

    }


    @Test(priority = 5)
    public void Debug_Test_Cases_To_See_Table_Data_Loaded(){
       appEnv.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pgRecentWO.Click_Search_Button();
        pgRecentWO.Display_Element_Coordinates("595662");
        pgRecentWO.Dislay_Row_Elements();
        Utils.StaticWait(10000);
    }

}
