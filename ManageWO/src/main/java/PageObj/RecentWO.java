package PageObj;


import Services.AppEnv;
import Services.General;
import org.openqa.selenium.WebElement;

/**
 * This class will manage page objects of Recent Work Orders Page
 */
public class RecentWO {


    private static AppEnv appEnv;
    private General Utils;

    public RecentWO(AppEnv appEnvo){
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);

    }

    public void Click_Search_Button(){
        WebElement btSearch = appEnv.getDriver().findElementByLinkText("Search");
        boolean GetStatus = Utils.ClickObj(btSearch);
    }
    public void Click_Clear_Button(){
        WebElement btClear = appEnv.getDriver().findElementByLinkText("Clear");
        boolean GetStatus = Utils.ClickObj(btClear);
    }
    public void Type_WO_Number(String WONumber)
    {
        appEnv.getDriver().findElementByName("workOrderNumber").clear();
        appEnv.getDriver().findElementByName("workOrerNumber").sendKeys(WONumber);
    }
    public void Type_First_Name(String FirstName)
    {
        appEnv.getDriver().findElementByXPath("//*[@id=\"root\"]/div/div[2]/div/form/div[1]/div[2]/div/input").clear();
        appEnv.getDriver().findElementByXPath("//*[@id=\"root\"]/div/div[2]/div/form/div[1]/div[2]/div/input").sendKeys(FirstName);
    }
    public void Type_Last_Name(String LastName)
    {
        appEnv.getDriver().findElementByName("lastName").clear();
        appEnv.getDriver().findElementByName("lastName").sendKeys(LastName);
    }
    public void Type_Phone_Number(String PhoneNumber)
    {
        appEnv.getDriver().findElementByName("phoneNumber").clear();
        appEnv.getDriver().findElementByName("phoneNumber").sendKeys(PhoneNumber);
    }
    public void Type_Customer_Number(String CustomerNumber)
    {
        appEnv.getDriver().findElementByName("customerNumber").clear();
        appEnv.getDriver().findElementByName("customerNumber").sendKeys(CustomerNumber);
    }

    public void Type_Stock_Number(String StockNumber)
    {
        appEnv.getDriver().findElementByName("stockNumber").clear();
        appEnv.getDriver().findElementByName("stockNumber").sendKeys(StockNumber);
    }

    public void Type_Completed_With_in_Days(String completedWithinDays)
    {
        appEnv.getDriver().findElementByName("completedWithinDays").clear();
        appEnv.getDriver().findElementByName("completedWithinDays").sendKeys(completedWithinDays);
    }



}
