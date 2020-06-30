package TestSpecs;

import PageObj.LoginPage;
import PageObj.RecentWO;
import Services.AppEnv;
import Services.General;
import Services.RestManager;
import TestManager.InputDataStream;
import TestManager.SuiteListener;
import org.testng.annotations.BeforeMethod;
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

    /**
     * This is constructor class
     */

    public SearchWO() {
        appEnv = SuiteListener.appEnv;
        Utils = General.getInstance(appEnv);
        pgRecentWO = new RecentWO(appEnv);
        loginPage = new LoginPage(appEnv);
        inputDataStream = InputDataStream.getInstance(appEnv);


    }

    @BeforeMethod
    public void BodyConfiguration(){

    }
    @Test(priority = 0)
    public void Login(){
        appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetWOAgainstStockNo());
        loginPage.LogIn(appEnv.getEmail(),appEnv.getPassword());
        appEnv.setLogInReq(false);
        appEnv.setTestPass(loginPage.IsSession_Logged_In());
        Utils.VerifyResult("Unable to Login",appEnv.isTestPass());

    }
   @Test(priority = 1)
    public void Search_WO_By_WO_Number(){
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
    public void Search_WO_By_Stock_Number(){
       Utils.StaticWait(10000);
       loginPage.Click_Work_Order_Button();
       Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
       pgRecentWO.Click_Clear_Button();
       pgRecentWO.Click_More_Filters_Button();
       pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
       pgRecentWO.Click_Search_Button();
       Utils.StaticWait(10000);
       appEnv.setTestPass(Utils.Search_Table_with_String(appEnv.getStockNumber()));
       Utils.VerifyResult("No Work Order Found Against Given Stock Number", appEnv.isTestPass());
    }
    @Test(priority = 3)
    public void Search_WO_By_First_Name(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.setTestPass(Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("No Work Order Found against the given First Name ", appEnv.isTestPass());

    }
    @Test(priority = 4)
    public void Search_WO_By_Last_Name(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.setTestPass(Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("No Work Order Found against the given Last Name ", appEnv.isTestPass());

    }

    @Test(priority = 5)
    public void Search_WO_By_Customer_Number(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(10000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.setTestPass(Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("No Work Order Found against the given Customer Number ", appEnv.isTestPass());

    }

    @Test(priority = 6)
    public void Search_Open_Work_Orders_Against_Customer_Number(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(Utils.Count_Table_Rows()>=Integer.parseInt(appEnv.getTotalOpenWOAgainstCustomerNumber())){
            appEnv.getReportManager().LogStepInfo("Total Number of Open Work Orders against Customer # "
                                                    + appEnv.getCustomerNumber() + " : " + Utils.Count_Table_Rows());
            Utils.VerifyResult("Passed Open Work Orders against Customer Number", true);
        }

        else
            Utils.VerifyResult("Total Number of Open Work Orders Found are : " + Utils.Count_Table_Rows() +
                    "Total Number of open work orders in the system are : " + appEnv.getTotalOpenWO(), false);

    }

    @Test(priority = 7)
    public void Search_Open_Work_Orders_Against_Customer_Number_And_Stock_Number(){
        Utils.StaticWait(10000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(Utils.Count_Table_Rows()==Integer.parseInt(appEnv.getTotalOpenWOAgainstCustomerNumberAndStockNumber())){
            appEnv.getReportManager().LogStepInfo("Total Number of Open Work Orders against Customer # "
                                                    + appEnv.getCustomerNumber() +
                                                    " And Stock Number " + appEnv.getStockNumber() + " : "
                                                    + Utils.Count_Table_Rows());
            Utils.VerifyResult("Passed Open Work Orders against Customer Number and Stock Number", true);
        }

        else
            Utils.VerifyResult("Total Number of Open Work Orders Found are : " + Utils.Count_Table_Rows() +
                    "Total Number of open work orders in the system are : " + appEnv.getTotalOpenWO(), false);

    }

    @Test(priority = 8)
    public void Search_Completed_Work_Orders_Within_Ten_Days(){
        Utils.StaticWait(5000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Click_Include_Open_WO();
        pgRecentWO.Type_Completed_With_in_Days("10");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(Utils.Count_Table_Rows()>=Integer.parseInt(appEnv.getTotalCompletedWO()))
            Utils.VerifyResult("Total Completed Work Orders Within Ten Days : " + Utils.Count_Table_Rows(), true);
        else
            Utils.VerifyResult("Total Completed Work Orders Within Ten Days Found are : " + Utils.Count_Table_Rows() +
                    "Total Completed Work Orders Within Ten Days in the system are : " + appEnv.getTotalCompletedWO(), false);

    }



    @Test(priority = 9)
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
