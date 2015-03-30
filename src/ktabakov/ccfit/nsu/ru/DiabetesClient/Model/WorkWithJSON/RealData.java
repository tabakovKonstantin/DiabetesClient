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

    private ResultSet resultSetRealData = null;
    ArrayList<BGLevel> bgLevels = null;

    public RealData(ResultSet resultSetRealData, ArrayList<BGLevel> bgLevels) {
        this.resultSetRealData = resultSetRealData;
        this.bgLevels = bgLevels;
    }

    @Override
    public JSONObject createJSON() throws SQLException {

        JSONArray dateList = new JSONArray();
        JSONArray valueList = new JSONArray();

        JSONObject jsonObject = new JSONObject();
//        resultSetRealData.beforeFirst();
//        System.out.println("asdfgh" + resultSetRealData.isFirst());
//
//        while (resultSetRealData.next()){
//            dateList.add(resultSetRealData.getInt(1));
//            System.out.println("asdfgh" + resultSetRealData.isFirst());
//            dateList.add(resultSetRealData.getInt(16));
//        }

        for (BGLevel tmp : bgLevels) {
            dateList.add(tmp.getTimeMeasurement());
            valueList.add(tmp.getValueMeasurement());
        }

        jsonObject.put("Date", dateList);
        jsonObject.put("Value", valueList);

        System.out.println(jsonObject);

        return jsonObject;
    }
}

