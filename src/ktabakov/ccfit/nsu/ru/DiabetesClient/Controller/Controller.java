package ktabakov.ccfit.nsu.ru.DiabetesClient.Controller;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.Model;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.TableModelRealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;

import javax.swing.table.TableModel;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Константин on 29.03.2015.
 */
public class Controller {

    private Model model = null;

    public Controller() {
        model = new Model();

    }

    public TableModel getTableModelForRealData(File pathToFile) {

        ArrayList<BGLevel> bgLevels = new ArrayList<BGLevel>();

        int numColumnBGlevel = model.getNumColumnBGlevel();
        int numColumnTime = model.getNumColumnTime();

        try {
            ResultSet resultSet = model.downloadDataFromFile(pathToFile);
            while (resultSet.next()) {
                bgLevels.add(new BGLevel(resultSet.getString(numColumnTime), resultSet.getInt(numColumnBGlevel)));
            }
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

        return new TableModelRealData(bgLevels);
    }
}
