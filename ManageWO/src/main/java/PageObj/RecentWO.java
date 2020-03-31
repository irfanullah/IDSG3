package PageObj;


import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will manage page objects of Recent Work Orders Page
 */
public class RecentWO {


    private static AppEnv appEnv;
    private General Utils;
    private Fetch_Elements fetch_elements;
    List<String> Locators  = new ArrayList<>();;
    List<String> Values  = new ArrayList<>();

    public RecentWO(AppEnv appEnvo){
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnvo);

    }

    public void Click_Search_Button(){
        Locators.add("xpath");
        Values.add("//*[@data-test-id=\"searchButton\"]");
        Utils.ClickObj(fetch_elements.GetObj(Locators,Values));
        Locators.remove("xpath");
        Values.remove("//*[@data-test-id=\"searchButton\"]");
    }
    public void Click_Clear_Button(){
        Locators.add("xpath");
        Values.add("//*[@data-test-id=\"clearButton\"]");
        Utils.ClickObj(fetch_elements.GetObj(Locators,Values));
        Locators.remove("xpath");
        Values.remove("//*[@data-test-id=\"clearButton\"]");
    }
    public void Click_More_Filters_Button(){
        Locators.add("xpath");
        Values.add("//*[@data-test-id=\"filtersButton-Collapsed\"]");
        Utils.ClickObj(fetch_elements.GetObj(Locators,Values));
        Locators.remove("xpath");
        Values.remove("//*[@data-test-id=\"filtersButton-Collapsed\"]");
    }

    public void Click_Less_Filters_Button(){
        Locators.add("xpath");
        Values.add("//*[@data-test-id=\"filtersButton-Expanded\"]");
        Utils.ClickObj(fetch_elements.GetObj(Locators,Values));
        Locators.remove("xpath");
        Values.remove("//*[@data-test-id=\"filtersButton-Expanded\"]");
    }

    public void Type_WO_Number(String WONumber)
    {
        Locators.add("name");
        Values.add("workOrderNumber");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),WONumber);
        Locators.remove("name");
        Values.remove("workOrderNumber");
    }
    public void Type_First_Name(String FirstName)
    {
        Locators.add("name");
        Values.add("firstName");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),FirstName);
        Locators.remove("name");
        Values.remove("firstName");
    }
    public void Type_Last_Name(String LastName)
    {
        Locators.add("name");
        Values.add("lastName");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),LastName);
        Locators.remove("name");
        Values.remove("lastName");
    }
    public void Type_Phone_Number(String PhoneNumber)
    {
        Locators.add("name");
        Values.add("phoneNumber");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),PhoneNumber);
        Locators.remove("name");
        Values.remove("phoneNumber");
    }
    public void Type_Customer_Number(String CustomerNumber)
    {
        Locators.add("name");
        Values.add("customerNumber");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),CustomerNumber);
        Locators.remove("name");
        Values.remove("customerNumber");
    }

    public void Type_Stock_Number(String StockNumber)
    {
        Locators.add("name");
        Values.add("stockNumber");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),StockNumber);
        Locators.remove("name");
        Values.remove("stockNumber");
    }

    public void Type_Completed_With_in_Days(String completedWithinDays)
    {
        Locators.add("name");
        Values.add("completedWithinDays");
        Utils.SendText(fetch_elements.GetObj(Locators,Values),completedWithinDays);
        Locators.remove("name");
        Values.remove("completedWithinDays");
    }

   //This methods locates table, counts number of rows and columsn and displays data of table
    public void Dislay_Row_Elements() {
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
                System.out.print("[" + i + "]" + "[" + j + "]" + Table_data +"  ");
            }
            System.out.println("");
            System.out.println("");
        }


    }

    //This function will return number of rows in given table
    public int Count_Table_Rows(String xPath){
        List rows = appEnv.getDriver().findElementsByXPath(xPath);
        int Row_count =  rows.size();
        return Row_count;

    }

    //This function will return ture if given element exit in the table otherwise false
    public boolean Search_Table_with_String(String Search_Item){

        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        List Columns = appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"][1]/*[@data-test-id=\"gridBodyCell\"]"));
        int Col_count = Columns.size();
        String first_part = "//*[@data-test-id=";
        String second_part ="'gridBodyRow'";
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
    public void Display_Element_Coordinates(String Search_Items){

        List  rows =  appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"]/*[@data-test-id=\"gridBodyCell\"][1]"));
        int Row_count =  rows.size();
        List Columns = appEnv.getDriver().findElements(By.xpath("//*[@data-test-id=\"gridBodyRow\"][1]/*[@data-test-id=\"gridBodyCell\"]"));
        int Col_count = Columns.size();
        String first_part = "//*[@data-test-id=";
        String second_part ="'gridBodyRow'";
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
                if (Table_data.equalsIgnoreCase(Search_Items)) {
                    System.out.print("Item found at following coordinates [" + i + "]" + "[" + j + "]" + Table_data + "  ");
                }
            }

        }

    }
}





