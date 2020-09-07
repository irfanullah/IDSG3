package UniverseDBValidation;

import org.json.simple.JSONObject;

import java.io.IOException;

public interface RestHelper {
    String post(String base, String url, JSONObject data) throws IOException;
}
