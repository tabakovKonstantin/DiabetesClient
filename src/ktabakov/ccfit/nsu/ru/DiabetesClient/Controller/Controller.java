package ktabakov.ccfit.nsu.ru.DiabetesClient.Controller;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.BGLevel;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.Model;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.TableModelRealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.LoginData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON.RealData;
import ktabakov.ccfit.nsu.ru.DiabetesClient.View.ErrorDialog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Константин on 29.03.2015.
 */
public class Controller {

    private Model model = null;
    private ArrayList<BGLevel> bgLevelsReal = null;
    private ArrayList<BGLevel> bgLevelsPredict = null;

    private JSONObject jsonObjectPredictData = null;

    private boolean authorization = false;

    private String urlServer = "http://localhost:3000/evaluate";
    String urlLogin = "http://localhost:3000/login";
    String urlLogout = "http://localhost:3000/logout";
    String urlRegister = "http://localhost:3000/register";



    public Controller() {
        model = new Model();
        System.out.println(System.getProperty("java.io.tmpdir"));

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
            String response =  model.sendJSONObject(jsonObjectRealData, urlServer);
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

    public boolean checkDataRealDownload() {
        if(bgLevelsReal == null) {
            return false;
        } else {
            return true;
        }
    }

    private void saveAuthorizateDate(String login, char[] password) {

    }

    private void login(String login, char[] password) throws SQLException, IOException {
        LoginData loginData =  new LoginData(login, password);
        JSONObject jsonObjectLoginData = model.createJSONdata(loginData);
        System.out.println("Сервер ответил на авторизацию " + model.sendJSONObject(jsonObjectLoginData, urlLogin ));

    }

    public int logout() {
        try {
            System.out.println("Сервер ответил на деавторизацию " + model.sendJSONObject(new JSONObject(), urlLogout ));
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
            new ErrorDialog().showErrorDialog("Пароли не совпадают");
            return 2;
        } else {
            LoginData loginData =  new LoginData(login, password);
            try {
                JSONObject jsonObjectLoginData = model.createJSONdata(loginData);
                System.out.println("Сервер ответил на регистрацию " + model.sendJSONObject(jsonObjectLoginData, urlRegister ));
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
