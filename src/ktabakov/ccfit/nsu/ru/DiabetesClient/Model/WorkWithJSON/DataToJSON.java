package ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON;

import org.json.simple.JSONObject;

import java.sql.SQLException;

/**
 * Created by Константин on 30.03.2015.
 */
public interface DataToJSON {
    public JSONObject createJSON() throws SQLException;

}
