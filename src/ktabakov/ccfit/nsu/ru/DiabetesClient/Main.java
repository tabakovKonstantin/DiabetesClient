package ktabakov.ccfit.nsu.ru.DiabetesClient;

import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;
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
import java.sql.*;

/**
 * Created by ?????????? on 13.03.2015.
 */
public class Main {
    public static void main(String[] args) {
//        View view = new View();
/***************************************************/
//        JSONObject json = new JSONObject();
//        json.put("someKey", "someValue");

        JSONObject obj1 = new JSONObject();
        obj1.put("name", "foo");
        obj1.put("num", new Integer(100));
        obj1.put("balance", new Double(1000.21));

        JSONObject json = new JSONObject();
        json.put("is_vip", new Boolean(true));
        json.put("nickname", null);
        json.putAll(obj1);
/********************************************************/
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //HttpResponse response = httpClient.execute(new HttpGet("http://localhost:3000/test"));
//        String responseString = new BasicResponseHandler().handleResponse(response);
//        System.out.println(responseString);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) + status : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };

        try {
            HttpPost request = new HttpPost("http://localhost:3000/test");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            ErrorDialog errorDialog = new ErrorDialog();
            //errorDialog.showErrorDialog("start");
            //errorDialog.showErrorDialog("start");
            System.out.print(httpClient.execute(request, responseHandler));
            //errorDialog.showErrorDialog("finish");
//            HttpResponse response = httpClient.execute(request);
//            String responseString = new BasicResponseHandler().handleResponse(response);
//            System.out.println(responseString);
            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
//            httpClient.close();
        }
/**********************************************************************/
        Connection conn = null;
        Statement st = null;
        try {
             conn = DriverManager.getConnection("jdbc:ucanaccess://c:/test2.mmg","" ,"gAbriEl");
            System.out.print("все ок я тут");
        } catch (SQLException e) {
            System.out.print("я тут");
            e.printStackTrace();
        }
        /*try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "Select * from valueGlucose";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()){ System.out.println("\n"+rs.getString(2));

        }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }}
