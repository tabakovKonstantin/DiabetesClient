package ktabakov.ccfit.nsu.ru.DiabetesClient.res;

/**
 * Created by Константин on 04.04.2015.
 */
public class Property {

    public static final String NAME_USER_DB_FILE = "diabetesUser.accdb";
    public static final String CREATE_DB_VER = ";newdatabaseversion=V2010";
    public static final String SQL_QUERY_CREATE_USER_TABLE = "CREATE TABLE myUser (ID COUNTER PRIMARY KEY, login TEXT(50), password TEXT(50), flag BOOLEAN)";
    public static final String SQL_QUERY_INSERT_USER_TABLE = "INSERT INTO myUser (ID, login, password, flag) VALUES (1, 'DefaultName', 'DefaultPass', false )";
    public static final String SQL_QUERY_USER_TABLE_SELECT_ALL = "Select * from myUser";
    public static final String PASSWORD_USER_DB = "";
    public static final String LOGIN_USER_DB = "";

    public static final String URL_DB = "jdbc:ucanaccess://";
    public static final String LOGIN_DATA_DB = "";
    public static final String PASSWORD_DATA_DB = "gAbriEl";
    public static final String SQL_QUERY = "Select * from tblCGMDetail";
    public static final String NAME_BGLEVEL_COLUMN = "BGLevel";
    public static final String NAME_DATE_COLUMN = "Select * from tblCGMDetail";

    public static final String APPLICATION_FOLDER_NAME = "DiabetesClient";



    public static final String URL_EVALUATE = "http://localhost:3000/evaluate";
    public static final String URL_LOGIN = "http://localhost:3000/login";
    public static final String URL_LOGOUT = "http://localhost:3000/logout";
    public static final String URL_REGISTER = "http://localhost:3000/register";



}
