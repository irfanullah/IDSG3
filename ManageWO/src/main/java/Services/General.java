package Services;

import PageObj.WODetails;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * This is class for wrapping webdriver methods.
 */
public class General {

    private static General general = new General();
    private static AppEnv appEnv = new AppEnv();
    private static WODetails woDetails;



    /* Static 'instance' method */
    public static General getInstance(AppEnv appEnv) {
        General.appEnv = appEnv;
       // woDetails = WODetails.getInstance(appEnv);
        return general;
    }

    /**
     * This method is for Clicking button.
     *
     * @param ButtonID
     */
    public boolean ClickObj(WebElement ButtonID) {
        boolean State;
        try {
            ButtonID.click();
            State = true;
        } catch (Exception e) {
            State = false;
            System.out.println("Unable to Click Button");
        }
        return State;
    }

  /*
   This method will return given item text
   */
  public String GetText(WebElement WebItem){
        String ElementText = null;
        try {
            ElementText= WebItem.getText();
        }catch (Exception e) {
            System.out.println("Unable to Retreive Object Text");
        }
        return ElementText;
    }

    /**
     * This is general method to wait for a moment in MS
     * @param TimeInMS In milli second
     */

    public void StaticWait(long TimeInMS){

        try {
            Thread.sleep(TimeInMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will wait for an element until wait timeout
     * @param Obj: Input a Webelement
     * @param TimeInSec: Timeout
     * @return boolean
     */
    public boolean Wait_For_Element(WebElement Obj, long TimeInSec){
        boolean State = true;
        int Counter = 0;
        while (State){
            State = IsObjExist(Obj);
            StaticWait(1000);
            Counter++;
            if(Counter > TimeInSec){
                break;
            }
        }
        return State;
    }

    public void waitTillXpathPresent(String xpath, int waitTimeSec) {
        WebDriverWait wait = new WebDriverWait(appEnv.getDriver(), waitTimeSec);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitTillNamePresent(String name, int waitTimeSec) {
        WebDriverWait wait = new WebDriverWait(appEnv.getDriver(), waitTimeSec);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }
  /*  public boolean Wait_For_Element_By_Xpath(String Xpath, long TimeInSec){
        WebDriverWait(appEnv.getDriver(),10).
        boolean State = true;
        int Counter = 0;
        while (State){
            State = IsObjExist(Obj);
            StaticWait(1000);
            Counter++;
            if(Counter > TimeInSec){
                break;
            }
        }
        return State;
    }*/
    /**
     * This method will use to wait an element to display
     *
     * @param Obj: This parameter will use to verify object exist or not
     */
    public boolean IsObjExist(WebElement Obj){
        boolean State;
        try {
            State = Obj.isDisplayed();
        } catch (Exception e) {
            State = false;
        }
        return State;

    }

    /**
     * This method is for adding text to a field.
     *
     * @param SendText
     * @param Text
     */
    public void SendText(WebElement SendText, String Text) {
        try {
            SendText.clear();
            SendText.sendKeys(Text);
        } catch (Exception e) {
            System.out.println("Unable to Input Text in Given field");
        }
    }

    /**
     * This method is to clear text in a field
     *
     * @param ClearField
     */
    public void ClearField(WebElement ClearField) {
        try {
            ClearField.clear();
        } catch (Exception e) {
            System.out.println("Unable to Clear field");
        }
    }

    /**
     * This method is to select objects from dropdown
     *
     * @param DrpDown
     * @param index
     */
    public void SelectDropdown(Select DrpDown, int index) {
        try {
            DrpDown.selectByIndex(index);
        } catch (Exception e) {
            System.out.println("Unable to Select Drop down field");
        }
    }



    /**
     * This method will use to make assertion in test cases
     * @param strActualResult: Provide actual result
     * @param strExpectedResult: Provide expected result
     */
    public void VerifyResult(String strActualResult, String strExpectedResult) {
        SoftAssert assertion = new SoftAssert();
        if (appEnv.isTestPass()) {
            System.out.println("Expected Result: " + strExpectedResult);
            System.out.println("Actual Result: " + strActualResult);
        }
        assertion.assertTrue(appEnv.isTestPass(), strActualResult);
        assertion.assertAll();
    }

    /**
     *
     * @param driver
     * @param weElement
     */
    public void HighLightElement(RemoteWebDriver driver, WebElement weElement) {
        driver.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                weElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        driver.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", weElement);
    }

    /**
     * This is overloading method of VerifyResult provide only boolean decision and reason to fail a test
     * @param Msg: Reason why this test is failing
     * @param Condition: Test Pass/Fail decision
     */
    public void VerifyResult(String Msg, boolean Condition) {
        SoftAssert assertion = new SoftAssert();
        assertion.assertTrue(Condition, Msg);
        assertion.assertAll();
    }

    /**
     * This Method will return file for reports file having time stamp in the given format
     * @return
     */
    public String Get_TimeStamp()
    {
        DateTime dt = new DateTime();
        String filename = dt.toString("SSSSssmmww'_AutomationReport.html" );
        System.out.println(filename);
        return filename;
    }

    public void setToken(String token) {
        Token = token;
    }

    String Token = null;

    private General() {
    }

    //This methods locates table, counts number of rows and columsn and displays data of table
    public void Dislay_Row_Elements() {
        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        List Columns = appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"][1]/*[@data-test-id=\"gridBodyCell\"]"));
        int Col_count = Columns.size();
        System.out.println("Number of Rows are : "+ Row_count + "\nNumber of Columns are : " + Col_count);
        String first_part = "//*[@data-test-id=";
        String second_part ="'gridBodyRow'";
        String third_part = "][";
        String four_part = "]/*[@data-test-id=";
        String five_part = "'gridBodyCell'";
        String six_part = "][";
        String seven_part = "]";
        //Used for loop for number of rows.
        for (int i=1; i<=Row_count; i++){
            //Used for loop for number of columns.
            for(int j=1; j<=Col_count; j++){
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part+second_part+third_part+i+four_part+five_part+six_part+j+seven_part;
                //Will retrieve value from located cell and print It.
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                System.out.print("[" + i + "]" + "[" + j + "]" + Table_data +"  ");
            }
            System.out.println("");
            System.out.println("");
        }


    }

    //This function will return number of rows in given table
    public int Count_Table_Rows(){
        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        return rows.size();

    }

    //This function will return true if given element exit in the table otherwise false
    public boolean Search_Table_with_String(String Search_Item){

        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        List Columns = appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\'gridBodyRow0\'][1]/*[@data-test-id=\'gridBodyCell\']"));
        int Col_count = Columns.size();
        String first_part = "//*[@data-test-id=";
        String second_part ="'gridBodyRow0'";
        String third_part = "][";
        String four_part = "]/*[@data-test-id=";
        String five_part = "'gridBodyCell'";
        String six_part = "][";
        String seven_part = "]";
        //Used for loop for number of rows.
        for (int i=1; i<=Row_count; i++) {
            //Used for loop for number of columns.
            for (int j = 1; j <= Col_count; j++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + second_part + third_part + i + four_part + five_part + six_part + j + seven_part;
                //Will retrieve value from located cell and print It.
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                if (Table_data.equalsIgnoreCase(Search_Item)) {
                    System.out.print("Item " + Search_Item + " Found at " + "[" + i + "]" + "[" + j + "] " + Table_data +"  ");
                    return true;
                }
            }

        }
        return false;
    }

    //This function will print coordinates of given element.
    public void Display_Element_Coordinates(){

        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        List Columns = appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow0\"]/*[@data-test-id=\"gridBodyCell\"]"));
        int Col_count = Columns.size();
        String first_part = "//*[@data-test-id=";
        String second_part ="'gridBodyRow0'";
        String third_part = "][";
        String four_part = "]/*[@data-test-id=";
        String five_part = "'gridBodyCell'";
        String six_part = "][";
        String seven_part = "]";
        //Used for loop for number of rows.
        for (int i=1; i<=Row_count; i++) {
            //Used for loop for number of columns.
            for (int j = 1; j <= Col_count; j++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + second_part + third_part + i + four_part + five_part + six_part + j + seven_part;
                //Will retrieve value from located cell and print It.
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                                   System.out.print("Item found at following coordinates [" + i + "]" + "[" + j + "]" + Table_data + "  ");
                }

        }

    }

