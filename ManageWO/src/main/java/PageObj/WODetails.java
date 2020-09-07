package PageObj;

import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;

public class WODetails {

    private static WODetails woDetails = new WODetails();
    private static AppEnv appEnv = new AppEnv();
    private static Fetch_Elements fetch_elements ;
    private static General Utils;
    private static RecentWO pgRecentWO;
    private static LoginPage loginPage;
    private WODetails() {
    }

    /* Static 'instance' method */
    public static WODetails getInstance(AppEnv appEnv) {
        WODetails.appEnv = appEnv;
        Utils= General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnv);
        pgRecentWO = new RecentWO(appEnv);
        loginPage = new LoginPage(appEnv);
    return woDetails;
    }

    public void Search_And_Click_WONumber(String  WONumber){
        Utils.StaticWait(1000);
        loginPage.Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@data-test-id='workOrderListPage']", 30);
        pgRecentWO.Click_Clear_Button();
        Utils.StaticWait(5000);
        pgRecentWO.Type_WO_Number(WONumber);
        pgRecentWO.Click_Search_Button();
        Utils.StaticWait(5000);
        Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[starts-with(@data-test-id,'gridBodyRow0')]/*[@data-test-id='gridBodyCell'][1]"));
        Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[starts-with(@data-test-id,'gridBodyRow0')]/*[@data-test-id='gridBodyCell'][1]"));
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[1]/div[1]/div[2]", 30);
        appEnv.getReportManager().LogStepInfo("Click to Open Work Order Number  : " + WONumber);

    }
    public String GetWODate(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[1]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderGeneral']/div[2]/div[1]/div[1]/div[2]"));
    }

    public String GetWOAuthor(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[2]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderGeneral']/div[2]/div[2]/div[1]/div[2]"));
    }
    public String GetWOStatus(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[3]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderGeneral']/div[2]/div[3]/div[1]/div[2]"));
    }

    public String GetWOLocation(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[4]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderGeneral']/div[2]/div[4]/div[1]/div[2]"));
    }

    public String GetWOSalesID(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderGeneral']/div[2]/div[5]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderGeneral']/div[2]/div[5]/div[1]/div[2]"));
    }

    public String GetCustomerNo(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderCustomer']/div[2]/div[1]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderCustomer']/div[2]/div[1]/div[1]/div[2]"));
    }


    public String GetCustomerEmail(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderCustomer']/div[2]/div[2]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderCustomer']/div[2]/div[2]/div[1]/div[2]"));
    }

    public String GetCustomerHomePhone(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderCustomer']/div[2]/div[3]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderCustomer']/div[2]/div[3]/div[1]/div[2]"));
    }

    public String GetCustomerMobilePhone(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderCustomer']/div[2]/div[4]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderCustomer']/div[2]/div[4]/div[1]/div[2]"));
    }

    public String GetCustomerAddress(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderCustomer']/div[2]/div[5]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderCustomer']/div[2]/div[5]/div[1]/div[2]"));
    }


    public String GetWOAppointmentDate(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderSchedule']/div[2]/div[1]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderSchedule']/div[2]/div[1]/div[1]/div[2]"));
    }


    public String GetWOExpectedDate(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderSchedule']/div[2]/div[2]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderSchedule']/div[2]/div[2]/div[1]/div[2]"));
    }

    public String GetWOInServiceDate(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderSchedule']/div[2]/div[3]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderSchedule']/div[2]/div[3]/div[1]/div[2]"));
    }

    public String GetWOSchedulePriority(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderSchedule']/div[2]/div[4]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderSchedule']/div[2]/div[4]/div[1]/div[2]"));
    }
    public String GetWOWarrantyDate(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderUnit']/div[2]/div[1]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderUnit']/div[2]/div[1]/div[1]/div[2]"));
    }


    public String GetWOChasisNumber(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderUnit']/div[2]/div[2]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderUnit']/div[2]/div[2]/div[1]/div[2]"));
    }

    public String GetWOStockNumber(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderUnit']/div[2]/div[3]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderUnit']/div[2]/div[3]/div[1]/div[2]"));
    }

    public String GetWOSerialNumber(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderUnit']/div[2]/div[4]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderUnit']/div[2]/div[4]/div[1]/div[2]"));
    }

    public String GetStockMeterIn(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderMisc']/div[2]/div[1]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderMisc']/div[2]/div[1]/div[1]/div[2]"));
    }


    public String GetStockMeterOut(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderMisc']/div[2]/div[2]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderMisc']/div[2]/div[2]/div[1]/div[2]"));
    }

    public String GetWOTagNumber(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderMisc']/div[2]/div[3]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderMisc']/div[2]/div[3]/div[1]/div[2]"));
    }

    public String GetWoCategory(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderMisc']/div[2]/div[4]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderMisc']/div[2]/div[4]/div[1]/div[2]"));
    }
    public String GetWOPartsDiscountPercentage(){
        Utils.waitTillXpathPresent("//div[@data-test-id='woHeaderMisc']/div[2]/div[5]/div[1]/div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//div[@data-test-id='woHeaderMisc']/div[2]/div[5]/div[1]/div[2]"));
    }

}
