package ktabakov.ccfit.nsu.ru.DiabetesClient.Controller;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.Model;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.TableModelRealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.RealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Константин on 29.03.2015.
 */
public class Controller {

    private Model model = null;
    private ArrayList<BGLevel> bgLevelsReal = null;
    private ArrayList<BGLevel> bgLevelsPredict = null;

    private JSONObject jsonObjectPredictData = null;

    private String urlServer = "http://localhost:3000/test";


    public Controller() {
        model = new Model();

    }

    public TableModel getTableModelForRealData(File pathToFile) {

        bgLevelsReal = new ArrayList<BGLevel>();

        int numColumnBGlevel = model.getNumColumnBGlevel();
        int numColumnTime = model.getNumColumnTime();

        try {
            ResultSet resultSetRealData = model.downloadDataFromFile(pathToFile);
            while (resultSetRealData.next()) {
                bgLevelsReal.add(new BGLevel(resultSetRealData.getString(numColumnTime), resultSetRealData.getInt(numColumnBGlevel)));
            }
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

        return new TableModelRealData(bgLevelsReal);
    }

    public TableModel getTableModelForPredictData() {

        bgLevelsPredict = new ArrayList<BGLevel>();

        JSONArray arrayValue = (JSONArray)jsonObjectPredictData.get("Value");
        JSONArray arrayDate = (JSONArray)jsonObjectPredictData.get("Date");

        for (int i = 0; i < arrayValue.size(); i++) {

            String date = (String) arrayDate.get(i);
            Long valueLong  = new Long((Long)arrayValue.get(i));
            int value = valueLong.intValue();
            bgLevelsPredict.add(new BGLevel(date, value));
        }

        return new TableModelRealData(bgLevelsPredict);
    }

    public void sendJSONWithRealData() {
        RealData realData = new RealData(bgLevelsReal);
        try {
            JSONObject jsonObjectRealData = model.createJSONdata(realData);
            jsonObjectPredictData = model.sendJSONObject(jsonObjectRealData, urlServer);
        } catch (IOException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean checkDataRealDownload() {
        if(bgLevelsReal == null) {
            return false;
        } else {
            return true;
        }
    }

}
