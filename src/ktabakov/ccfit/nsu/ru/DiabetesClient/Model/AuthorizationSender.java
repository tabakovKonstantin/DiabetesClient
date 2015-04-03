package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by Константин on 03.04.2015.
 */
public class AuthorizationSender {

    private CloseableHttpClient httpClient = null;

    public AuthorizationSender(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void send() {

    }
}
