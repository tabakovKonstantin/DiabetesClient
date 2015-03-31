package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.StringJSONParse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by Константин on 30.03.2015.
 */
public class JSONSender {

    public JSONObject send(JSONObject jsonObject, String urlServer) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(urlServer);

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        JSONObject httpResponse = httpClient.execute(request, responseHandler);

        return httpResponse;
    }


    ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {
        @Override
        public JSONObject handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                String stringResponse = EntityUtils.toString(entity);

                try {
                    return new StringJSONParse().parse(stringResponse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            return null;
        }
    };

}
