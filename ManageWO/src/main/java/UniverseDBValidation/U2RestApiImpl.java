package UniverseDBValidation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class U2RestApiImpl implements U2RestApi{

    private String baseUrl;
    private String authorization = "Token token=";

    private RestHelper restHelper;
    private JSONParser parser = new JSONParser();

    public U2RestApiImpl(String u2RestApiBaseUrl, String u2RestApiToken){

        this.baseUrl = u2RestApiBaseUrl;
        this.authorization = this.authorization + "\"" + u2RestApiToken + "\"";

        Dictionary<String, String> headers = new Hashtable<String, String>();
        headers.put("Authorization", authorization);
        headers.put("Content-Type","application/json");
        headers.put("Accept", "application/json");

        restHelper = new RestHelperImpl(headers);
    }

    @Override
    public String[] readRecord(int accountId, String location, String fileName, String pk) {
        String url = "v1/ReadRecord";

        JSONObject object = new JSONObject();
        object.put("AccountId", accountId);
        object.put("Location", location);
        object.put("Filename", fileName);
        object.put("PK", pk);

        return read(url, object);
    }

    @Override
    public String[] readFields(int accountId, String location, String fileName, String pk, List<Integer> fields) {
        String url = "v1/ReadFields";

        JSONObject object = new JSONObject();
        object.put("AccountId", accountId);
        object.put("Location", location);
        object.put("Filename", fileName);
        object.put("PK", pk);
        object.put("Fields", fields);

        return read(url, object);
    }

    private String[] read(String url, JSONObject object) {
        try {
            String response = restHelper.post(baseUrl, url, object);
            JSONArray array = (JSONArray) parser.parse(response);

            int size = array.size();
            String[] result = new String[size];

            for (int i = 0; i < size; i++) {
                result[i] = (String) array.get(i);
            }
            return result;
        }catch (Exception e){
            return new String[1];
        }
    }

}
