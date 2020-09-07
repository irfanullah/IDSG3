package Services;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
    private boolean IsLogInReq = false;
    private String Email;
    private String Password;
    private int TotalWOAgainstWONumber;
    private int TotalWOAgainstCustomerNumber;
    private int TotalWOAgainstCustomerNumberAndStockNumber;
    private int TotalWOAgainstStockNumber;
    private int TotalWOAgainstFirstName;
    private int TotalWOAgainstLastName;
    private int TotalWOAgainstPhoneNumber;
    private int TotalWOAgainstFirstNameAndLastName;
    private int TotalWOAgainstFirstNameLastNameAndStockNumber;
    private int TotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber;
    private int TotalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber;
    private int TotalWOAgainstFirstNameLastNameAndCustomerNumber;
    private String CompletedWithinDays;
    private String APIAddress;
    private String UserID;
    private ReportManager reportManager = null;
    private RestManager restManager = null;
    private String Token;
    private String Location;
    private String Environment;
    private String ReportName;
    private String SendReportEmail;
    private String ToEmailAddress;
    private String CCEmailAddress;
    private String EncryptedPassword;

    public String getAPIBaseURL() {
        return APIBaseURL;
    }

    public void setAPIBaseURL(String APIBaseURL) {
        this.APIBaseURL = APIBaseURL;
    }

    private String APIBaseURL;

    public String getEncryptedPassword() {
        return EncryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        EncryptedPassword = encryptedPassword;
    }

    public String getToEmailAddress() {
        return ToEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        ToEmailAddress = toEmailAddress;
    }

    public String getCCEmailAddress() {
        return CCEmailAddress;
    }

    public void setCCEmailAddress(String CCEmailAddress) {
        this.CCEmailAddress = CCEmailAddress;
    }

    public String getSendReportEmail() {
        return SendReportEmail;
    }

    public void setSendReportEmail(String sendReportEmail) {
        SendReportEmail = sendReportEmail;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }

    public int getTotalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber() {
        return TotalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber;
    }

    public void setTotalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber(int totalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber) {
        TotalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber = totalWOAgainstWOFirstNameLastNameCustomerNumhberAndStockNumber;
    }

    public String getEnvironment() {
        return Environment;
    }

    public void setEnvironment(String environment) {
        Environment = environment;
    }

    public int getTotalWOAgainstWONumber() {
        return TotalWOAgainstWONumber;
    }

    public void setTotalWOAgainstWONumber(int totalWOAgainstWONumber) {
        TotalWOAgainstWONumber = totalWOAgainstWONumber;
    }

    public int getTotalWOAgainstFirstNameLastNameAndCustomerNumber() {
        return TotalWOAgainstFirstNameLastNameAndCustomerNumber;
    }

    public void setTotalWOAgainstFirstNameLastNameAndCustomerNumber(int totalWOAgainstFirstNameLastNameAndCustomerNumber) {
        TotalWOAgainstFirstNameLastNameAndCustomerNumber = totalWOAgainstFirstNameLastNameAndCustomerNumber;
    }

    public int getTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber() {
        return TotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber;
    }

    public void setTotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber(int totalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber) {
        TotalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber = totalWOAgainstFirstNameLastNameCustomerNumhberAndStockNumber;
    }

    public int getTotalWOAgainstFirstNameLastNameAndStockNumber() {
        return TotalWOAgainstFirstNameLastNameAndStockNumber;
    }

    public void setTotalWOAgainstFirstNameLastNameAndStockNumber(int totalWOAgainstFirstNameLastNameAndStockNumber) {
        TotalWOAgainstFirstNameLastNameAndStockNumber = totalWOAgainstFirstNameLastNameAndStockNumber;
    }

    public int getTotalWOAgainstFirstNameAndLastName() {
        return TotalWOAgainstFirstNameAndLastName;
    }

    public void setTotalWOAgainstFirstNameAndLastName(int totalWOAgainstFirstNameAndLastName) {
        TotalWOAgainstFirstNameAndLastName = totalWOAgainstFirstNameAndLastName;
    }

    public String getAPIAddress() {
        return APIAddress;
    }

    public void setAPIAddress(String APIAddress) {
        this.APIAddress = APIAddress;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getTotalWOAgainstCustomerNumber() {
        return TotalWOAgainstCustomerNumber;
    }

    public void setTotalWOAgainstCustomerNumber(int totalWOAgainstCustomerNumber) {
        TotalWOAgainstCustomerNumber = totalWOAgainstCustomerNumber;
    }

    public int getTotalWOAgainstCustomerNumberAndStockNumber() {
        return TotalWOAgainstCustomerNumberAndStockNumber;
    }

    public void setTotalWOAgainstCustomerNumberAndStockNumber(int totalWOAgainstCustomerNumberAndStockNumber) {
        TotalWOAgainstCustomerNumberAndStockNumber = totalWOAgainstCustomerNumberAndStockNumber;
    }

    public String getCompletedWithinDays() {
        return CompletedWithinDays;
    }

    public void setCompletedWithinDays(String completedWithinDays) {
        CompletedWithinDays = completedWithinDays;
    }

    public int getTotalWOAgainstLastName() {
        return TotalWOAgainstLastName;
    }

    public void setTotalWOAgainstLastName(int totalWOAgainstLastName) {
        TotalWOAgainstLastName = totalWOAgainstLastName;
    }

    public int getTotalWOAgainstPhoneNumber() {
        return TotalWOAgainstPhoneNumber;
    }

    public void setTotalWOAgainstPhoneNumber(int totalWOAgainstPhoneNumber) {
        TotalWOAgainstPhoneNumber = totalWOAgainstPhoneNumber;
    }

    public int getTotalWOAgainstFirstName() {
        return TotalWOAgainstFirstName;
    }

    public void setTotalWOAgainstFirstName(int totalWOAgainstFirstName) {
        TotalWOAgainstFirstName = totalWOAgainstFirstName;
    }

    public int getTotalWOAgainstStockNumber() {
        return TotalWOAgainstStockNumber;
    }

    public void setTotalWOAgainstStockNumber(int totalWOAgainstStockNumber) {
        TotalWOAgainstStockNumber = totalWOAgainstStockNumber;
    }

    public RestManager getRestManager() {
        return restManager;
    }

    public void setRestManager(RestManager restManager) {
        this.restManager = restManager;
    }

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
    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        Email=email;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        Password = password;
    }

    public void setToken(String token){
        Token = token;
    }

    public String getToken(){

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("./src/main/resources/DataSecure/Token.csv"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // Printing out each line in the file
                    Token = x;

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return Token;
    }


    public void setLocation(String location){
        Location = location;
    }
    public String getLocation(){
        return Location;
    }
}

/*--------------------------------------------------------------------------------------------------------------------*/