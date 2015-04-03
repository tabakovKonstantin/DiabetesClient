package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.DataToJSON;
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

    private String loginDB = "";
    private String passwordDB = "gAbriEl";
    private String urlDB = "jdbc:ucanaccess://";
    private String SQLQuery = "Select * from tblCGMDetail";
    private int numColumnBGlevel = 16;
    private int numColumnTime = 1;
    private JSONSender jsonSender = null;
    private CloseableHttpClient httpClient = null;

    public Model() {
        httpClient = HttpClientBuilder.create().build();
        jsonSender = new JSONSender(httpClient);
    }

    public ResultSet downloadDataFromFile(File pathToFile) throws SQLException {
        Connection connection = connectDataBase(pathToFile);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLQuery);
        return resultSet;
    }

    private Connection connectDataBase(File pathToFile) throws SQLException {
        Connection connection = DriverManager.getConnection(urlDB.concat(pathToFile.toString()), loginDB, passwordDB);
        System.out.print("pyt' k file" + urlDB.concat(pathToFile.getPath().toString()));
        return connection;
    }

    public int getNumColumnTime() {
        return numColumnTime;
    }

    public int getNumColumnBGlevel() {
        return numColumnBGlevel;
    }

    public JSONObject createJSONdata(DataToJSON dataToJSON) throws SQLException {

        return dataToJSON.createJSON();
    }

    public String sendJSONObject(JSONObject jsonObject, String urlServer) throws IOException {
        return jsonSender.send(jsonObject, urlServer);
    }


        public JSONObject parseJSON(String s) throws ParseException {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            return (JSONObject) obj;
        }




}
