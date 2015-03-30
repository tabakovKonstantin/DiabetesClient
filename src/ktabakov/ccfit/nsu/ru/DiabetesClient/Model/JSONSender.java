package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

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

import java.io.IOException;

/**
 * Created by Константин on 30.03.2015.
 */
public class JSONSender {

    public String send(JSONObject jsonObject, String urlServer) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(urlServer);

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        String httpResponse = httpClient.execute(request, responseHandler);

        return httpResponse;
    }


    ResponseHandler<String> responseHandler = new ResponseHandler<String>() { //TODO переделать на тип который вернет сервер
        @Override
        public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) + status : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }
    };

}
