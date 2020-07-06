package PageObj;

import Drivers.Fetch_Elements;
import Services.AppEnv;
import Services.General;

public class WODetails {

    private static WODetails woDetails = new WODetails();
    private static AppEnv appEnv = new AppEnv();
    private static Fetch_Elements fetch_elements ;
    private static General Utils;
    private WODetails() {
    }

    /* Static 'instance' method */
    public static WODetails getInstance(AppEnv appEnv) {
        WODetails.appEnv = appEnv;
        Utils= General.getInstance(appEnv);
        fetch_elements = Fetch_Elements.getInstance(appEnv);
    return woDetails;
    }

    public String GetWODate(){
        Utils.waitTillXpathPresent("//*[@id=\"root\"]//div[starts-with(@class,'Card_container')][1]//div[starts-with(@class,'CardLineItem_container')][1]//div[2]", 30);
        return  Utils.GetText(fetch_elements.GetObj("xpath","//*[@id=\"root\"]//div[starts-with(@class,'WorkOrderHeader_container')]//div[@class=\"col-12\"][1]//div[starts-with(@class,'Card_container')][1]//div[starts-with(@class,'Card_dataCol')][1]//div[starts-with(@class,'CardLineItem_container')][1]//div[2]"));
    }


}
