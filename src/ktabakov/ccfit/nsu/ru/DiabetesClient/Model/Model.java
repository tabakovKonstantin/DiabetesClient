package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.DataToJSON;
import org.json.simple.JSONObject;

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

    public Model() {
        jsonSender = new JSONSender();
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


    /*метод принимает */
    public JSONObject createJSONdata(DataToJSON dataToJSON) {

        return dataToJSON.createJSON();
    }

    public void sendJSONObject(JSONObject jsonObject, String urlServer) throws IOException {
        jsonSender.send(jsonObject, urlServer);
    }

}
