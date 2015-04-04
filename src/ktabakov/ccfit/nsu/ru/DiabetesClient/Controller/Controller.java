package ktabakov.ccfit.nsu.ru.DiabetesClient.Controller;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.Model;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.TableModelData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.LoginData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.RealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Property;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Константин on 29.03.2015.
 */
public class Controller {

    private Model model = null;
    private JSONObject jsonObjectPredictData = null;

    private ArrayList<BGLevel> bgLevelsReal = null;
    private ArrayList<BGLevel> bgLevelsPredict = null;

    private boolean authorization = false;

    public Controller() {
        model = new Model();
    }

    public TableModelData getTableModelForRealData(File pathToFile) {

        bgLevelsReal = new ArrayList<BGLevel>();

        try {
            ResultSet resultSetRealData = model.downloadDataFromFile(pathToFile);
            while (resultSetRealData.next()) {
                bgLevelsReal.add(new BGLevel(resultSetRealData.getString(1), resultSetRealData.getInt(Property.NAME_BGLEVEL_COLUMN))); //TODO: сделать дату на проперти а не на инт
            }
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        }

        return new TableModelData(bgLevelsReal);
    }

    public TableModelData getTableModelForPredictData() {

        bgLevelsPredict = new ArrayList<BGLevel>();

        JSONArray arrayValue = (JSONArray)jsonObjectPredictData.get("Value");
        JSONArray arrayDate = (JSONArray)jsonObjectPredictData.get("Date");

        for (int i = 0; i < arrayValue.size(); i++) {

            String date = (String) arrayDate.get(i);
            Long valueLong  = new Long((Long)arrayValue.get(i));
            int value = valueLong.intValue();
            bgLevelsPredict.add(new BGLevel(date, value));
        }

        return new TableModelData(bgLevelsPredict);
    }

    public void sendJSONWithRealData() {
        RealData realData = new RealData(bgLevelsReal);
        try {
            JSONObject jsonObjectRealData = model.createJSON(realData);
            String response =  model.sendJSON(jsonObjectRealData, Property.URL_EVALUATE);
            jsonObjectPredictData = model.parseJSON(response);
        } catch (IOException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void saveAuthorizateDate(String login, char[] password) {

    }

    private void login(String login, char[] password) throws SQLException, IOException {
        LoginData loginData =  new LoginData(login, password);
        JSONObject jsonObjectLoginData = model.createJSON(loginData);
        System.out.println("Сервер ответил на авторизацию " + model.sendJSON(jsonObjectLoginData, Property.URL_LOGIN));

    }

    public int logout() {
        try {
            System.out.println("Сервер ответил на деавторизацию " + model.sendJSON(new JSONObject(), Property.URL_LOGOUT));
            authorization = false;
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int authorization(String login, char[] password, boolean saveAuthorizateDate) {
        if(!authorization) {
            try {
                login(login, password);
                authorization = true;
                saveAuthorizateDate(login, password);
                return 0;
            } catch (SQLException e) {
                new ErrorDialog().showErrorDialog(e.getMessage());
                e.printStackTrace();
                return 1;
            } catch (IOException e) {
                new ErrorDialog().showErrorDialog(e.getMessage());
                e.printStackTrace();
                return 1;
            }
        } else {
            return 0;
        }


    }

    public int registred(String login, char[] password, char[] retryPassword) {
        if(!Arrays.equals(password, retryPassword)) {
            new ErrorDialog().showErrorDialog(Strings.ERROR_MASSAGE_PASSWORD_EQVALS);
            return 2;
        } else {
            LoginData loginData =  new LoginData(login, password);
            try {
                JSONObject jsonObjectLoginData = model.createJSON(loginData);
                System.out.println("Сервер ответил на регистрацию " + model.sendJSON(jsonObjectLoginData, Property.URL_REGISTER));
                authorization = true;
                return 0;
            } catch (SQLException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
            return 1;
        } catch (IOException e) {
            new ErrorDialog().showErrorDialog(e.getMessage());
            e.printStackTrace();
            return 1;
        }

        }

    }
}
