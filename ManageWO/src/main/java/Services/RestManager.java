package Services;

import Drivers.Fetch_Elements;
import io.restassured.response.Response;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
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
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        String dateTime = jsonObj.getJSONObject(ParentNode).getString(DateName);
        // Format for input
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        // Parsing the date
        DateTime jodatime = dtf.parseDateTime(dateTime);
        // Parsing the date

        DateTimeFormatter dtfOutDate = DateTimeFormat.forPattern("dd MMM yy");
        return (dtfOutDate.print(jodatime));

    }


    public String GetDateTimeFromWOAPI(String ParentNode, String Date, String Time){
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
        System.out.println(APIResponse);
        JSONObject jsonObj = new JSONObject(APIResponse);
        String APIDate = jsonObj.getJSONObject(ParentNode).getString(Date);
        String APITIme = jsonObj.getJSONObject(ParentNode).getString(Time);
        // Format for input
      //  DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter dateformatter  = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
        // Parsing the date
        DateTime jodadate = dateformatter.parseDateTime(APIDate);
        DateTime jodatime = timeFormatter.parseDateTime(APITIme);
        // Parsing the date

        DateTimeFormatter dtfOutDate = DateTimeFormat.forPattern("dd MMM yy");
        DateTimeFormatter dtfOutTime = DateTimeFormat.forPattern("hh:mma");
        String FormatedDate = (dtfOutDate.print(jodadate));
        String FormatedTime = (dtfOutTime.print(jodatime));
        System.out.println("API Time is : " + FormatedTime);
        System.out.println("API Date is : "+ FormatedDate);
      /*  if(FormatedTime.equalsIgnoreCase( "00:00") && FormatedDate.equalsIgnoreCase( "00:00" ))
            return Date;
        else {
            DateTimeFormatter dtfOutDateTime = DateTimeFormat.forPattern("dd MMM yy hh:mma");
            System.out.println("API Date Time is : " +  (dtfOutDateTime.print(jodatime)));
            return (dtfOutDateTime.print(jodatime));
        }*/
      return FormatedDate +" "+ FormatedTime;
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




   public JSONArray GetInfoFromAPI(String Title){
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
                    //   .log().all()
                       .get(""+appEnv.getAPIBaseURL()+"/Service/v1/WorkOrder/"+appEnv.getLocation()+"/" +appEnv.getWorkOrderNumber()+ "?invalidateCache=false")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
                        String APIResponse = response.body().asString();
                        System.out.println(APIResponse);
                        JSONObject jsonObj = new JSONObject(APIResponse);
                        JSONArray jsonArray = jsonObj.getJSONArray(Title);
                        return jsonArray;


/*

                     JSONArray myArray = jsonObj.getJSONArray(Title);
                     for(how to iterate throught jason array)
                         JSONObject element = <elemn in each iterantion>
                        if (elemtn.get(KEy).equals(value)) {
                            return element.get(KEy);
                        }
*/
    }
}
