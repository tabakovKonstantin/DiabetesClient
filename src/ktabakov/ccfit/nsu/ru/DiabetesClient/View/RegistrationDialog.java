package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class RegistrationDialog extends JDialog {

    private int lenLogin = 12;
    private int lenPass = 12;
    public RegistrationDialog() {
        createRegistrationDialog();
    }

    private void createRegistrationDialog() {
        JPanel panel = new JPanel();

        JLabel loginLabel = new JLabel("Логин");
        JLabel passLabel = new JLabel("Пароль");
        JLabel confirmpassLabel = new JLabel("Введите пароль еще раз");

        JTextField loginField = new JTextField(lenLogin);
        JPasswordField passwordField = new JPasswordField(lenPass);
        JPasswordField confirmPasswordField = new JPasswordField(lenPass);

        JButton okButton = new JButton("Ок");
        JButton cancelButton = new JButton("Cancel");

        panel.add(loginLabel);
        panel.add(loginField);

        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(confirmpassLabel);
        panel.add(confirmPasswordField);

        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);

        setTitle("Регистрация");
        setSize(200, 200);
        setVisible(true);
    }


}
