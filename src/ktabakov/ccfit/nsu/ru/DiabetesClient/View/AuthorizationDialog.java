package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class AuthorizationDialog extends JDialog {

    private static int lenPass = 12;
    private static int lenLogin = 12;
    private char [] password = null;
    private String login = null;

    public AuthorizationDialog() {
        createAuthorizationDialog();
    }

    private void createAuthorizationDialog() {
        JPanel panel = new JPanel();

        JLabel loginLabel = new JLabel("Логин");
        JLabel passLabel = new JLabel("Пароль");

        JTextField loginField = new JTextField(lenLogin);
        JPasswordField passwordField = new JPasswordField(lenPass);

        JCheckBox savePassCheckBox = new JCheckBox("Оставаться в системе");

        JButton loginButton = new JButton("Log In");
        JButton cancelButton = new JButton("Cancel");
        JButton vkLoginButton = new JButton("Vk");


        panel.add(loginLabel);
        panel.add(loginField);

        panel.add(passLabel);
        panel.add(passwordField);


        panel.add(savePassCheckBox);

        panel.add(vkLoginButton);
        panel.add(loginButton);
        panel.add(cancelButton);

        add(panel);

        setTitle("Авторизация");
        setSize(200, 200);
        setVisible(true);
    }

    private void setPass(char [] password) {
        this.password = password;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    public char [] getPass() {
        return password;

    }

    public String getLogin() {
        return login;

    }
}
