package TestManager;

import Drivers.BrowserManager;
import PageObj.LoginPage;
import Services.*;
import org.testng.*;

/**
 * This is suite listener class to setup a suite before its execution
 */

public class SuiteListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
    private final SystemConfiguration SysConfig = SystemConfiguration.getInstance(appEnv);
    private BrowserManager browserManager = null;
  //  private SendMailSSLWithAttachment sendMailSSLWithAttachment = SendMailSSLWithAttachment.getInstance(appEnv);
    public static AppEnv appEnv = new AppEnv();
    public static EmailAdopter emailAdopter = null;
    public static ReportManager reportManager = null;
    public static ReportManager getReportManager(){
        return reportManager;
    }


    @Override
    public void onStart(ISuite iSuite) {
        appEnv = SysConfig.Read_Properties(appEnv);
        browserManager = BrowserManager.getInstance(appEnv);
        emailAdopter = EmailAdopter.getInstance(appEnv);
        browserManager.Launch_Browser();
        appEnv.setReportManager(ReportManager.getInstance(appEnv));
        appEnv.setRestManager(RestManager.getInstance(appEnv));
        appEnv.getReportManager().TestEnvironment();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        appEnv.getReportManager().EndReport();
        browserManager.Kill_Driver();
        if(appEnv.getSendReportEmail().equalsIgnoreCase("true")) {
            appEnv.getReportManager().vAttach_Image_With_Email();
            emailAdopter.Email_Report();
        }
    }


    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        TestFiltration testFiltration = TestFiltration.getInstance(appEnv);
        testFiltration.Test_Assignment(iInvokedMethod);
        browserManager.GetURL();
        appEnv.setTestPass(false);
        System.out.println(iInvokedMethod.getTestMethod().getMethodName() + " Started");
        /** Log In the session if required */
        LoginPage loginPage = new LoginPage(appEnv);
        if(appEnv.isLogInReq() && !(loginPage.IsSession_Logged_In())){
            appEnv.getReportManager().LogStepInfo("Login Called from Before Invocation for " + iInvokedMethod.getTestMethod().getMethodName() + "Test");
            loginPage.LogIn(appEnv.getUserID(),appEnv.getPassword());
        }

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println(iInvokedMethod.getTestMethod().getMethodName() + " Completed");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Started  " +  iTestResult.getName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        appEnv.getReportManager().LogTestStep(true,iTestResult.getName()+ " Passed ");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        appEnv.getReportManager().LogTestStep(false,iTestResult.getName()+ " Failed ");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {


    }

}
