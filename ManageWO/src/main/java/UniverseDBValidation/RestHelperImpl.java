package UniverseDBValidation;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RestHelperImpl implements RestHelper {

    private final HttpClient httpClient;
    private final Dictionary<String, String > headers;
    private final Gson gson;
    private final Logger logger;

    public RestHelperImpl(Dictionary<String, String > headers){
        this.httpClient = HttpClients.createDefault();
        this.headers = headers;
        gson = new Gson();
        logger = LoggerFactory.getLogger(RestHelperImpl.class);
    }

    @Override
    public String post(String base, String url, HashMap<String, Object> data) {
        HttpPost httpPost = new HttpPost(base+url);
        addHeaders(httpPost);

        logger.info("Request URL : " + base + url);
        logger.info("Request Payload : " + gson.toJson(data));

        HttpEntity entity = new StringEntity(gson.toJson(data), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        return response(httpPost,0);
    }

    private String response(ClassicHttpRequest request, int retry) {

        try {
            CloseableHttpResponse closeableHttpResponse = (CloseableHttpResponse) httpClient.execute(request);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader((closeableHttpResponse.getEntity().getContent())));

            String response = bufferedReader.lines().collect(Collectors.joining());

            logger.info("Request Response : " + response);

            return response;
        } catch (IOException e){
            logger.error("Request retry: "+retry, e);
            if(retry <= 5)
                return response(request, retry+1);
            return null;
        }
    }

    private void addHeaders(HttpUriRequestBase requestBase) {
        Enumeration<String> keys = headers.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = headers.get(key);
            requestBase.addHeader(key, value);
        }
    }

}
