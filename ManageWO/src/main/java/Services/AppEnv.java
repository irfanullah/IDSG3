package Services;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class will use as a global class in our project
 */

public class AppEnv {

    private String Browser;
    private RemoteWebDriver Driver;
    private String Domain;
    private String WorkOrderNumber;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String StockNumber;
    private String CustomerNumber;
    private boolean IsTestPass;
    private boolean IsLogInReq;
    private ReportManager reportManager = null;

    /**
     * This mehtod is get domain name
     *
     * @return
     */
    public String getDomain() {
        return Domain;
    }

    /**
     * This method is to set domain name
     *
     * @param domain
     */
    public void setDomain(String domain) {
        Domain = domain;
    }

    /**
     * This method is to set domain name
     *
     * @param stockNumber
     */
    public void setStockNumber(String stockNumber){
        StockNumber = stockNumber;
    }

    /**
     * This method is to set domain name
     *
     * @param customerNumber
     */
    public void setCustomerNumber(String customerNumber){
        CustomerNumber = customerNumber;
    }

    /**
     * This method is to set Work Order Number
     */
    public void setWorkOrderNumber(String WONumber) {
        WorkOrderNumber = WONumber;
    }
    /**
     * This method is to set First Name
     */
    public void setFirstName(String FName){
        FirstName = FName;
    }
    /**
     * This method is to set Last Name
     */
    public void setLastName(String LName){
        LastName = LName;
    }
    /**
     * This method is to set Phone Number
     */
    public void setPhoneNumber(String PNumber){
        PhoneNumber = PNumber;
    }

    /**
     * This method will return First Name
     *
     * @return
     */
    public String getFirstName(){
        return FirstName;
    }
    /**
     * This method will return Last Name
     *
     * @return
     */
    public String getLastName(){
        return LastName;
    }
    /**
     * This method will return Phone Name
     *
     * @return
     */
    public String getPhoneNumber(){
        return PhoneNumber;
    }
    /**
     * This method will return Work Order Number
     *
     * @return
     */
    public String getWorkOrderNumber(){
     return WorkOrderNumber;
    }
    /**
     * This method will return Stock Number
     *
     * @return
     */
    public String getStockNumber(){
        return StockNumber;
    }
    /**
     * This method will return Customer Number
     *
     * @return
     */
    public String getCustomerNumber(){
        return CustomerNumber;
    }

    /**
     * This method will return WebDriver name ( Chrome or Firefox.)
     *
     * @return
     */
    public RemoteWebDriver getDriver() {
        return Driver;
    }

    /**
     * This method will set Webdriver Name
     *
     * @return
     */

    public void setDriver(RemoteWebDriver driver) {
        Driver = driver;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public boolean isTestPass() {
        return IsTestPass;
    }

    public void setTestPass(boolean testPass) {
        IsTestPass = testPass;
    }

    public boolean isLogInReq() {
        return IsLogInReq;
    }

    public void setLogInReq(boolean logInReq) {
        IsLogInReq = logInReq;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    public void setReportManager(ReportManager reportManager) {
        this.reportManager = reportManager;
    }
}

/*--------------------------------------------------------------------------------------------------------------------*/