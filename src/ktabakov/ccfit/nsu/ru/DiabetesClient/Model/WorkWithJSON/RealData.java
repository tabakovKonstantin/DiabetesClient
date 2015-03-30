package ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON;

import org.json.simple.JSONObject;

import java.sql.ResultSet;

/**
 * Created by Константин on 30.03.2015.
 */
public class RealData implements DataToJSON {

    private ResultSet resultSetRealData = null;

    public RealData(ResultSet resultSetRealData) {
        this.resultSetRealData =resultSetRealData;
    }

    @Override
    public JSONObject createJSON() {
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "foo");
        obj1.put("num", new Integer(100));
        obj1.put("balance", new Double(1000.21));

        JSONObject json = new JSONObject();
        json.put("is_vip", new Boolean(true));
        json.put("nickname", null);
        json.putAll(obj1);
        return json;
    }
}

