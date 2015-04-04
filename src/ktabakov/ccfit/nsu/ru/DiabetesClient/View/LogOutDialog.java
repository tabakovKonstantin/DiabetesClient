package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 03.04.2015.
 */
public class LogOutDialog extends JDialog {

    private JButton yesButton;
    private JButton noButton;
    private JTextField jTextField1;

    private Controller controller = null;
    private MenuBar menuBar = null;

    public LogOutDialog(Controller controller, MenuBar menuBar){
        this.controller = controller;
        this.menuBar = menuBar;
        initComponents();
        setActionLIstener();
        pack();
        setTitle(Strings.NAME_LOGOUT_DIALOG);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {

        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        yesButton.setText(Strings.YES_BUTTON_NAME);

        noButton.setText(Strings.NO_BUTTON_NAME);

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(Strings.TEXT_LOGOUT_DIALOG);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(yesButton)
                                        .addComponent(noButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void setActionLIstener() {
        JDialog dialog = this;
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = controller.logout();
                if(1 == status) {
                    new ErrorDialog().showErrorDialog(Strings.ERROR_MASSAGE_LOGOUT_ERROR);
                    dialog.dispose();

                } else {
                    menuBar.getjMenuItemLogOut().setEnabled(false);
                    menuBar.getjMenuItemLogIn().setEnabled(true);
                    dialog.dispose();
                }
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }
}