    public boolean Find_Customer(String Search_Item){
        List rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[@data-test-id=";
            String second_part = "'gridBodyRow0'";
            String third_part = "][";
            String four_part = "]/*[@data-test-id=";
            String five_part = "'gridBodyCell'";
            String six_part = "][";
            String seven_part = "]";
            for (int i = 1; i <= Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + second_part + third_part + i + four_part + five_part + six_part + "6" + seven_part;
                //Will retrieve value from located cell and print It.
                System.out.println("\n Customer Column data : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                Table_data = Table_data.replaceAll("[\\[\\](){}]", "");
                String[] strArray = Table_data.split(" ");
                for (int j = 0; j < strArray.length; j++) {
                    System.out.println("\nCustomer Column Data is : " + strArray[j]);
                    if (strArray[j].equalsIgnoreCase(Search_Item)) {
                        return true;
                    }
                }

            }


        }
        return false;
    }

    public boolean Find_First_Name(String Search_Item){

        List Rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  Count_Table_Rows();
        int Matching_Record_Found = 0;
        int Non_Matching_Record_Found = 0;
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[starts-with(@data-test-id,'gridBodyRow";
            String second_part = "')]/*[@data-test-id=\'gridBodyCell\'][6]";
            for (int i = 0; i < Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part  + i + second_part;
                //Will retrieve value from located cell and print It.
                System.out.println(final_xpath);
                System.out.println("Customer Data Loaded  : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                Table_data = Table_data.replaceAll("[\\[\\](){}]", "");
                String[] strArray = Table_data.split(" ");
                System.out.println("\n First Name Found : " + strArray[0]);
                if (strArray[0].equalsIgnoreCase(Search_Item))
                        Matching_Record_Found++;
                else
                        Non_Matching_Record_Found++;
            }
        }
        if(Non_Matching_Record_Found == 0)
            return true;
        else
            return false;
    }
    public boolean Find_Last_Name(String Search_Item){

        int Row_count =  Count_Table_Rows();
        int Matching_Record_Found = 0;
        int Non_Matching_Record_Found = 0;
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[starts-with(@data-test-id,'gridBodyRow";
            String second_part = "')]/*[@data-test-id=\'gridBodyCell\'][6]";
            for (int i = 0; i < Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + i + second_part;
                //Will retrieve value from located cell and print It.
                System.out.println(" Customer Data Loaded  : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                Table_data = Table_data.replaceAll("[\\[\\](){}]", "");
                String[] strArray = Table_data.split(" ");
                System.out.println("\n Last Name Found  : " + strArray[1]);
                if (strArray[1].equalsIgnoreCase(Search_Item))
                    Matching_Record_Found++;
                else
                    Non_Matching_Record_Found++;
            }
        }
        if(Non_Matching_Record_Found == 0)
            return true;
        else
            return false;
    }
    public boolean Find_Customer_Number(String Search_Item){

        List Rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  Count_Table_Rows();
        int Matching_Record_Found = 0;
        int Non_Matching_Record_Found = 0;
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[starts-with(@data-test-id,'gridBodyRow";
            String second_part = "')]/*[@data-test-id=\'gridBodyCell\'][6]";
            for (int i = 0; i < Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + i + second_part;
                //Will retrieve value from located cell and print It.
                System.out.println(" Customer Data Loaded  : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                Table_data = Table_data.replaceAll("[\\[\\](){}]", "");
                String[] strArray = Table_data.split(" ");
                System.out.println("\n Customer Number Found  : " + strArray[2]);
                if (strArray[2].equalsIgnoreCase(Search_Item))
                    Matching_Record_Found++;
                else
                    Non_Matching_Record_Found++;
            }
        }
        if(Non_Matching_Record_Found == 0)
            return true;
        else
            return false;
    }

    public boolean Find_Stock_Number(String Search_Item){

        int Row_count =  Count_Table_Rows();
        int Matching_Record_Found = 0;
        int Non_Matching_Record_Found = 0;
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[starts-with(@data-test-id,'gridBodyRow";
            String second_part = "')]/*[@data-test-id=\'gridBodyCell\'][7]";
            for (int i = 0; i < Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + i + second_part;
                //Will retrieve value from located cell and print It.
                System.out.println(" Number Loaded  : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                if (Table_data.equalsIgnoreCase(Search_Item))
                    Matching_Record_Found++;
                else
                    Non_Matching_Record_Found++;
            }
        }
        if(Non_Matching_Record_Found == 0)
            return true;
        else
            return false;
    }

    public boolean Find_WO_Number(String Search_Item){

        List Rows =  appEnv.getDriver().findElements(By.xpath("//*[starts-with(@data-test-id,'gridBodyRow')]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  Count_Table_Rows();
        int Matching_Record_Found = 0;
        int Non_Matching_Record_Found = 0;
        System.out.println("\n Number of rows loaded : " + Row_count);
        if(Row_count > 0) {
            String first_part = "//*[starts-with(@data-test-id,'gridBodyRow";
            String second_part = "')]/*[@data-test-id=\'gridBodyCell\'][1]";
            for (int i = 0; i < Row_count; i++) {
                //Prepared final xpath of specific cell as per values of i and j.
                String final_xpath = first_part + i + second_part;
                //Will retrieve value from located cell and print It.
                System.out.println(" Number Loaded  : " + appEnv.getDriver().findElement(By.xpath(final_xpath)).getText());
                String Table_data = appEnv.getDriver().findElement(By.xpath(final_xpath)).getText();
                if (Table_data.equalsIgnoreCase(Search_Item))
                    Matching_Record_Found++;
                else
                    Non_Matching_Record_Found++;
            }
        }
        if(Non_Matching_Record_Found == 0)
            return true;
        else
            return false;
    }

    public void Scroll_Down_To_Bottom(){
        JavascriptExecutor js = (JavascriptExecutor) appEnv.getDriver();
        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        Actions actions = new Actions(appEnv.getDriver());

        // Scroll Down using Actions class
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }




}

/*--------------------------------------------------------------------------------------------------------------------*/