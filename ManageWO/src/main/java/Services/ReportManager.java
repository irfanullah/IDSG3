package Services;

import Drivers.BrowserManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReportManager {

    private static ReportManager reportManager = new ReportManager();
    private static AppEnv appEnv = new AppEnv();
    private static ExtentReports extentReports = null;
    private static ExtentTest extentTest = null;
    private static General Utils = null;

    private ReportManager() {
    }

    /* Static 'instance' method */
    public static ReportManager getInstance(AppEnv appEnv) {
        ReportManager.appEnv = appEnv;
        Utils = General.getInstance(appEnv);
        extentReports = new ExtentReports();
        appEnv.setReportName(Utils.Get_TimeStamp());
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./src/main/resources/Reports/" + appEnv.getReportName());

        htmlReporter.setAppendExisting(true);
        htmlReporter.config().setChartVisibilityOnOpen(false);
        extentReports.attachReporter(htmlReporter);
        return reportManager;
    }

    /**
     *
     */
    public void TestEnvironment()
    {
        extentReports.setSystemInfo("Server : ", appEnv.getEnvironment());
        extentReports.setSystemInfo("Browser : ", appEnv.getBrowser());
        extentReports.setSystemInfo("User : ", System.getProperty("user.name"));
        extentReports.setSystemInfo("OS : ", System.getProperty("os.name"));
        extentReports.setSystemInfo("JDK : ", System.getProperty("java.version"));

    }
    public void InitReport(String strTestName, String strTestDescription)
    {
        extentTest = extentReports.createTest(strTestName, strTestDescription);
    }
    public void LogTestStep(boolean bStatus, String strTestStep)
    {
        if(bStatus)
        {
            extentTest.log(Status.PASS, strTestStep);
            System.out.println("Pass : " + strTestStep);
        }else
        {
            extentTest.log(Status.FAIL, strTestStep);
            System.out.println("Fail : " + strTestStep);
        }
    }
    public void LogStepInfo(String strTestStep)
    {
        if( extentTest !=null)
        {
            extentTest.log(Status.INFO, strTestStep);
            System.out.println(strTestStep);
        }
    }
    public void EndReport()
    {
        if(extentTest!=null)
            extentReports.flush();
    }

public void vAttach_Image_With_Email(){

        BrowserManager wdBrowser = BrowserManager.getInstance(appEnv);
        wdBrowser.Launch_Browser();
        RemoteWebDriver local = appEnv.getDriver();
        String strEmail_Image_Path = "./src/main/resources/Reports/emailable-extent.png";
        String strFile = Paths.get("src/main/resources/Reports/"+appEnv.getReportName()).toAbsolutePath().toString();
    //    String strFile = "E:/JavaPrograms/IDSG3/ManageWO/src/main/resources/Reports/" + appEnv.getReportName();
        List<String> htmlFileContent = Arrays.asList(
               "<html>\n",
                "<body>\n",
               "<p><img src=\""+"emailable-extent.png"+"\" alt=\"Results\" ></p>\n",
               "</body>\n",
               "</html>\n");
        Path htmlFilePath = Paths.get("./src/main/resources/Reports/emailable-extent.html");
        try{
            local.get(strFile);
            List<WebElement> lstweList = local.findElements(By.xpath("//ul[@id='slide-out']/li"));
            Utils.ClickObj(lstweList.get(lstweList.size()-1));
            File source = local.findElement(By.id("dashboard-view")).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(strEmail_Image_Path));
            Files.write(htmlFilePath,htmlFileContent, Charset.forName("UTF-8"));
        } catch (Exception e) {
            System.out.println("Email File Saving Failed");
            if(local.getSessionId() != null) {
                wdBrowser.Kill_Driver();
                local = null;
            }
        }
        finally {
            if(local != null) {
                wdBrowser.Kill_Driver();
            }
        }

    }


}
