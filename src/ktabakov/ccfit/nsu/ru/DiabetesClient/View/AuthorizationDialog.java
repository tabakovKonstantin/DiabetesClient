package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 13.03.2015.
 */
public class AuthorizationDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JCheckBox saveAuthorizateDataCheckBox;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField loginTextField;

    private Controller controller = null;
    private MenuBar menuBar = null;

   public AuthorizationDialog(Controller controller, MenuBar menuBar) {
       this.controller = controller;
       this.menuBar = menuBar;
       initComponents();
       setActionLIstener();
       pack();
       setTitle(Strings.NAME_AUTHORIZATION_DIALOG);
       setResizable(false);
       setVisible(true);

    }

    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginTextField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        saveAuthorizateDataCheckBox = new javax.swing.JCheckBox();
        cancelButton = new javax.swing.JButton();


        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText(Strings.AUTHORIZATION_LOGIN_LABEL);

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText(Strings.AUTHORIZATION_PASSWORD_LABEL);


        okButton.setText(Strings.OK_BUTTON_NAME);

        saveAuthorizateDataCheckBox.setText(Strings.AUTHORIZATION_SAVE_DATA_CHECKBOX);

        cancelButton.setText(Strings.CANCEL_BUTTON_NAME);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordField)
                                                        .addComponent(loginTextField)
                                                        .addComponent(saveAuthorizateDataCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginLabel)
                                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(saveAuthorizateDataCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
    }

    private void setActionLIstener() {
        JDialog dialog  = this;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginTextField.getText();
                char[] password = passwordField.getPassword();
                boolean saveAuthorizateDate = saveAuthorizateDataCheckBox.isSelected();

                int status = controller.authorization(login, password, saveAuthorizateDate);

                if(1 == status) {
                    loginTextField.setText("");
                    passwordField.setText("");
                } else {
                    menuBar.getjMenuItemLogOut().setEnabled(true);
                    menuBar.getjMenuItemLogIn().setEnabled(false);
                    dialog.dispose();
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
