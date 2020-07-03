package TestManager;

import Services.AppEnv;
import Services.General;

public class InputDataStream {

    private static InputDataStream inputDataStream = new InputDataStream();
    private static AppEnv appEnv = new AppEnv();
    private static General Utils = null;

    private InputDataStream() {
    }

    /* Static 'instance' method */
    public static InputDataStream getInstance(AppEnv appEnv) {
        InputDataStream.appEnv = appEnv;
        Utils = General.getInstance(appEnv);
        return inputDataStream;
    }

    public  String SetOpenWOAgainstStockNo(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \""+appEnv.getStockNumber()+"\",\n" +
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
                "}";
                return jsonObj;
    }

    public  String SetOpenWOAgainstCustomerNo(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"" + appEnv.getCustomerNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetOpenWOAgainstFirstName(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"\",\n" +
                "    \"FirstName\": \"" + appEnv.getFirstName() + "\",\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetOpenWOAgainstLastName(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \"\",\n" +
                "    \"PhoneNo\": \"\",\n" +
                "    \"LastName\": \"" + appEnv.getLastName() + "\"\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetOpenWOAgainstPhoneNumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \"\",\n" +
                "    \"PhoneNo\": \"" + appEnv.getPhoneNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetOpenWOAgainstWONumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetOpenWOAgainstWONumberAndStockNumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": true,\n" +
                "    \"IncludeCompleted\": false,\n" +
                "    \"IncludeCompletedWithinDays\": 0,\n" +
                "    \"CustomerNo\": \"" + appEnv.getCustomerNumber() + "\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \"" + appEnv.getStockNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }

    public  String SetCompletedWOWithinGivenDaysAgainstCustomerNumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": false,\n" +
                "    \"IncludeCompleted\": true,\n" +
                "    \"IncludeCompletedWithinDays\": "+appEnv.getCompletedWithinDays()+",\n" +
                "    \"CustomerNo\": \"" + appEnv.getCustomerNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }
    public  String SetCompletedWOWithinGivenDaysAgainstStockNumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": false,\n" +
                "    \"IncludeCompleted\": true,\n" +
                "    \"IncludeCompletedWithinDays\": "+appEnv.getCompletedWithinDays()+",\n" +
                "    \"CustomerNo\": \"\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \"" + appEnv.getStockNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }
    public  String SetCompletedWOWithinGivenDaysAgainstCustomerAndStockNumber(){
        String jsonObj ="{\n" +
                "  \"Location\": \""+appEnv.getLocation()+"\",\n" +
                "  \"Filters\": {\n" +
                "    \"WorkOrderNo\": \"\",\n" +
                "    \"IncludeOpen\": false,\n" +
                "    \"IncludeCompleted\": true,\n" +
                "    \"IncludeCompletedWithinDays\": "+appEnv.getCompletedWithinDays()+",\n" +
                "    \"CustomerNo\": \"" + appEnv.getCustomerNumber() + "\",\n" +
                "    \"FirstName\": \"\",\n" +
                "    \"StockNo\": \"" + appEnv.getStockNumber() + "\",\n" +
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
                "}";
        return jsonObj;
    }


}
