package ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Константин on 30.03.2015.
 */
public class RealData implements DataToJSON {

    ArrayList<BGLevel> bgLevels = null;

    public RealData(ArrayList<BGLevel> bgLevels) {
        this.bgLevels = bgLevels;
    }

    @Override
    public JSONObject createJSON() {

        JSONArray dateList = new JSONArray();
        JSONArray valueList = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        for (BGLevel tmp : bgLevels) {
            dateList.add(tmp.getTimeMeasurement());
            valueList.add(tmp.getValueMeasurement());
        }

        jsonObject.put("Date", dateList);
        jsonObject.put("Value", valueList);

        System.out.println("На сервер отправлен следующий обьект " + jsonObject);

        return jsonObject;
    }
}

