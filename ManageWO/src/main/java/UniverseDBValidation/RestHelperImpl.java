package UniverseDBValidation;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class RestHelperImpl implements RestHelper {

    private HttpClient httpClient;
    private boolean log = false;
    private Dictionary<String, String > headers;

    public RestHelperImpl(Dictionary<String, String > headers){
        this.httpClient = HttpClients.createDefault();
        this.headers = headers;
    }

    @Override
    public String post(String base, String url, JSONObject data) throws IOException {
        HttpPost httpPost = new HttpPost(base+url);
        addHeaders(httpPost);

        if (log) {
            System.out.println("Request URL : " + base + url);
            System.out.println("Request Payload : " + data.toString());
        }
        HttpEntity entity = new StringEntity(data.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        return response(httpPost);
    }

    private String response(ClassicHttpRequest request) throws IOException {

        CloseableHttpResponse closeableHttpResponse = (CloseableHttpResponse) httpClient.execute(request);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((closeableHttpResponse.getEntity().getContent())));

        String response = bufferedReader.lines().collect(Collectors.joining());
        if (log) {
            System.out.println("Request Response : " + response);
            System.out.println("");
        }
        return response;
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
