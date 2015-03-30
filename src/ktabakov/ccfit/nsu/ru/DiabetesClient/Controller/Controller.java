package ktabakov.ccfit.nsu.ru.DiabetesClient.Controller;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.Model;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.TableModelRealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.RealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;
import org.json.simple.JSONObject;

import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Константин on 29.03.2015.
 */
public class Controller {

    private Model model = null;
    private ResultSet resultSetRealData = null;

    private String urlServer = "http://localhost:3000/test";


    public Controller() {
        model = new Model();

    }

    public TableModel getTableModelForRealData(File pathToFile) {

        ArrayList<BGLevel> bgLevels = new ArrayList<BGLevel>();

        int numColumnBGlevel = model.getNumColumnBGlevel();
        int numColumnTime = model.getNumColumnTime();

        try {
            resultSetRealData = model.downloadDataFromFile(pathToFile);
            while (resultSetRealData.next()) {
                bgLevels.add(new BGLevel(resultSetRealData.getString(numColumnTime), resultSetRealData.getInt(numColumnBGlevel)));
            }
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

        return new TableModelRealData(bgLevels);
    }

    public ResultSet getResultSetRealData() {
        return resultSetRealData;
    };

    public void sendJSONWithRealData() {
        RealData realData = new RealData(resultSetRealData);
        JSONObject jsonObjectRealData = model.createJSONdata(realData);
        try {
            model.sendJSONObject(jsonObjectRealData, urlServer);
        } catch (IOException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean checkDataRealDownload() {
        if(resultSetRealData == null) {
            return false;
        } else {
            return true;
        }
    }
}
