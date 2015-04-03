package ktabakov.ccfit.nsu.ru.DiabetesClient.Model.WorkWithJSON;

import org.json.simple.JSONObject;

/**
 * Created by Константин on 30.03.2015.
 */
public class LoginData implements DataToJSON {

    private String login = null;
    private String password = null;

    public LoginData(String login, char[] password) {
        this.login = login;
        this.password = new String(password);
        System.out.println("Создан объект авторизации " + login  + password);
    }

    @Override
    public JSONObject createJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", login);
        jsonObject.put("password", password);
        System.out.println("Создан json объект авторизации " + jsonObject);
        return jsonObject;
    }
}
