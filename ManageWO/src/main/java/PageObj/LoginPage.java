package PageObj;

import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * This class will list have all the objects from Login Screen.
 */
public class LoginPage
{


    private static AppEnv appEnv;
    private General Utils;
    private Fetch_Elements fetch_elements;

    public LoginPage(AppEnv appEnvo) {
        appEnv = appEnvo;
        Utils = General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnvo);
    }

    /**
     * This Method will Type Email/Username on Login Screen.
     *
     * @param Email
     */
    public void Type_Email_Address(String Email) {
        if (Utils.IsObjExist(fetch_elements.GetObj("name", "userId"))) {
            Utils.SendText(fetch_elements.GetObj("name", "userId"), Email);
            appEnv.getReportManager().LogStepInfo("Type Email or UserID Address :  " + Email);
        }
    }

    /**
     * This Method will Type Password on Login Screen.
     *
     * @param Password
     */
    public void Type_Password(String Password) {
        if (Utils.IsObjExist(fetch_elements.GetObj("name", "password"))) {
            Utils.SendText(fetch_elements.GetObj("name", "password"), Password);
            appEnv.getReportManager().LogStepInfo("Type Password :  XXXXXXXXXXXX");
        }
    }

    /**
     * This method will click Sign in button on Login Screen.
     */
    public void Click_Sign_in_Button() {

        if (Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'submit']"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'submit']"));
            appEnv.getReportManager().LogStepInfo("Click Sign in  button.");
            if (Utils.IsObjExist(fetch_elements.GetObj("xpath", "//div[starts-with(@class,'Popup_modalBody')]")))
            {
                Utils.ClickObj(fetch_elements.GetObj("xpath", "//button[starts-with(@class,'Popup_button')]"));
                Utils.ClickObj(fetch_elements.GetObj("xpath", "//button[starts-with(@class,'Popup_button')]"));
                appEnv.getReportManager().LogStepInfo("Click OK on Try Again Message");
                Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[@type= 'submit']"));
            }

        }
    }

    /**
     * This method will Click Go to Work Order button on Welcome Page.
     */
    public void Click_Work_Order_Button() {

        Utils.waitTillXpathPresent("/html/body/div/div/div/div[2]/div/a[1]/div/div", 30);
        if (Utils.IsObjExist(fetch_elements.GetObj("xpath", "/html/body/div/div/div/div[2]/div/a[1]/div/div"))) {
            Utils.ClickObj(fetch_elements.GetObj("xpath", "/html/body/div/div/div/div[2]/div/a[1]/div/div"));
            appEnv.getReportManager().LogStepInfo("Click Work Order Button");
        }
    }


    /**
     * This method will verify either session is logged in or not
     * @return boolean
     */
    public boolean IsSession_Logged_In()
    {
        return (Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[starts-with(@class, 'Header_title')]")));
    }

    /**
     * This method will login and and returns a boolen value
     * @param Email
     * @param Password
     * @return
     */
    public boolean LogIn(String Email, String Password){
        String Path = "./src/main/resources/DataSecure/Token.csv";
        System.out.println("Status Code Received : " +  appEnv.getRestManager().GetStatusCode());
        if(!(appEnv.getRestManager().GetStatusCode()==200)) {
            try{
                System.out.println("New Token Generated and Saved");
                File file = new File(Path);
                if(!file.exists()){
                    file.createNewFile();
                    System.out.println("New File Created");
                }
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(appEnv.getRestManager().GetAccessToken());
                System.out.println("New Token Added to File");
                bufferedWriter.close();
            }catch(Exception e){
                System.out.println(e);
            }
            appEnv.setToken(appEnv.getRestManager().GetAccessToken());

        }

        Utils.waitTillNamePresent("userId",30);
        Type_Email_Address(Email);
        Type_Password(Password);
        Click_Sign_in_Button();
        Utils.waitTillXpathPresent("//*[starts-with(@class, 'Header_title')]", 30);
        return IsSession_Logged_In();
    }

    public boolean Incorrect_Credentials(String UserName, String Password){
        Utils.waitTillNamePresent("userId",30);
        Type_Email_Address(UserName);
        Type_Password(Password);
        Click_Sign_in_Button();
        Utils.waitTillXpathPresent("//div[starts-with(@class,'Alert_container')]", 30);
        Utils.StaticWait(1000);
        if(Utils.IsObjExist(fetch_elements.GetObj("xpath", "//div[starts-with(@class,'Alert_container')]")))
            return true;
        else
            return false;
    }



    public void Logout()
    {
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]", 30);
        if (Utils.IsObjExist(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]")))
            Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//div[starts-with(@class, 'Avatar')]"));
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//button[starts-with(@class, 'UserMenu')]", 30);
        Utils.ClickObj(fetch_elements.GetObj("xpath", "//*[@id=\"root\"]//button[starts-with(@class, 'UserMenu')]"));
        appEnv.getReportManager().LogStepInfo("Click Sign Out  button.");
        Utils.waitTillNamePresent("userId",30);
    }


}

