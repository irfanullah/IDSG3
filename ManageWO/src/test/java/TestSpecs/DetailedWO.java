package TestSpecs;

import Drivers.Fetch_Elements;
import PageObj.LoginPage;
import PageObj.RecentWO;
import PageObj.WODetails;
import Services.*;
import TestManager.InputDataStream;
import TestManager.SuiteListener;
import UniverseDBValidation.U2WorkOrder;
import UniverseDBValidation.response.WorkOrderResponse;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(SuiteListener.class)
public class DetailedWO {

    private static AppEnv appEnv = new AppEnv();
    private static General Utils;
    private WODetails woDetails;
    private LoginPage loginPage;
    private RestManager restManager;
    private InputDataStream inputDataStream;
    private Fetch_Elements fetch_elements;
    private RecentWO pgRecentWO;
    private WorkOrderResponse response;
    private G3WOResponse g3WOResponse;


    @BeforeClass
    public void GetJobsDataFromAPI() {
        U2WorkOrder u2WorkOrder = new U2WorkOrder(appEnv.getU2RestApiBaseUrl(), appEnv.getU2RestApiToken());
        g3WOResponse = appEnv.getRestManager().GetWOInfoFromAPI();
        response = u2WorkOrder.getWorkOrderResponse(appEnv.getAccoutID(), appEnv.getLocation(), appEnv.getWorkOrderNumber());
    }


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
    public void Load_Work_Order_Date() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWODate();
        String U2APIValue = Utils.FormateDateTime(Utils.FormateString(response.workOrder.workOrderDate.toString()));
        String G3APIValue = Utils.FormateDate(Utils.FormateString(g3WOResponse.WorkOrder.WorkOrderDate));
        System.out.println("WO Date is   : " + GUIValue);
        System.out.println("WO Date from G3 API is : " + G3APIValue );
        System.out.println("WO Date from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Date is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Date ", appEnv.isTestPass());
    }

    @Test(priority = 501, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Author() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOAuthor();
        String U2APIValue = Utils.FormateString(response.workOrder.author);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.Author);
        System.out.println("WO Author is   : " + GUIValue);
        System.out.println("WO Author from G3 API is : " + G3APIValue );
        System.out.println("WO Author from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Author is : " + woDetails.GetWOAuthor());
        Utils.VerifyResult("Can not Load Work Order Author ", appEnv.isTestPass());
    }

    @Test(priority = 502, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Status() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOStatus();
        String U2APIValue = Utils.FormateString(response.workOrder.statusCode) + " - " + Utils.FormateString(response.workOrder.statusDesc);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.StatusCode) + " - " +Utils.FormateString(g3WOResponse.WorkOrder.StatusDesc);
        System.out.println("WO Status is   : " + GUIValue);
        System.out.println("WO Status from G3 API is : " + G3APIValue );
        System.out.println("WO Status from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Status is : " + woDetails.GetWOStatus());
        Utils.VerifyResult("Can not Load Work Order Status ", appEnv.isTestPass());
    }

    @Test(priority = 503, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Location() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOLocation();
        String U2APIValue = Utils.FormateString(response.workOrder.workOrderLocation) + " - " + Utils.FormateString(response.workOrder.workOrderLocationDesc);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.WorkOrderLocation) + " - " +Utils.FormateString(g3WOResponse.WorkOrder.WorkOrderLocationDesc);
        System.out.println("WO Location is   : " + GUIValue);
        System.out.println("WO Location from G3 API is : " + G3APIValue );
        System.out.println("WO Location from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Location is : " + woDetails.GetWOLocation());
        Utils.VerifyResult("Can not Load Work Order Location ", appEnv.isTestPass());
    }

    @Test(priority = 504, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_SalesID() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOSalesID();
        String U2APIValue = Utils.FormateString(response.workOrder.salesmanCode) + " - " + Utils.FormateString(response.workOrder.salesmanDesc);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.SalesmanCode) + " - " +Utils.FormateString(g3WOResponse.WorkOrder.SalesmanDesc);
        System.out.println("WO Sales ID is   : " + GUIValue);
        System.out.println("WO Sales ID from G3 API is : " + G3APIValue );
        System.out.println("WO Sales ID from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Sales ID is : " + woDetails.GetWOSalesID());
        Utils.VerifyResult("Can not Load Work Order Sales ID ", appEnv.isTestPass());
    }

    @Test(priority = 505, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Name() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetCustomerNo();
        String U2APIValue = Utils.FormateString(response.customer.customerNo) + " - " + Utils.FormateString(response.customer.name);
        String G3APIValue = Utils.FormateString(g3WOResponse.Customer.CustomerNo) + " - " +Utils.FormateString(g3WOResponse.Customer.Name);
        System.out.println("WO Customer is   : " + GUIValue);
        System.out.println("WO Customer from G3 API is : " + G3APIValue );
        System.out.println("WO Customer from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Name and Number is : " + woDetails.GetCustomerNo());
        Utils.VerifyResult("Can not Load Work Order Name and Number ", appEnv.isTestPass());
    }

    @Test(priority = 506, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Email() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetCustomerEmail();
        String U2APIValue = Utils.FormateString(response.customer.email);
        String G3APIValue = Utils.FormateString(g3WOResponse.Customer.Email);
        System.out.println("WO Customer Email is   : " + GUIValue);
        System.out.println("WO Customer Email from G3 API is : " + G3APIValue );
        System.out.println("WO Customer Email from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Email is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Email ", appEnv.isTestPass());
    }

    @Test(priority = 507, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Home_Phone() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetCustomerHomePhone();
        String U2APIValue = Utils.FormateString(response.customer.homePhone);
        String G3APIValue = Utils.FormateString(g3WOResponse.Customer.HomePhone);
        System.out.println("WO Customer Mobile Number is   : " + GUIValue);
        System.out.println("WO Customer Mobile from G3 API is : " + G3APIValue );
        System.out.println("WO Customer Mobile from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Home Phone is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Home Phone ", appEnv.isTestPass());
    }

    @Test(priority = 508, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Mobile_Phone() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetCustomerMobilePhone();
        String U2APIValue = Utils.FormateString(response.customer.mobilePhone);
        String G3APIValue = Utils.FormateString(g3WOResponse.Customer.MobilePhone);
        System.out.println("WO Customer Mobile Number is   : " + GUIValue);
        System.out.println("WO Customer Mobile from G3 API is : " + G3APIValue );
        System.out.println("WO Customer Mobile from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Mobile Phone is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Mobile Phone ", appEnv.isTestPass());
    }

    @Test(priority = 509, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Customer_Address() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetCustomerAddress();
        String U2APIValue = Utils.FormateString(response.customer.addressLine1) +  ", "+ Utils.FormateString(response.customer.addressLine2)
                            + Utils.FormateString(response.customer.city) + " "+  Utils.FormateString(response.customer.zipCode)
                            + ", "+ Utils.FormateString(response.customer.country)   ;
        String G3APIValue = Utils.FormateString(g3WOResponse.Customer.AddressLine1) + ", "+ Utils.FormateString(g3WOResponse.Customer.AddressLine2) +
                ", "+ Utils.FormateString(g3WOResponse.Customer.City) + ", "+  Utils.FormateString(g3WOResponse.Customer.State) +" "+ Utils.FormateString(g3WOResponse.Customer.ZipCode)
                + ", "+ Utils.FormateString(g3WOResponse.Customer.Country)   ;
        System.out.println("WO Customer Address is   : " + GUIValue);
        System.out.println("WO Customer Address from G3 API is : " + G3APIValue );
        System.out.println("WO Customer Address from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(U2APIValue) && GUIValue.equalsIgnoreCase(G3APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Address is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Address ", appEnv.isTestPass());
    }

    @Test(priority = 510, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Appointment_Date() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOAppointmentDate();
        System.out.println(response.workOrder.appointmentDateTime);
        String U2APIValue = Utils.FormateDateTime(Utils.FormateString(response.workOrder.appointmentDateTime.toString()));
        String G3APIValue = Utils.FormateDate(Utils.FormateString(g3WOResponse.WorkOrder.AppointmentDate)) +" "+ Utils.FormateTime(Utils.FormateString(g3WOResponse.WorkOrder.AppointmentTime));
        System.out.println("WO Appointment Date is   : " + GUIValue);
        System.out.println("WO Appointment Date from G3 API is : " + G3APIValue );
        System.out.println("WO Appointment Date from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Appointment Date is : " + woDetails.GetWOAppointmentDate());
        Utils.VerifyResult("Can not Load Work Order Appointment Date ", appEnv.isTestPass());
    }

    @Test(priority = 511, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Expected_Promised_Date() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOExpectedDate();
        String U2APIValue = Utils.FormateDateTime(Utils.FormateString(response.workOrder.promiseDateTime.toString()));
        String G3APIValue = Utils.FormateDate(Utils.FormateString(g3WOResponse.WorkOrder.PromiseDate)) +" "+ Utils.FormateTime(Utils.FormateString(g3WOResponse.WorkOrder.PromiseTime));
        System.out.println("WO Expected/Primised Date is   : " + GUIValue);
        System.out.println("WO Expected/Primised Date from G3 API is : " + G3APIValue );
        System.out.println("WO Expected/Primised Date from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Expected/Promised Date is : " + woDetails.GetWOExpectedDate());
        Utils.VerifyResult("Can not Load Work Order Expected/Promised Date ", appEnv.isTestPass());
    }

    @Test(priority = 512, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_In_Service_Date() {

        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOInServiceDate();
        String U2APIValue = Utils.FormateDateTime(Utils.FormateString(response.workOrder.inServiceDate.toString()));
        String G3APIValue = Utils.FormateDate(Utils.FormateString(g3WOResponse.WorkOrder.InServiceDate));
        System.out.println("WO InService Date is   : " + GUIValue);
        System.out.println("WO InService Date from G3 API is : " + G3APIValue );
        System.out.println("WO InService Date from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO InService Date is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order InService Date ", appEnv.isTestPass());
    }

    @Test(priority = 513, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Schedule_Priority() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOSchedulePriority();
        String U2APIValue = Utils.FormateString(response.workOrder.schedulePriorityCode.toString()) + " - "+ Utils.FormateString(response.workOrder.schedulePriorityDesc);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.SchedulePriorityCode.toString()) + " - "+ Utils.FormateString(g3WOResponse.WorkOrder.SchedulePriorityDesc);
        System.out.println("WO Schedule Priority is   : " + GUIValue);
        System.out.println("WO Schedule Priority from G3 API is : " + G3APIValue );
        System.out.println("WO Schedule Priority from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Customer Schedule Priority is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Schedule Priority ", appEnv.isTestPass());
    }

    @Test(priority = 514, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Warranty_Date() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOWarrantyDate();
        String U2APIValue = Utils.FormateDateTime(Utils.FormateString(response.inventory.warrantyDate.toString()));
        String G3APIValue = Utils.FormateDate(Utils.FormateString(g3WOResponse.Inventory.WarrantyDate));
        System.out.println("WO Warranty Date is   : " + GUIValue);
        System.out.println("WO Warranty Date from G3 API is : " + G3APIValue );
        System.out.println("WO Warranty Date from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Stock Number Warranty Date is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Number Warranty Date ", appEnv.isTestPass());
    }

    @Test(priority = 515, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Chasis_Number() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOChasisNumber();
        String U2APIValue = Utils.FormateString(response.inventory.chassisNo);
        String G3APIValue = Utils.FormateString(g3WOResponse.Inventory.ChassisNo);
        System.out.println("Stock Chassis Number is   : " + GUIValue);
        System.out.println("Stock Chassis Number from G3 API is : " + G3APIValue );
        System.out.println("Stock Chassis Number from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("WO Stock Chassis Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load WO Stock Chasis Number ", appEnv.isTestPass());
    }

    @Test(priority = 516, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Stock_Number() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOStockNumber();
        String U2APIValue = Utils.FormateString(response.inventory.stockNo) + " - " + Utils.FormateString(response.inventory.description);;
        String G3APIValue = Utils.FormateString(g3WOResponse.Inventory.StockNo) + " - " + Utils.FormateString(g3WOResponse.Inventory.Description);
        System.out.println("Stock  Number is   : " + GUIValue);
        System.out.println("Stock Number from G3 API is : " + G3APIValue );
        System.out.println("Stock Number from Universe DB is   : " + U2APIValue);
        appEnv.getReportManager().LogStepInfo("WO Stock Number is : " + GUIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        appEnv.getReportManager().LogStepInfo("Work Order Stock  Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Stock Number ", appEnv.isTestPass());
    }

    @Test(priority = 517, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Serial_Number() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOSerialNumber();
        String U2APIValue = Utils.FormateString(response.inventory.serialNo);
        String G3APIValue = Utils.FormateString(g3WOResponse.Inventory.SerialNo);
        System.out.println("Stock Serial Number is   : " + GUIValue);
        System.out.println("Stock API Serial Number is : " + G3APIValue + " " + G3APIValue);
        System.out.println("Stock Serial Number from Universe DB is   : " + U2APIValue+ " " + U2APIValue);
        appEnv.getReportManager().LogStepInfo("WO Stock Serial Number is : " + GUIValue);
        appEnv.setTestPass(GUIValue.equalsIgnoreCase(G3APIValue) && GUIValue.equalsIgnoreCase(U2APIValue));
        Utils.VerifyResult("Can not Load WO Stock Serial Number ", appEnv.isTestPass());

    }

    @Test(priority = 518, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Meter_In_Reading() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String U2MileageUnitDesc = Utils.FormateString(response.workOrder.mileageUnitDesc);
        String G3APIMilageDescription = Utils.FormateString(g3WOResponse.WorkOrder.MileageUnitDesc);
        String GUIValue = woDetails.GetStockMeterIn();
        Double GUIInMileage = Double.parseDouble(Utils.GetNumfromString(GUIValue));
        String GUIMileageUnitDesc = Utils.GetAlpafromString(GUIValue);
        Double U2APIValue = Utils.FormateString(response.workOrder.mileageIn);
        Double G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.MileageIn);
        System.out.println("Stock Mileage In is   : " + GUIValue);
        System.out.println("Stock Order API Mileage In is   : " + G3APIValue + " " + G3APIMilageDescription);
        System.out.println("Stock Mileage In from Universe DB is   : " + U2APIValue+ " " + U2MileageUnitDesc);
        appEnv.getReportManager().LogStepInfo("WO Stock Meter In Reading is : " + GUIValue);
        appEnv.setTestPass(U2APIValue.equals(GUIInMileage) && G3APIValue.equals(GUIInMileage) && woDetails.MatchMilageUnit(U2MileageUnitDesc,GUIMileageUnitDesc) &&
                woDetails.MatchMilageUnit(G3APIMilageDescription,GUIMileageUnitDesc));
        Utils.VerifyResult("Can not Load WO Stock Meter In Reading ", appEnv.isTestPass());

    }
    @Test(priority = 519, retryAnalyzer = ReTry.class)
    public void Load_WO_Stock_Meter_Out_Reading() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String U2MileageUnitDesc = Utils.FormateString(response.workOrder.mileageUnitDesc);
        String G3APIMilageDesc = Utils.FormateString(g3WOResponse.WorkOrder.MileageUnitDesc);
        String GUIValue = woDetails.GetStockMeterOut();
        Double GUIOutMileage = Double.parseDouble(Utils.GetNumfromString(GUIValue));
        String GUIMileageUnitDesc = Utils.GetAlpafromString(GUIValue);
        Double U2APIValue = Utils.FormateString(response.workOrder.mileageOut);
        Double G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.MileageOut);
        System.out.println("Stock Mileage Out  is   : " + GUIValue);
        System.out.println("Stock API Mileage Out is   : " + G3APIValue + " " + G3APIMilageDesc);
        System.out.println("Stock Mileage Out from Universe DB is   : " + U2APIValue+ " " + U2MileageUnitDesc);
        appEnv.getReportManager().LogStepInfo("WO Stock Meter Out Reading is : " + GUIValue);
        appEnv.setTestPass(U2APIValue.equals(GUIOutMileage) && G3APIValue.equals(GUIOutMileage) && woDetails.MatchMilageUnit(U2MileageUnitDesc,GUIMileageUnitDesc) &&
                woDetails.MatchMilageUnit(G3APIMilageDesc,GUIMileageUnitDesc));
        Utils.VerifyResult("Can not Load WO Stock Meter Out Reading ", appEnv.isTestPass());
    }

    @Test(priority = 520, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Tag_Number() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOTagNumber();
        String U2APIValue = Utils.FormateString(response.workOrder.tagNo);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.TagNo);
        System.out.println("Work Order Tag Number is   : " + GUIValue);
        System.out.println("Work Order API Tag Number is   : " + G3APIValue);
        System.out.println("Work Order Tag Number  from Universe DB is  : " + U2APIValue);
        appEnv.setTestPass(U2APIValue.equalsIgnoreCase(GUIValue) && U2APIValue.equalsIgnoreCase(G3APIValue));
        appEnv.getReportManager().LogStepInfo("Work Order Tag Number is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Tag Number ", appEnv.isTestPass());
    }

    @Test(priority = 521, retryAnalyzer = ReTry.class)
    public void Load_Work_Order_Category() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWoCategory();
        String U2APIValue = Utils.FormateString(response.workOrder.categoryCode) + " - " + Utils.FormateString(response.workOrder.categoryDesc);
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.CategoryCode) + " - " + Utils.FormateString(g3WOResponse.WorkOrder.CategoryDesc);
        System.out.println("Work Order Category is   : " + GUIValue);
        System.out.println("Work Order API Category is   : " + G3APIValue);
        System.out.println("Work Order Category from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(U2APIValue.equalsIgnoreCase(GUIValue) && G3APIValue.equalsIgnoreCase(GUIValue));
        appEnv.getReportManager().LogStepInfo("Work Order Category is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Category ", appEnv.isTestPass());
    }

    @Test(priority = 521, retryAnalyzer = ReTry.class)
    public void Load_Parts_Discount_Percentage() {
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());
        String GUIValue = woDetails.GetWOPartsDiscountPercentage();
        String U2APIValue = Utils.FormateString(response.workOrder.partsDiscount.toString());
        String G3APIValue = Utils.FormateString(g3WOResponse.WorkOrder.PartsDiscount.toString());
        System.out.println("Work Order Parts Percentage is   : " + GUIValue);
        System.out.println("Work Order API Parts Percentage is   : " + G3APIValue);
        System.out.println("Work Order Parts Percentage from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(U2APIValue.equalsIgnoreCase(GUIValue) && G3APIValue.equalsIgnoreCase(GUIValue));
        appEnv.getReportManager().LogStepInfo("Work Order Category is : " + GUIValue);
        Utils.VerifyResult("Can not Load Work Order Parts Percentage ", appEnv.isTestPass());
    }

    @Test(priority = 550, retryAnalyzer = ReTry.class)
    public void Load_Work_Orders_Job_List(){
        woDetails.Search_And_Click_WONumber(appEnv.getWorkOrderNumber());

        int GUIValue =  woDetails.GetNumberofRows("//div[@data-test-id='woJobListSummaryGrid']//*[starts-with(@data-test-id,'gridBodyRow')]");
        int TotalColumns = woDetails.GetNumberofRows("//div[@data-test-id='woJobListSummaryGrid']//*[starts-with(@data-test-id,'gridBodyRow0')]//*[starts-with(@data-test-id,'gridBodyCell')]");
        int U2APIValue = response.jobs.size();
        int G3APIValue = g3WOResponse.Jobs.size();
        List U2APIJobList = response.jobs;
        List G3APIJobList = g3WOResponse.Jobs;
        for(int i =0; i< GUIValue; i++) {
            for (int j = 1; j <= U2APIJobList.size(); j++)
                System.out.println(appEnv.getDriver().findElement(By.xpath("//div[@data-test-id='woJobListSummaryGrid']//*[starts-with(@data-test-id,'gridBodyRow0')]//*[starts-with(@data-test-id,'gridBodyCell')]" +"["+j+"]")).getText());
        }


        for(int k =0; k< U2APIJobList.size(); k++)
            System.out.println(U2APIJobList.get(k));
        for(int l =0; l< G3APIJobList.size(); l++)
            System.out.println(G3APIJobList.get(l));

        System.out.println("Work Order Parts Percentage is   : " + GUIValue);
        System.out.println("Work Order API Parts Percentage is   : " + G3APIValue);
        System.out.println("Work Order Parts Percentage from Universe DB is   : " + U2APIValue);
        appEnv.setTestPass(U2APIValue == GUIValue && G3APIValue == GUIValue);
        appEnv.getReportManager().LogStepInfo("Number of Job in Work Order Job List is : " + GUIValue);
        Utils.VerifyResult("Correct WO Jobs Loaded  ", appEnv.isTestPass());
    }


}
