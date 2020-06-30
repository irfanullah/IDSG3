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
            case "Login":
                appEnv.setLogInReq(false);
                TestDescription = "This test will log in the user";
                break;
            case "Search_WO_By_WO_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders by Work Order Number";
                break;
            case "Search_WO_By_Stock_Number":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders by Stock Number";
                break;

            case "Search_WO_By_First_Name":
                appEnv.setLogInReq(true);
                TestDescription = "This test will Search Work Orders by First Name";
                break;

        default:
            appEnv.setLogInReq(false);
            break;
        }
        appEnv.getReportManager().InitReport(methodName.getMethodName() , TestDescription);
    }
}
