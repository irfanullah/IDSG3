package Services;

import Drivers.Fetch_Elements;
import UniverseDBValidation.response.WorkOrderResponse;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class RestManager {

    private static AppEnv appEnv;
    private static General Utils = null;
    private Fetch_Elements fetch_elements;
    private static RestManager restManager = new RestManager();
    //   private  String WOEndPointURL = ""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false";
    //  private  String WOSearchEndPointURL = ""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrders/Search"+"";
    //  private  String LoginEndPointURL = ""+appEnv.getAPIBaseURL()+"/v1/Login";


    private RestManager(){
    }
    public static RestManager getInstance(AppEnv appEnv) {
        RestManager.appEnv = appEnv;
        Utils = General.getInstance(appEnv);
        return restManager;
    }


    public int SetDataFromAPI(String jsonObject){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .body(jsonObject)
                        .log().all()
                        .post(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrders/Search"+"")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        String APIResponse = response.body().asString();
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        return jsonObj.getInt("ItemCount");

    }


    public int GetStatusCode(){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false")
                        .then()
                        .extract()
                        .response();
        return response.statusCode();

    }



    public String GetDateFromWOAPI(String ParentNode, String DateName){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .log().all()
                        .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        String APIResponse = response.body().asString();

        Gson gson = new Gson();
        WorkOrderResponse workOrder = gson.fromJson(APIResponse, WorkOrderResponse.class);

        // workOrder.WorkOrder.WorkOrderDate.format();

        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        String dateTime = jsonObj.getJSONObject(ParentNode).getString(DateName);
        // Format for input
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        // Parsing the date
        DateTime jodatime = dtf.parseDateTime(dateTime);
        // Parsing the date

        DateTimeFormatter dtfOutDate = DateTimeFormat.forPattern("dd MMM yy");
        DateTimeFormatter dtfOutTime = DateTimeFormat.forPattern("HH:mm");
        String Date = (dtfOutDate.print(jodatime));
        String Time = (dtfOutTime.print(jodatime));
        System.out.println("API Time is : " + Time);
        System.out.println("API Date is : "+ Date);
        if(Time.equalsIgnoreCase( "00:00"))
            return Date;
        else {
            DateTimeFormatter dtfOutDateTime = DateTimeFormat.forPattern("dd MMM yy hh:mma");
            System.out.println("API Date Time is : " +  (dtfOutDateTime.print(jodatime)));
            return (dtfOutDateTime.print(jodatime));
        }
    }

    public String GetAccessToken(){
        String loginbody = "{\n" +
                "  \"UserId\": \""+appEnv.getUserID()+"\",\n" +
                "  \"Pd\": \""+appEnv.getEncryptedPassword()+"\"\n" +
                "}";
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .header("Origin","http://myurl.com")
                        .body(loginbody)
                        .post(""+appEnv.getAPIBaseURL()+"/v1/Login")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        String APIResponse = response.body().asString();
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        return  jsonObj.getString("AccessToken");

    }
    public String GetStringInfoFromWOAPI(String ParentNode, String Title){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")

                        .log().all()
                        // .post(""+appEnv.getAPIAddress()+"")
                        .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        String APIResponse = response.body().asString();
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        return jsonObj.getJSONObject(ParentNode).getString(Title);

    }

    public int GetIntInfoFromWOAPI(String ParentNode, String Title){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")

                        .log().all()
                        // .post(""+appEnv.getAPIAddress()+"")
                        .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        String APIResponse = response.body().asString();
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        return jsonObj.getJSONObject(ParentNode).getInt(Title);

    }




    public G3WOResponse GetWOInfoFromAPI(){
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.26.3")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .log().all()
                        .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=true")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
        String APIResponse = response.body().asString();
        System.out.println(APIResponse);
        Gson gson = new Gson();
        return gson.fromJson(APIResponse,G3WOResponse.class);


    }
}
