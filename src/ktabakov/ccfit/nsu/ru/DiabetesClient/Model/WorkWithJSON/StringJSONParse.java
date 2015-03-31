package ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Константин on 30.03.2015.
 */
public class StringJSONParse {
    public JSONObject parse(String s) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(s);
        return (JSONObject) obj;
    }
}
