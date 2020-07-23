package TestManager;

import Services.AppEnv;
import Services.General;
import org.testng.IInvokedMethod;
import org.testng.ITestNGMethod;

/**
 * This class will assign parameters to test cases
 */
public class TestFiltration {
    private static TestFiltration testFiltrationObj = new TestFiltration();
    private static AppEnv appEnv = new AppEnv();
    private static General Utils = null;

    private TestFiltration() {
    }

    /* Static 'instance' method */
    public static TestFiltration getInstance(AppEnv appEnv) {
        TestFiltration.appEnv = appEnv;
        Utils = General.getInstance(appEnv);
        return testFiltrationObj;
    }

    public void Test_Assignment(IInvokedMethod iInvokedMethod){
        ITestNGMethod methodName = iInvokedMethod.getTestMethod();
        String TestDescription = "";
        switch (methodName.getMethodName()) {
            case "Login_With_EmailAddress":
                appEnv.setLogInReq(false);
                TestDescription = "This test will log in the user";
                break;
            case "Search_WO_By_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders by Work Order Number";
                break;
            case "Search_Open_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Open Work Orders by Stock Number";
                break;

            case "Search_Open_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders by First Name";
                break;
            case "Search_Open_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Open Work Orders by Last Name";
                break;
            case "Search_Completed_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Completed Search Work Orders by Customer Number";
                break;
            case "Search_Open_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders ";
                break;
            case "Search_Open_WO_Against_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders ";
                break;
            case "Search_Completed_WO_Against_Customer_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Days_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Days_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Completed_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Cancelled_WO_Against_Customer_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Completed_WO_Against_Customer_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_And_Cancelled_WO_Against_Customer_And_Stock_Number":
                    appEnv.setLogInReq(true);
                    TestDescription = "This test will Search Work Orders";
                    break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_Completed_And_Cancelled_WO_Against_Customer_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_Open_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_First_Name_And_Last_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_First_Name_Last_Name_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_First_Name_Last_Name_And_Customer_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_Customer_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;
            case "Search_WO_Against_WO_First_Name_Last_Name_Customer_Number_And_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders";
                break;

        default:
            appEnv.setLogInReq(false);
            break;
        }
        appEnv.getReportManager().InitReport(methodName.getMethodName() , TestDescription);
    }
}
