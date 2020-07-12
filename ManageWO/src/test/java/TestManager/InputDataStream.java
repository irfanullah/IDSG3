package TestManager;

import Services.AppEnv;
import org.json.JSONObject;

public class InputDataStream {

    private static InputDataStream inputDataStream = new InputDataStream();
    private static AppEnv appEnv = new AppEnv();
    private static JSONObject jsonObject = null;

    private InputDataStream() {
    }

    /* Static 'instance' method */
    public static InputDataStream getInstance(AppEnv appEnv) {
        InputDataStream.appEnv = appEnv;
                jsonObject = new JSONObject("{\n" +
                        "  \"Location\": \"\",\n" +
                        "  \"Filters\": {\n" +
                        "    \"WorkOrderNo\": \"\",\n" +
                        "    \"IncludeOpen\": true,\n" +
                        "    \"IncludeCompleted\": false,\n" +
                        "    \"IncludeCancelled\": false,\n" +
                        "    \"IncludeCompletedWithinDays\": 0,\n" +
                        "    \"IncludeOpenedSinceDays\": 10000,\n" +
                        "    \"IncludeCancelledWithinDays\": 0,\n" +
                        "    \"CustomerNo\": \"\",\n" +
                        "    \"FirstName\": \"\",\n" +
                        "    \"StockNo\": \"\",\n" +
                        "    \"PhoneNo\": \"\",\n" +
                        "    \"LastName\": \"\"\n" +
                        "  },\n" +
                        "  \"Fields\": [\n" +
                        "    \"WorkOrderNumber\",\n" +
                        "    \"Status\",\n" +
                        "    \"CustomerLastName\",\n" +
                        "    \"CustomerFirstName\"\n" +
                        "  ],\n" +
                        "  \"PageNo\": 1,\n" +
                        "  \"invalidateCache\": false,\n" +
                        "  \"TotalRecordLimit\": 600\n" +
                        "}");
        return inputDataStream;
    }

    public  String SetOpenWOAgainstStockNo(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstCustomerNo(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstFirstName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();

    }

    public  String SetOpenWOAgainstLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstPhoneNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("PhoneNo",appEnv.getPhoneNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstWONumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("WorkOrderNo",appEnv.getWorkOrderNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstCustomerNumberAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }


    public  String SetCancelledWOWithinGivenDaysAgainstCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCompletedWOWithinGivenDaysAgainstStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCancelledWOWithinGivenDaysAgainstStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstCustomerAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstFirstName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstFirstName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstFirstName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCompletedWOWithinGivenDaysAgainstLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCancelledWOWithinGivenDaysAgainstLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstFirstNameAndLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameAndLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstFirstNameAndLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstWONumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("WorkOrderNo",appEnv.getWorkOrderNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstWONumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("WorkOrderNo",appEnv.getWorkOrderNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstWONumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("WorkOrderNo",appEnv.getWorkOrderNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstFirstNameLastNameAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameCustomerNumberAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenAndCompletedWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCompleted","true");
        clone.getJSONObject("Filters").put("IncludeCompletedWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetCancelledWOWithinGivenDaysAgainstFirstNameLastNameAndCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("IncludeCancelled","true");
        clone.getJSONObject("Filters").put("IncludeOpen","false");
        clone.getJSONObject("Filters").put("IncludeOpenedSinceDays","0");
        clone.getJSONObject("Filters").put("IncludeCancelledWithinDays",appEnv.getCompletedWithinDays());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenWOAgainstFirstNameAndLastName(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }

    public  String SetOpenWOAgainstFirstNameLastNameAndCustomerNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }
    public  String SetOpenWOAgainstFirstNameLastNameCustomerNumberAndStockNumber(){
        JSONObject clone = new JSONObject(jsonObject.toString());
        clone.getJSONObject("Filters").put("LastName",appEnv.getLastName());
        clone.getJSONObject("Filters").put("FirstName",appEnv.getFirstName());
        clone.getJSONObject("Filters").put("StockNo",appEnv.getStockNumber());
        clone.getJSONObject("Filters").put("CustomerNo",appEnv.getCustomerNumber());
        clone.put("Location",appEnv.getLocation());
        return clone.toString();
    }




}
