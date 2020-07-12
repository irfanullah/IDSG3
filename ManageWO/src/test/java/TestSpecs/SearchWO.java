package TestSpecs;

import Drivers.Fetch_Elements;
import PageObj.LoginPage;
import PageObj.RecentWO;
import PageObj.WODetails;
import Services.AppEnv;
import Services.General;
import Services.ReTry;
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
    private Fetch_Elements fetch_elements;

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
        fetch_elements = Fetch_Elements.getInstance(appEnv);


    }

    @Test(priority = 0, retryAnalyzer = ReTry.class)
    public void Login_With_UserID_And_Logout(){
        loginPage.LogIn(appEnv.getUserID(),appEnv.getPassword());
        loginPage.Logout();
        appEnv.setLogInReq(false);
        appEnv.setTestPass(Utils.IsObjExist(fetch_elements.GetObj("name", "userId")));
        Utils.VerifyResult("Unable to Login",appEnv.isTestPass());


    }
    @Test(priority = 1, retryAnalyzer = ReTry.class)
    public void Login_With_EmailAddress(){
        loginPage.LogIn(appEnv.getEmail(),appEnv.getPassword());
        appEnv.setLogInReq(false);
        appEnv.setTestPass(loginPage.IsSession_Logged_In());
        Utils.VerifyResult("Unable to Login",appEnv.isTestPass());

    }
   @Test(priority = 2, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_WO_Number(){
        Utils.StaticWait(10000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.setTestPass(Utils.Search_Table_with_String(appEnv.getWorkOrderNumber()));
        Utils.VerifyResult("Work Order Not Found", appEnv.isTestPass());
    }

    @Test(priority = 3, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_First_Name(){
        appEnv.setTotalWOAgainstFirstName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstName()));
        Utils.StaticWait(10000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstName() && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Orders Loaded in GUI Does Not Match with Number of Work Orders against the given First Name ", appEnv.isTestPass());

    }
    @Test(priority = 4, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_Last_Name(){
        appEnv.setTotalWOAgainstLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstLastName()));
        Utils.StaticWait(10000);
        //loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstLastName() && Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("Work Orders Loaded in GUI Does Not Match with Number of Work Orders against the given Last Name ", appEnv.isTestPass());


    }
    @Test(priority = 5, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_Stock_Number(){
        appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstStockNo()));
        Utils.StaticWait(10000);
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


    @Test(priority = 6, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstCustomerNo()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstCustomerNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber()
                && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Orders loaded does not match with Work Orders in the system against given customer", appEnv.isTestPass());
    }

    @Test(priority = 7, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstCustomerNumberAndStockNumber()));
        Utils.StaticWait(10000);
    //    loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
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
    @Test(priority = 8, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_First_Name_And_Last_Name(){
        appEnv.setTotalWOAgainstFirstNameAndLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstNameAndLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameAndLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameAndLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open WO in the System Against First Name And Last Name", appEnv.isTestPass());
    }

    @Test( priority = 9,retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_First_Name_Last_Name_And_Customer_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstNameLastNameAndCustomerNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open WO in the System Against First Name , Last Name and Customer Number", appEnv.isTestPass());
    }

    @Test(priority = 10, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_First_Name_Last_Name_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstNameLastNameAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open WO in the System Against First Name , Last Name and Stock Number", appEnv.isTestPass());
    }
    @Test(priority = 11, retryAnalyzer = ReTry.class)
    public void Search_Open_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenWOAgainstFirstNameLastNameCustomerNumberAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open WO in the System Against First Name , Last Name, Customer and Stock Number", appEnv.isTestPass());
    }




    @Test(priority = 30, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_WO_Number(){
        appEnv.setTotalWOAgainstWONumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstWONumber()));
        Utils.StaticWait(10000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(appEnv.getTotalWOAgainstWONumber() == 0 && Utils.Count_Table_Rows() == 0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(appEnv.getTotalWOAgainstWONumber() == Utils.Count_Table_Rows() && Utils.Search_Table_with_String(appEnv.getWorkOrderNumber()));
        Utils.VerifyResult("Completed Work Order Not Found", appEnv.isTestPass());
    }
    @Test(priority = 30, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_First_Name(){
        appEnv.setTotalWOAgainstFirstName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstFirstName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstName() &&
                    Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against First Name", appEnv.isTestPass());
    }
    @Test(priority = 31, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_Last_Name(){
        appEnv.setTotalWOAgainstLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against Last Name", appEnv.isTestPass());
    }
    @Test(priority = 32, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_Stock_Number(){
        appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against Stock Number", appEnv.isTestPass());

    }
    @Test(priority = 33, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstCustomerNumber()));
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Completed WO Loaded in GUI does not match with Completed WO in the system against Customer Number", appEnv.isTestPass());

    }

    @Test(priority = 34, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_First_Name_And_Last_Name(){
        appEnv.setTotalWOAgainstFirstNameAndLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstFirstNameAndLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameAndLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameAndLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against First Name And Last Name", appEnv.isTestPass());
    }
    @Test(priority = 35, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_First_Name_Last_Name_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against First Name , Last Name and Stock Number", appEnv.isTestPass());
    }

    @Test(priority = 36, retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against First Name , Last Name, Customer and Stock Number", appEnv.isTestPass());
    }

    @Test( priority = 37,retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_First_Name_Last_Name_And_Customer_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against First Name , Last Name and Customer Number", appEnv.isTestPass());
    }
    @Test(priority = 38,retryAnalyzer = ReTry.class)
    public void Search_Completed_WO_Against_Customer_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumberAndStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Completed WO in the System Against Customer and Stock Number", appEnv.isTestPass());

    }


    @Test(priority = 70, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_WO_Number(){
        appEnv.setTotalWOAgainstWONumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstWONumber()));
        Utils.StaticWait(10000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(appEnv.getTotalWOAgainstWONumber() == 0 && Utils.Count_Table_Rows() == 0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(appEnv.getTotalWOAgainstWONumber() == Utils.Count_Table_Rows() && Utils.Search_Table_with_String(appEnv.getWorkOrderNumber()));
        Utils.VerifyResult("Cancelled Work Order Not Found", appEnv.isTestPass());
    }

    @Test(priority = 71, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_First_Name(){
        appEnv.setTotalWOAgainstFirstName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstFirstName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstName() &&
                    Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against First Name", appEnv.isTestPass());
    }
    @Test(priority = 72, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_Last_Name(){
        appEnv.setTotalWOAgainstLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against Last Name", appEnv.isTestPass());
    }
    @Test(priority = 73, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_Stock_Number(){
        appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against Stock Number", appEnv.isTestPass());

    }
    @Test(priority = 74, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstCustomerNumber()));
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Completed WO Loaded in GUI does not match with Cancelled WO in the system against Customer Number", appEnv.isTestPass());

    }

    @Test(priority = 75, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_First_Name_And_Last_Name(){
        appEnv.setTotalWOAgainstFirstNameAndLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstFirstNameAndLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameAndLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameAndLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against First Name And Last Name", appEnv.isTestPass());
    }
    @Test(priority = 76, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_First_Name_Last_Name_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against First Name , Last Name and Stock Number", appEnv.isTestPass());
    }

    @Test(priority = 77, retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against First Name , Last Name, Customer and Stock Number", appEnv.isTestPass());
    }

    @Test( priority = 78,retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_First_Name_Last_Name_And_Customer_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against First Name , Last Name and Customer Number", appEnv.isTestPass());
    }
    @Test(priority = 79,retryAnalyzer = ReTry.class)
    public void Search_Cancelled_WO_Against_Customer_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetCancelledWOWithinGivenDaysAgainstCustomerAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_First_Status_Filter("Cancelled");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumberAndStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Cancelled WO in the System Against Customer and Stock Number", appEnv.isTestPass());

    }







    @Test(priority = 100, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_WO_Number(){
        appEnv.setTotalWOAgainstWONumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstWONumber()));
        Utils.StaticWait(10000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Type_WO_Number(appEnv.getWorkOrderNumber());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        if(appEnv.getTotalWOAgainstWONumber() == 0 && Utils.Count_Table_Rows() == 0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(appEnv.getTotalWOAgainstWONumber() == Utils.Count_Table_Rows() && Utils.Search_Table_with_String(appEnv.getWorkOrderNumber()));
        Utils.VerifyResult("Open And Completed Work Order Not Found", appEnv.isTestPass());
    }
    @Test(priority = 101, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_First_Name(){
        appEnv.setTotalWOAgainstFirstName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstFirstName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstName() &&
                    Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against First Name", appEnv.isTestPass());
    }
    @Test(priority = 102, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_Last_Name(){
        appEnv.setTotalWOAgainstLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against Last Name", appEnv.isTestPass());
    }
    @Test(priority = 103, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_Stock_Number(){
        appEnv.setTotalWOAgainstStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against Stock Number", appEnv.isTestPass());

    }
    @Test(priority = 104, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_Customer_Number(){
        appEnv.setTotalWOAgainstCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstCustomerNumber()));
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) );
        Utils.VerifyResult("Completed WO Loaded in GUI does not match with Open And Completed WO in the system against Customer Number", appEnv.isTestPass());

    }

    @Test(priority = 105, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_First_Name_And_Last_Name(){
        appEnv.setTotalWOAgainstFirstNameAndLastName(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameAndLastName()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameAndLastName()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameAndLastName() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against First Name And Last Name", appEnv.isTestPass());
    }
    @Test(priority = 106, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_First_Name_Last_Name_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against First Name , Last Name and Stock Number", appEnv.isTestPass());
    }

    @Test(priority = 106, retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against First Name , Last Name, Customer and Stock Number", appEnv.isTestPass());
    }

    @Test( priority = 107,retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_First_Name_Last_Name_And_Customer_Number(){
        appEnv.setTotalWOAgainstFirstNameLastNameAndCustomerNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_First_Name(appEnv.getFirstName());
        pgRecentWO.Type_Last_Name(appEnv.getLastName());
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        if(appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber()== 0 && Utils.Count_Table_Rows()==0)
            appEnv.setTestPass(true);
        else
            appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstFirstNameLastNameAndCustomerNumber() &&
                    Utils.Find_Customer(appEnv.getLastName()) && Utils.Find_Customer(appEnv.getFirstName()) &&
                    Utils.Find_Customer(appEnv.getCustomerNumber()));
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against First Name , Last Name and Customer Number", appEnv.isTestPass());
    }
    @Test(priority = 108,retryAnalyzer = ReTry.class)
    public void Search_Open_And_Completed_WO_Against_Customer_And_Stock_Number(){
        appEnv.setTotalWOAgainstCustomerNumberAndStockNumber(appEnv.getRestManager().SetDataFromAPI(inputDataStream.SetOpenAndCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber()));
        Utils.StaticWait(5000);
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Click_More_Filters_Button();
        pgRecentWO.Select_Second_Status_Filter("Completed");
        pgRecentWO.Type_Customer_Number(appEnv.getCustomerNumber());
        pgRecentWO.Type_Stock_Number(appEnv.getStockNumber());
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        appEnv.getReportManager().LogStepInfo("Work Orders Found : " + Utils.Count_Table_Rows());
        appEnv.setTestPass(Utils.Count_Table_Rows()==appEnv.getTotalWOAgainstCustomerNumberAndStockNumber() &&
                Utils.Search_Table_with_String(appEnv.getStockNumber()) && Utils.Find_Customer(appEnv.getCustomerNumber()) );
        Utils.VerifyResult("Work Order Loaded in the GUI Does Not Match with Open And Completed WO in the System Against Customer and Stock Number", appEnv.isTestPass());

    }

    @Test(priority = 11)
    public void Debug_Test_Cases_To_See_Table_Data_Loaded(){
        Utils.StaticWait(5000);
    //    loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(10000);
        Utils.Display_Element_Coordinates("595662");
        Utils.Dislay_Row_Elements();
        Utils.StaticWait(10000);
    }

}
