package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import java.io.File;
import java.sql.*;

/**
 * Created by Константин on 29.03.2015.
 */
public class Model {

    private String loginDB = "";
    private String passwordDB = "gAbriEl";
    private String urlDB = "jdbc:ucanaccess://";
    private String SQLQuery = "Select * from tblCGMDetail WHERE BGLevel < 45";
    private int numColumnBGlevel = 16;
    private int numColumnTime = 1;

    public Model() {

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
}
