package TestSpecs;

import PageObj.LoginPage;
import PageObj.RecentWO;
import PageObj.WODetails;
import Services.AppEnv;
import Services.General;
import Services.RestManager;
import TestManager.InputDataStream;
import TestManager.SuiteListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestManager.SuiteListener.class)
public class SearchWO {
    private static AppEnv appEnv = new AppEnv();
    private static General Utils;
    private RecentWO pgRecentWO;
    private LoginPage loginPage;
    private RestManager restManager;
    private InputDataStream inputDataStream;
    private WODetails woDetails;

    /**
     * This is constructor class
     */

    public SearchWO() {
        appEnv = SuiteListener.appEnv;
        Utils = General.getInstance(appEnv);
        pgRecentWO = new RecentWO(appEnv);
        loginPage = new LoginPage(appEnv);
        inputDataStream = InputDataStream.getInstance(appEnv);
        woDetails = WODetails.getInstance(appEnv);


    }

    @Test(priority = 0)
    public void Login(){
        loginPage.LogIn(appEnv.getEmail(),appEnv.getPassword());
        appEnv.setLogInReq(false);
        appEnv.setTestPass(loginPage.IsSession_Logged_In());
        Utils.VerifyResult("Unable to Login",appEnv.isTestPass());

    }
   @Test(priority = 1)
    public void Search_Open_WO_Against_WO_Number(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.setTestPass(Utils.Search_Table_with_String(appEnv.getWorkOrderNumber()));
        Utils.VerifyResult("Work Order Not Found", appEnv.isTestPass());
    }
   @Test(priority = 2)
    public void Search_Open_WO_Against_Stock_Number(){
       appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstStockNo()));
       Utils.StaticWait(10000);
       loginPage.Click_Work_Order_Button();
       Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
       pgRecentWO.Click_Clear_Button();
       pgRecentWO.Click_More_Filters_Button();
       pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
       pgRecentWO.Click_Search_Button();
       Utils.StaticWait(10000);
       appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());

       appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstStockNumber() &&
               Utils.Search_Table_with_String(appEnv.getStockNumber()) );
       Utils.VerifyResult("No Work Order Found Against Given Stock Number", appEnv.isTestPass());
    }
    @Test(priority = 3)
    public void Search_WO_Against_First_Name(){
        appEnv.setTotalWOAgainstFirstName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstName()));
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstName() && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Orders Loaded in GUI Does Not Match with Number of Work Orders against the given First Name ", appEnv.isTestPass());

    }
    @Test(priority = 4)
    public void Search_Open_WO_Against_Last_Name(){
        appEnv.setTotalWOAgainstLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstLastName()));
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstLastName() && Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("Work Orders Loaded in GUI Does Not Match with Number of Work Orders against the given Last Name ", appEnv.isTestPass());


    }

    @Test(priority = 5)
    public void Search_Completed_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstCustomerNumber()));
        Utils.StaticWait(5000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Click_Include_Open_WO();
        pgRecentWO.Type_Completed_With_in_Days(appEnv.getCompletedWithinDays());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Completed WO Loaded in GUI does not match with Completed WO in the system against Customer Number", appEnv.isTestPass());

    }

    @Test(priority = 6)
    public void Search_Open_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstCustomerNo()));
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber()
                && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Orders loaded does not match with Work Orders in the system against given customer", appEnv.isTestPass());
    }

    @Test(priority = 7)
    public void Search_Open_WO_Against_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstWONumberAndStockNumber()));
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumberAndStockNumber()
                && Utils.Find_Customer(appEnv.getCustomerNumber()) && Utils.Search_Table_with_String(appEnv.getStockNumber()));
        Utils.VerifyResult("Work Orders loaded does not match with Open Work Orders in the system against given customer and stock Number", appEnv.isTestPass());
    }

    @Test(priority = 8)
    public void Search_Completed_Work_Orders_Within_Given_Days_Against_Stock_Number(){
        appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstStockNumber()));
        Utils.StaticWait(5000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Click_Include_Open_WO();
        pgRecentWO.Type_Completed_With_in_Days(appEnv.getCompletedWithinDays());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against Stock Number", appEnv.isTestPass());

    }

    @Test(priority = 9)
    public void Search_Completed_Work_Orders_Within_Given_Days_Against_Customer_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber()));
        Utils.StaticWait(5000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Click_Include_Open_WO();
        pgRecentWO.Type_Completed_With_in_Days(appEnv.getCompletedWithinDays());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumberAndStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against Customer and Stock Number", appEnv.isTestPass());

    }



    @Test(priority = 10)
    public void Debug_Test_Cases_To_See_Table_Data_Loaded(){
        Utils.StaticWait(5000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.Display_Element_Coordinates("595662");
        Utils.Dislay_Row_Elements();
        Utils.StaticWait(10000);
    }

}
