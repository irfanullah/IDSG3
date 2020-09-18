package UniverseDBValidation;

import com.google.gson.Gson;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class U2RestApiImpl implements U2RestApi{

    private final String baseUrl;
    private final RestHelper restHelper;
    private final Gson gson;

    public U2RestApiImpl(String u2RestApiBaseUrl, String u2RestApiToken){

        this.baseUrl = u2RestApiBaseUrl;

        Dictionary<String, String> headers = new Hashtable<>();
        headers.put("Authorization", "Token token=" + "\"" + u2RestApiToken + "\"");
        headers.put("Content-Type","application/json");
        headers.put("Accept", "application/json");

        restHelper = new RestHelperImpl(headers);
        gson = new Gson();
    }

    @Override
    public String[] readRecord(int accountId, String location, String fileName, String pk) {
        String url = "v1/ReadRecord";

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("AccountId", accountId);
        hashMap.put("Location", location);
        hashMap.put("Filename", fileName);
        hashMap.put("PK", pk);

        return read(url, hashMap);
    }

    @Override
    public String[] readFields(int accountId, String location, String fileName, String pk, List<Integer> fields) {
        String url = "v1/ReadFields";

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("AccountId", accountId);
        hashMap.put("Location", location);
        hashMap.put("Filename", fileName);
        hashMap.put("PK", pk);
        hashMap.put("Fields", fields);

        return read(url, hashMap);
    }

    @Override
    public String[] callSubroutine(int accountId, String location, List<String> parameters, String subToCall) {
        String url = "v1/CallSubroutine";

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("AccountId", accountId);
        hashMap.put("Location", location);
        hashMap.put("Parameters", parameters);
        hashMap.put("SubToCall", subToCall);

        return read(url, hashMap);
    }

    private String[] read(String url, HashMap<String, Object> hashMap) {
        try {
            String response = restHelper.post(baseUrl, url, hashMap);
            return gson.fromJson(response, String[].class);
        }catch (Exception e){
            return null;
        }
    }

}
