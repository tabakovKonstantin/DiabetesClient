package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.DataToJSON;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Property;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Константин on 29.03.2015.
 */
public class Model {

    private JSONSender jsonSender = null;
    private CloseableHttpClient httpClient = null;

    public Model() {
        httpClient = HttpClientBuilder.create().build();
        jsonSender = new JSONSender(httpClient);
    }

    public Connection connectDataBase(String pathToFile, String login, String password) throws SQLException {
        String urlConnection = Property.URL_DB.concat(pathToFile);
        Connection connection = DriverManager.getConnection(urlConnection, login, password);
        System.out.println("URL: " + urlConnection);
        return connection;
    }

    public ResultSet sqlQuerySelect(Connection connection, String query) throws SQLException {
        System.out.println("ЗАпрос " + query);
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public boolean sqlQueryUpdate(Connection connection, String query) throws SQLException {
        System.out.println("ЗАпрос " +query);
        Statement statement = connection.createStatement();
        return statement.execute(query);
    }

    public JSONObject createJSON(DataToJSON dataToJSON) throws SQLException {
        return dataToJSON.createJSON();
    }

    public String sendJSON(JSONObject jsonObject, String urlServer) throws IOException {
        return jsonSender.send(jsonObject, urlServer);
    }

    public JSONObject parseJSON(String s) throws ParseException {
         JSONParser parser = new JSONParser();
         Object obj = parser.parse(s);
         return (JSONObject) obj;
    }
}
