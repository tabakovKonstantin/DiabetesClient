package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 13.03.2015.
 */
public class RegistrationDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel retryPasswordLabel;
    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JPasswordField retryPasswordField;

    private Controller controller = null;
    private MenuBar menuBar = null;

    public RegistrationDialog(Controller controller, MenuBar menuBar) {

        this.controller = controller;
        this.menuBar = menuBar;

        initComponents();
        setActionLIstener();
        pack();
        setTitle(Strings.NAME_REGISTRATION_DIALOG);
        setResizable(false);
        setVisible(true);

    }

    private void initComponents() {

            okButton = new JButton();
            cancelButton = new JButton();
            loginTextField = new JTextField(10);
            passwordField = new JPasswordField();
            retryPasswordField = new JPasswordField();
            loginLabel = new JLabel();
            passwordLabel = new JLabel();
            retryPasswordLabel = new JLabel();

            okButton.setText(Strings.OK_BUTTON_NAME);
            cancelButton.setText(Strings.CANCEL_BUTTON_NAME);

            loginLabel.setHorizontalAlignment(SwingConstants.LEFT);
            loginLabel.setText(Strings.AUTHORIZATION_LOGIN_LABEL);

            passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
            passwordLabel.setText(Strings.AUTHORIZATION_PASSWORD_LABEL);

            retryPasswordLabel.setText(Strings.AUTHORIZATION_RETRYPASSWORD_LABEL);

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(retryPasswordLabel)
                                                    .addComponent(loginLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(loginTextField)
                                                    .addComponent(passwordField)
                                                    .addComponent(retryPasswordField))
                                            .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                    .addGap(39, 39, 39))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(14, 14, 14)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(passwordLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(retryPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addComponent(retryPasswordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(cancelButton)
                                            .addComponent(okButton))
                                    .addContainerGap(33, Short.MAX_VALUE))
            );

    }

    private void setActionLIstener() {
        JDialog dialog = this;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                char[] password = passwordField.getPassword();
                char[] retryPassword = retryPasswordField.getPassword();
                int status = controller.registred(login, password, retryPassword);
                switch (status){
                    case 0:
                        menuBar.getjMenuItemLogIn().setEnabled(false);
                        menuBar.getjMenuItemLogOut().setEnabled(true);
                        dialog.dispose();
                        break;
                    case 1:
                        loginTextField.setText("");
                        break;
                    case 2:
                        passwordField.setText("");
                        retryPasswordField.setText("");
                        break;
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });


    }


}
