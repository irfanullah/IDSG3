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
public class DetailedWO {

    private static AppEnv appEnv = new AppEnv();
    private static General Utils;
    private WODetails woDetails;
    private LoginPage loginPage;
    private RestManager restManager;
    private InputDataStream inputDataStream;
    private Fetch_Elements fetch_elements;
    private RecentWO pgRecentWO;


    public DetailedWO() {
        appEnv = SuiteListener.appEnv;
        Utils = General.getInstance(appEnv);
        woDetails = WODetails.getInstance(appEnv);
        pgRecentWO = new RecentWO(appEnv);
        loginPage = new LoginPage(appEnv);
        inputDataStream = InputDataStream.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnv);

    }


    @Test(priority = 500, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Date(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWODate();
        String APIValue = appEnv.getRestManager().GetDateFromWOAPI("WorkOrder","WorkOrderDate");
        System.out.println("Work Order Date is : " + GUIValue);
        appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        appEnv.getReportManager().LogStepInfo("WO Date is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Date ", appEnv.isTestPass());
    }

    @Test(priority = 501, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Author(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Author : " + woDetails.GetWOAuthor());
        appEnv.setTestPass(appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","Author").equalsIgnoreCase(woDetails.GetWOAuthor()));
        appEnv.getReportManager().LogStepInfo("WO Author is : " + woDetails.GetWOAuthor());
        Utils.VerifyResult("Can not Load Work Order Author ", appEnv.isTestPass());
    }

    @Test(priority = 502, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Status(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Status  : " + woDetails.GetWOStatus());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","StatusCode") + " - "+ appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","StatusDesc");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetWOStatus()));
        appEnv.getReportManager().LogStepInfo("WO Status is : " + woDetails.GetWOStatus());
        Utils.VerifyResult("Can not Load Work Order Status ", appEnv.isTestPass());
    }

    @Test(priority = 503, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Location(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Location  : " + woDetails.GetWOLocation());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","WorkOrderLocation") + " - "+ appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","WorkOrderLocationDesc");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetWOLocation()));
        appEnv.getReportManager().LogStepInfo("WO Location is : " + woDetails.GetWOLocation());
        Utils.VerifyResult("Can not Load Work Order Location ", appEnv.isTestPass());
    }

    @Test(priority = 504, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_SalesID(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Sales ID   : " + woDetails.GetWOSalesID());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","SalesmanCode") + " - "+ appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","SalesmanDesc");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetWOSalesID()));
        appEnv.getReportManager().LogStepInfo("WO Sales ID is : " + woDetails.GetWOSalesID());
        Utils.VerifyResult("Can not Load Work Order Sales ID ", appEnv.isTestPass());
    }

    @Test(priority = 505, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Name(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Name and Number    : " + woDetails.GetCustomerNo());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","CustomerNo") + " - " + appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","Name");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetCustomerNo()));
        appEnv.getReportManager().LogStepInfo("WO Customer Name and Number is : " + woDetails.GetCustomerNo());
        Utils.VerifyResult("Can not Load Work Order Name and Number ", appEnv.isTestPass());
    }

    @Test(priority = 506, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Email(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Email   : " + woDetails.GetCustomerEmail());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","Email");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetCustomerEmail()));
        appEnv.getReportManager().LogStepInfo("WO Customer Email is : " + woDetails.GetCustomerEmail());
        Utils.VerifyResult("Can not Load Work Order Email ", appEnv.isTestPass());
    }

    @Test(priority = 507, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Home_Phone(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Home Phone   : " + woDetails.GetCustomerHomePhone());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","HomePhone");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetCustomerHomePhone()));
        appEnv.getReportManager().LogStepInfo("WO Customer Home Phone is : " + woDetails.GetCustomerHomePhone());
        Utils.VerifyResult("Can not Load Work Order Home Phone ", appEnv.isTestPass());
    }

    @Test(priority = 508, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Mobile_Phone(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Mobile Phone   : " + woDetails.GetCustomerMobilePhone());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","MobilePhone");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetCustomerMobilePhone()));
        appEnv.getReportManager().LogStepInfo("WO Customer Mobile Phone is : " + woDetails.GetCustomerMobilePhone());
        Utils.VerifyResult("Can not Load Work Order Mobile Phone ", appEnv.isTestPass());
    }

    @Test(priority = 509, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Address(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Address : " + woDetails.GetCustomerAddress());
        String Expected_Result = appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","AddressLine1") +", "+ appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","City") +", "+
                appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","State") +" "+ appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","ZipCode") +", "+
                appEnv.getRestManager().GetStringInfoFromWOAPI("Customer","Country");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetCustomerAddress()));
        appEnv.getReportManager().LogStepInfo("WO Customer Address is : " + woDetails.GetCustomerAddress());
        Utils.VerifyResult("Can not Load Work Order Address ", appEnv.isTestPass());
    }
    @Test(priority = 510, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Appointment_Date(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Appointment Date is : " + woDetails.GetWOAppointmentDate());
        appEnv.setTestPass(appEnv.getRestManager().GetDateFromWOAPI("WorkOrder","AppointmentDateTime").equalsIgnoreCase(woDetails.GetWOAppointmentDate()));
        appEnv.getReportManager().LogStepInfo("WO Appointment Date is : " + woDetails.GetWOAppointmentDate());
        Utils.VerifyResult("Can not Load Work Order Appointment Date ", appEnv.isTestPass());
    }

    @Test(priority = 511, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Expected_Promised_Date(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Expected/Promised Date is : " + woDetails.GetWOExpectedDate());
        appEnv.setTestPass(appEnv.getRestManager().GetDateFromWOAPI("WorkOrder","PromiseDateTime").equalsIgnoreCase(woDetails.GetWOExpectedDate()));
        appEnv.getReportManager().LogStepInfo("WO Expected/Promised Date is : " + woDetails.GetWOExpectedDate());
        Utils.VerifyResult("Can not Load Work Order Expected/Promised Date ", appEnv.isTestPass());
    }
    @Test(priority = 512, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_In_Service_Date(){

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIDate = woDetails.GetWOInServiceDate();
        String APIDate = appEnv.getRestManager().GetDateFromWOAPI("WorkOrder","InServiceDate");
        System.out.println("Work Order InService Date is : " + GUIDate);
        System.out.println("InService Date from API : " + APIDate);
        appEnv.setTestPass(GUIDate.equalsIgnoreCase(APIDate));
        appEnv.getReportManager().LogStepInfo("WO InService Date is : " + GUIDate);
        Utils.VerifyResult("Can not Load Work Order InService Date ", appEnv.isTestPass());
    }
    @Test(priority = 513, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Schedule_Priority(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        System.out.println("Work Order Customer Schedule Priority   : " + woDetails.GetWOSchedulePriority());
        String Expected_Result = appEnv.getRestManager().GetIntInfoFromWOAPI("WorkOrder","SchedulePriorityCode") + " - " + appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","SchedulePriorityDesc");
        appEnv.setTestPass(Expected_Result.equalsIgnoreCase(woDetails.GetWOSchedulePriority()));
        appEnv.getReportManager().LogStepInfo("WO Customer Schedule Priority is : " + woDetails.GetWOSchedulePriority());
        Utils.VerifyResult("Can not Load Work Order Schedule Priority ", appEnv.isTestPass());
    }
    @Test(priority = 514, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Warranty_Date(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOWarrantyDate();
        String APIValue = appEnv.getRestManager().GetDateFromWOAPI("Inventory","WarrantyDate");
        System.out.println("WO Stock Number Warranty Date is   : " + GUIValue);
        appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        appEnv.getReportManager().LogStepInfo("WO Stock Number Warranty Date is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Number Warranty Date ", appEnv.isTestPass());
    }
    @Test(priority = 515, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Chasis_Number(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOChasisNumber();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = appEnv.getRestManager().GetStringInfoFromWOAPI("Inventory", "ChasisNo");
            System.out.println("WO Stock Chasis Number is   : " + GUIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("WO Stock Chasis Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Chasis Number ", appEnv.isTestPass());
    }

    @Test(priority = 516, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Stock_Number(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOStockNumber();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = appEnv.getRestManager().GetStringInfoFromWOAPI("Inventory", "StockNo") +" - "+ appEnv.getRestManager().GetStringInfoFromWOAPI("Inventory", "Description");
            System.out.println("Work Order Stock  Number is   : " + GUIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("Work Order Stock  Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Stock Number ", appEnv.isTestPass());
    }

    @Test(priority = 517, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Serial_Number(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOSerialNumber();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = appEnv.getRestManager().GetStringInfoFromWOAPI("Inventory", "SerialNo");
            System.out.println("WO Stock Serial Number is   : " + GUIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("WO Stock Serial Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Serial Number ", appEnv.isTestPass());
    }

    @Test(priority = 518, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Meter_In_Reading(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetStockMeterIn();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String  APIValue = String.valueOf(appEnv.getRestManager().GetIntInfoFromWOAPI("WorkOrder", "MileageIn"));
            String ReadingUnit = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","MileageUnitCode");
            switch (ReadingUnit){
                case "H":
                    APIValue = APIValue + " " + "hrs";
                    break;
                case "K":
                    APIValue = APIValue + " " + "km";
                    break;
                case "M":
                    APIValue = APIValue + " " + "miles";
                    break;

            }
            System.out.println("WO Stock Meter In Reading is  : " + GUIValue);
            appEnv.setTestPass(GUIValue.equalsIgnoreCase(APIValue));
        }
        appEnv.getReportManager().LogStepInfo("WO Stock Meter In Reading is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Meter In Reading ", appEnv.isTestPass());
    }


    @Test(priority = 519, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Meter_Out_Reading(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetStockMeterOut();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String  APIValue = String.valueOf(appEnv.getRestManager().GetIntInfoFromWOAPI("WorkOrder", "MileageOut"));
            String ReadingUnit = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder","MileageUnitCode");
            switch (ReadingUnit){
                case "H":
                    APIValue = APIValue + " " + "hrs";
                    break;
                case "K":
                    APIValue = APIValue + " " + "km";
                    break;
                case "M":
                    APIValue = APIValue + " " + "miles";
                    break;

            }
            System.out.println("API - WO Stock Meter Out Reading is  : " + APIValue);
            appEnv.setTestPass(GUIValue.equalsIgnoreCase(APIValue));
        }
        appEnv.getReportManager().LogStepInfo("WO Stock Meter Out Reading is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Meter Out Reading ", appEnv.isTestPass());
    }

    @Test(priority = 520, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Tag_Number(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOTagNumber();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder", "TagNo");
            System.out.println("Work Order Tag Number is   : " + GUIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("Work Order Tag Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Tag Number ", appEnv.isTestPass());
    }

    @Test(priority = 521, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Category(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWoCategory();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder", "CategoryCode") + " - " + appEnv.getRestManager().GetStringInfoFromWOAPI("WorkOrder", "CategoryDesc");
            System.out.println("API - Work Order Category is   : " + APIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("Work Order Category is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Category ", appEnv.isTestPass());
    }

    @Test(priority = 521, retryAnalyzer = ReTry.class)
    public void Load_Parts_Discount_Percentage(){

        System.out.println(appEnv.getRestManager().GetWOCustomerInfoFromAPI("Customer"));
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOPartsDiscountPercentage();
        if(GUIValue.isEmpty())
            appEnv.setTestPass(true);
        else {
            String APIValue = String.valueOf(appEnv.getRestManager().GetIntInfoFromWOAPI("WorkOrder", "PartsDiscount"));
            System.out.println("API - Work Order Parts Discount Percentage is   : " + APIValue);
            appEnv.setTestPass(APIValue.equalsIgnoreCase(GUIValue));
        }
        appEnv.getReportManager().LogStepInfo("Work Order Parts Discount Percentage is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Parts Discount Percentage ", appEnv.isTestPass());
    }
}
