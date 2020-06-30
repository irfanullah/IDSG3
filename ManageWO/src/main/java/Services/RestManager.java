package Services;

import Drivers.Fetch_Elements;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class RestManager {

    private static AppEnv appEnv;
    private static General Utils = null;
    private Fetch_Elements fetch_elements;
    private static RestManager restManager = new RestManager();

    private RestManager(){
    }
    public static RestManager getInstance(AppEnv appEnv) {
        RestManager.appEnv = appEnv;
        Utils = General.getInstance(appEnv);
        return restManager;
    }


    public void SetDataFromAPI( String jsonObject){
        System.out.println(jsonObject);
                Response response =
                given()
                        .header("Authorization", "Bearer "+ appEnv.getToken())
                        .header("Cache-Control","no-cache")
                        .header("Content-Type","application/json")
                        .header("User-Agent","PostmanRuntime/7.25.0")
                        .header("Accept","*/*" )
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Connection","keep-alive")
                        .body(jsonObject)
                        .log().all()
                        .post("https://g3qa559.integrateddealersystems.com/IDSG3WorkOrderApi/v1/WorkOrders/Search")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();
                String APIResponse = response.body().asString();
                JSONObject jsonObj = new JSONObject(APIResponse);
                appEnv.setTotalOpenWOAgainstCustomerNumber(String.valueOf(jsonObj.get("ItemCount")));
                System.out.println(jsonObj.get("ItemCount"));

    }


}
