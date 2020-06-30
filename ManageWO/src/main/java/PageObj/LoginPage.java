package PageObj;

import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;

/**
 * This class will list have all the objects from Login Screen.
 */
public class LoginPage {


    private static AppEnv appEnv;
    private General Utils;
    private Fetch_Elements fetch_elements;

    public LoginPage(AppEnv appEnvo){
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnvo);
    }

    /**
     * This Method will Type Email/Username on Login Screen.
     * @param Email
     */
    public void Type_Email_Address(String Email){
        appEnv.getReportManager().LogStepInfo("Type Email Address :  " + Email);
        Utils.SendText(fetch_elements.GetObj("name","email"),Email);
    }

    /**
     * This Method will Type Password on Login Screen.
     * @param Password
     */
    public void Type_Password(String Password){
        if(Utils.IsObjExist(fetch_elements.GetObj("name","password"))) {
            Utils.SendText(fetch_elements.GetObj("name", "password"), Password);
            appEnv.getReportManager().LogStepInfo("Type Password :  XXXXXXXXXXXX");
        }
    }

    /**
     * This method will click Sign in button on Login Screen.
     */
    public void Click_Sign_in_Button(){

        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@id=\"root\"]//button[@type= 'submit']"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'submit']"));
            appEnv.getReportManager().LogStepInfo("Click Sign in  button.");
        }
    }
    /**
     * This method will Click Go to Work Order button on Welcome Page.
     */
    public void Click_Work_Order_Button(){

        Utils.waitTillXpathPresent("//*[@id=\"root\"]//button[@type= 'button']", 30);
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath","//*[@id=\"root\"]//button[@type= 'button']"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'button']"));
            appEnv.getReportManager().LogStepInfo("Click Work Order Button");
        }
    }

    /**
     * This method will verify either session is logged in or not
     * @return boolean
     */
    public boolean IsSession_Logged_In(){
        return Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]")) ||
                Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'button']"));
    }

    /**
     * This method will login and and returns a boolen value
     * @param Email
     * @param Password
     * @return
     */
    public boolean LogIn(String Email, String Password){
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//button[@type= 'submit']", 30);
        Type_Email_Address(Email);
        Type_Password(Password);
        Click_Sign_in_Button();
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//button[@type= 'button']", 30);
        Click_Work_Order_Button();
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]", 30);
        return IsSession_Logged_In();
    }

}
