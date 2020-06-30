package PageObj;


import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;

/**
 * This class will manage page objects of Recent Work Orders Page
 */
public class RecentWO {


    private static AppEnv appEnv;
    private General Utils;
    private Fetch_Elements fetch_elements;

    public RecentWO(AppEnv appEnvo){
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnvo);

    }

    public void Click_Search_Button(){
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@data-test-id=\"searchButton\"]"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@data-test-id=\"searchButton\"]"));
            appEnv.getReportManager().LogStepInfo("Click Search button.");
        }
    }
    public void Click_Clear_Button(){
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@data-test-id=\"clearButton\"]"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@data-test-id=\"clearButton\"]"));
            appEnv.getReportManager().LogStepInfo("Click Clear button.");
        }
    }
    public void Click_More_Filters_Button(){
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@data-test-id=\"filtersButton-Collapsed\"]"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@data-test-id=\"filtersButton-Collapsed\"]"));
            appEnv.getReportManager().LogStepInfo("Click More Filters button.");
        }
    }

    public void Click_Less_Filters_Button(){
        appEnv.getReportManager().LogStepInfo("Click Less Filters button.");
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@data-test-id=\"filtersButton-Expanded\"]")))
        Utils.ClickObj(fetch_elements.GetObj("xpath","//*[@data-test-id=\"filtersButton-Expanded\"]"));
    }

    public void Sign_Out()
    {
        appEnv.getReportManager().LogStepInfo("Click Sign out button");
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]")))
        {

            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]"));
            Utils.waitTillXpathPresent("//*[@id=\"root\"]//button[starts-with(@class, 'UserMenu')]", 30);
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[starts-with(@class, 'UserMenu')]"));
            Utils.waitTillXpathPresent("//*[@id=\"root\"]//div[starts-with(@class, 'Login')]", 30);
            appEnv.getReportManager().LogStepInfo("Successfully Signed Out");
        }
    }

    public void Type_WO_Number(String WONumber)
    {
        appEnv.getReportManager().LogStepInfo("Type Work Order Number " + WONumber);
        Utils.SendText(fetch_elements.GetObj("name","workOrderNumber"),WONumber);
    }
    public void Type_First_Name(String FirstName)
    {
        appEnv.getReportManager().LogStepInfo("Type First Name  " + FirstName);
        Utils.SendText(fetch_elements.GetObj("name","firstName"),FirstName);
    }
    public void Type_Last_Name(String LastName)
    {
        appEnv.getReportManager().LogStepInfo("Type Last Name " + LastName);
        Utils.SendText(fetch_elements.GetObj("name","lastName"),LastName);
    }
    public void Type_Phone_Number(String PhoneNumber)
    {
        appEnv.getReportManager().LogStepInfo("Type Phone Number " + PhoneNumber);
        Utils.SendText(fetch_elements.GetObj("name","phoneNumber"),PhoneNumber);
    }
    public void Type_Customer_Number(String CustomerNumber)
    {
        appEnv.getReportManager().LogStepInfo("Type Customer Number " + CustomerNumber);
        Utils.SendText(fetch_elements.GetObj("name","customerNumber"),CustomerNumber);
    }

    public void Type_Stock_Number(String StockNumber)
    {
        appEnv.getReportManager().LogStepInfo("Type Stock Number " + StockNumber);
        Utils.SendText(fetch_elements.GetObj("name","stockNumber"),StockNumber);
    }

    public void Type_Completed_With_in_Days(String completedWithinDays)
    {
        appEnv.getReportManager().LogStepInfo("Type Completed within Days " + completedWithinDays);
        Utils.SendText(fetch_elements.GetObj("name","completedWithinDays"),completedWithinDays);
    }
    public void Click_Include_Open_WO(){
        appEnv.getReportManager().LogStepInfo("Click Include Open Work Orders Button");
        Utils.ClickObj(fetch_elements.GetObj("xpath","//*[@id=\"root\"]//div[@class= 'react-switch-bg']"));
    }

}





