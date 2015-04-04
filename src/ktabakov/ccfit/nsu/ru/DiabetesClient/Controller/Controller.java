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
import java.sql.*;
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
        test();
//        insertDBAuthorizationData("login", new char[]{ 'a', 'b', 'c', 'd', 'e' }, false);
    }

    public TableModelData getTableModelForRealData(String pathToFile) {

        bgLevelsReal = new ArrayList<BGLevel>();

        try {
            Connection connection = model.connectDataBase(pathToFile, Property.LOGIN_DATA_DB, Property.PASSWORD_DATA_DB);
            ResultSet resultSetRealData = model.sqlQuerySelect(connection, Property.SQL_QUERY);
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

    private String createPathToUserDB() {
        String pathToFolderAplication = System.getenv("APPDATA").concat("/").concat(Property.APPLICATION_FOLDER_NAME);
        String pathToFileUserDB = pathToFolderAplication.concat("/").concat(Property.NAME_USER_DB_FILE);
        File folder = new File(pathToFolderAplication);
        if(!folder.exists()) {
            folder.mkdir();
        }
        return pathToFileUserDB;
    }

    private void test() {

        String pathToFileUserDB = createPathToUserDB();
        File file = new File(pathToFileUserDB);

        if( !file.exists() ) {
            try {
                String query = pathToFileUserDB.concat(";newdatabaseversion=V2010");
                Connection conn = model.connectDataBase(query, Property.LOGIN_USER_DB, Property.PASSWORD_USER_DB);
                model.sqlQueryUpdate(conn, Property.SQL_QUERY_CREATE_USER_TABLE);
                model.sqlQueryUpdate(conn, Property.SQL_QUERY_INSERT_USER_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Connection connection = model.connectDataBase(pathToFileUserDB, Property.LOGIN_USER_DB, Property.PASSWORD_USER_DB);
                ResultSet resultSet = model.sqlQuerySelect(connection, Property.SQL_QUERY_USER_TABLE_SELECT_ALL);

                String saveLogin = null;
                String savePassword = null;
                Boolean doLogin = null;

                while (resultSet.next()) {
                    saveLogin = resultSet.getString("login");
                    savePassword = resultSet.getString("password");
                    doLogin = resultSet.getBoolean("flag");
                }
                System.out.println("Что делать: " + doLogin + " logpass " + saveLogin + savePassword);
                if(doLogin) {
                    login(saveLogin, savePassword.toCharArray());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertDBAuthorizationData(String login, char[] password, boolean flag) {
        String urlDB = createPathToUserDB();
        try {

            Connection connection = model.connectDataBase(urlDB, Property.LOGIN_USER_DB, Property.PASSWORD_USER_DB);
//            String sqlQuery = "UPDATE myUser SET login = ".concat(login).concat(", password = ").concat(new String(password)).concat(", flag = ".concat(Boolean.toString(flag)));
            String sqlQuery = "UPDATE myUser SET login = '".concat(login).concat("', password = '").concat(new String(password)).concat("', flag = ").concat(Boolean.toString(flag));

            System.out.print(sqlQuery);
            model.sqlQueryUpdate(connection, sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFlag( boolean flag) {
        String urlDB = createPathToUserDB();
        try {

            Connection connection = model.connectDataBase(urlDB, Property.LOGIN_USER_DB, Property.PASSWORD_USER_DB);
            String sqlQuery = "UPDATE myUser SET  flag = ".concat(Boolean.toString(flag));

            System.out.print(sqlQuery);
            model.sqlQueryUpdate(connection, sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
