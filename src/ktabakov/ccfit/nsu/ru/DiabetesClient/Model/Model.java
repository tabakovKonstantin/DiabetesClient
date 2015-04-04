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

    private Connection connectDataBase(File pathToFile) throws SQLException {
        String urlConnection = Property.URL_DB.concat(pathToFile.toString());
        Connection connection = DriverManager.getConnection(urlConnection, Property.LOGIN_DB, Property.PASSWORD_DB);
        System.out.print("pyt' k file" + Property.URL_DB.concat(pathToFile.getPath().toString()));
        return connection;
    }

    public ResultSet downloadDataFromFile(File pathToFile) throws SQLException {
        Connection connection = connectDataBase(pathToFile);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Property.SQL_QUERY);
        return resultSet;
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
