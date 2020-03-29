package PageObj;


import Services.AppEnv;
import Services.General;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class will manage page objects of Recent Work Orders Page
 */
public class RecentWO {


    private static AppEnv appEnv;
    private General Utils;

    public RecentWO(AppEnv appEnvo){
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);

    }

    //This functions will find, clear and type a webelement.
    public void Find_And_Type_Element(String elementName, String fieldValue){
    /*    WebElement webElement = null;
        switch (elementName){
            case "WorkOrderNumber":
            {

            }
                break;
            case "FirstName":
                break;
            case "LastName":
                break;
            case "PhoneNumber":
                break;
            case "IncludeOpenWOs":
                break;
            case "IncludeWOsCompletedWithinDays":
                break;
            case "CustomerNumber":
                break;
            case "StockNumber":
                break;
            case "ClearButton":
                break;
            case "SearchButton":
                break;
            case "MoreFiters":
                break;
            case "LessFiters":
                break;
                default:
                    System.out.println("No Matching Elements Name Found");

        }*/
        WebElement webElement = appEnv.getDriver().findElementByName(elementName);
        webElement.clear();
        webElement.sendKeys(fieldValue);
    }
    public void Click_Search_Button(){

        WebElement btSearch = appEnv.getDriver().findElementByXPath("//*[@data-test-id=\"searchButton\"]");
        boolean GetStatus = Utils.ClickObj(btSearch);

    }
    public void Click_Clear_Button(){

        WebElement btClear = appEnv.getDriver().findElementByXPath("//*[@data-test-id=\"clearButton\"]");
        boolean GetStatus = Utils.ClickObj(btClear);
    }
    public void Click_More_Filters_Button(){

        WebElement btMoreFilters = appEnv.getDriver().findElementByXPath("//*[@data-test-id=\"filtersButton-Collapsed\"]");
        boolean GetStatus = Utils.ClickObj(btMoreFilters);
    }

    public void Click_Less_Filters_Button(){
            WebElement btLessFilters = appEnv.getDriver().findElementByXPath("//*[@data-test-id=\"filtersButton-Expanded\"]");
            boolean GetStatus = Utils.ClickObj(btLessFilters);

    }

    public void Type_WO_Number(String WONumber)
    {
        Find_And_Type_Element("workOrderNumber", WONumber);
    }
    public void Type_First_Name(String FirstName)
    {
        Find_And_Type_Element("firstName",FirstName);
    }
    public void Type_Last_Name(String LastName)
    {
        Find_And_Type_Element("lastName", LastName);
    }
    public void Type_Phone_Number(String PhoneNumber)
    {
        Find_And_Type_Element("phoneNumber", PhoneNumber);
    }
    public void Type_Customer_Number(String CustomerNumber)
    {
        Find_And_Type_Element("customerNumber", CustomerNumber);
    }

    public void Type_Stock_Number(String StockNumber)
    {
        Find_And_Type_Element("stockNumber", StockNumber);
    }

    public void Type_Completed_With_in_Days(String completedWithinDays)
    {
        Find_And_Type_Element("completedWithinDays", completedWithinDays);
    }

   //This methods locates table, counts number of rows and columsn and displays data of table
    public void Load_Table_Data() {
        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"]/*[@data-test-id=\"gridBodyCell\"][1]"));
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
                System.out.print(Table_data +"  ");
            }
            System.out.println("");
            System.out.println("");
        }


    }



}
