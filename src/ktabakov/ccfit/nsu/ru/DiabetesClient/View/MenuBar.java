package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 13.03.2015.
 */
public class MenuBar extends JMenuBar {

    private JMenuItem jMenuItemLogIn = null;
    private JMenuItem jMenuItemLogOut = null;
    private JMenuItem jMenuItemRegistration = null;
    private JMenuItem jMenuItemSettingModel = null;

    private Controller controller = null;
    private MenuBar menuBar = null;

    public MenuBar(Controller controller) {

        this.controller = controller;
        menuBar = this;

        createFileMenu();
        createSettingMenu();
        createAuthorizationMenu();
        createAboutMenu();

        setActionLIstener();
    }

    private void createFileMenu() {

        JMenu jMenuFile = new JMenu(Strings.MENU_FILE_NAME);

        JMenuItem jMenuItemExit = new JMenuItem(Strings.MENU_FILE_EXIT_ITEM_NAME);

        jMenuFile.add(jMenuItemExit);

        add(jMenuFile);
    }

    private void createSettingMenu() {

        JMenu jMenuSetting = new JMenu(Strings.MENU_SETTING_NAME);

        jMenuItemSettingModel = new JMenuItem(Strings.MENU_SETTING_ITEM_SETTING_NAME);

        jMenuSetting.add(jMenuItemSettingModel);

        add(jMenuSetting);
    }

    private void createAuthorizationMenu() {

        JMenu jMenuAuthorization = new JMenu(Strings.MENU_AUTHORIZATION_NAME);

        jMenuItemLogIn = new JMenuItem(Strings.MENU_AUTHORIZATION_ITEM_LOGIN_NAME);
        jMenuItemLogOut = new JMenuItem(Strings.MENU_AUTHORIZATION_ITEM_LOGOUT_NAME);
        jMenuItemRegistration = new JMenuItem(Strings.MENU_AUTHORIZATION_ITEM_REGISTR_NAME);

        jMenuAuthorization.add(jMenuItemRegistration);
        jMenuAuthorization.add(jMenuItemLogIn);
        jMenuAuthorization.add(jMenuItemLogOut);

        add(jMenuAuthorization);
    }

    private void createAboutMenu() {

        JMenu jMenuAbout = new JMenu(Strings.MENU_HELP_NAME);

        JMenuItem jMenuItemHelp = new JMenuItem(Strings.MENU_HELP_ITME_HELP_NAME);
        JMenuItem jMenuItemAbout = new JMenuItem(Strings.MENU_HELP_ITEM_ABOUT_NAME);

        jMenuAbout.add(jMenuItemHelp);
        jMenuAbout.add(jMenuItemAbout);

        add(jMenuAbout);
    }

    private void setActionLIstener() {
        jMenuItemLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthorizationDialog authorization1Dialog = new AuthorizationDialog(controller, menuBar);
            }
        });

        jMenuItemLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogOutDialog logOutDialog = new LogOutDialog(controller, menuBar);
                System.out.println("меню выхода");

            }
        });

        jMenuItemRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationDialog registrationDialog = new RegistrationDialog(controller, menuBar);
            }
        });

        jMenuItemSettingModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingDialog settingDialog = new SettingDialog(controller);
            }
        });
    }

    public JMenuItem getjMenuItemLogOut() {
        return jMenuItemLogOut;
    }

    public JMenuItem getjMenuItemLogIn() {
        return jMenuItemLogIn;
    }
}
