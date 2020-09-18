package UniverseDBValidation;

import java.util.HashMap;

public interface RestHelper {
    String post(String base, String url, HashMap<String, Object> data);
}
