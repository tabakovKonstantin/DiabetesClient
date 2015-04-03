package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 13.03.2015.
 */
public class MenuBar extends JMenuBar {

    private Controller controller = null;
    private MenuBar menuBar = null;
    private JMenuItem jMenuItemLogIn = null;
    private JMenuItem jMenuItemLogOut = null;

    public MenuBar(Controller controller) {

        this.controller = controller;
        menuBar = this;

        createFileMenu();
        createSettingMenu();
        createAuthorizationMenu();
        createAboutMenu();
    }

    private void createFileMenu() {

        JMenu jMenuFile = new JMenu("Файл");

        JMenuItem jMenuItemExit = new JMenuItem("Выход");

        jMenuFile.add(jMenuItemExit);

        add(jMenuFile);
    }

    private void createSettingMenu() {

        JMenu jMenuSetting = new JMenu("Настройки");

        JMenuItem jMenuItemSettingModel = new JMenuItem("Настройки модели");

        jMenuSetting.add(jMenuItemSettingModel);

        add(jMenuSetting);
    }

    private void createAuthorizationMenu() {

        JMenu jMenuAuthorization = new JMenu("Авторизация");

        jMenuItemLogIn = new JMenuItem("Вход");
        jMenuItemLogOut = new JMenuItem("Выход");
        JMenuItem jMenuItemRegistration = new JMenuItem("Регистрация");

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

        jMenuAuthorization.add(jMenuItemRegistration);
        jMenuAuthorization.add(jMenuItemLogIn);
        jMenuAuthorization.add(jMenuItemLogOut);

        add(jMenuAuthorization);
    }

    private void createAboutMenu() {

        JMenu jMenuAbout = new JMenu("Справка");

        JMenuItem jMenuItemHelp = new JMenuItem("Справка");
        JMenuItem jMenuItemAbout = new JMenuItem("О программе");

        jMenuAbout.add(jMenuItemHelp);
        jMenuAbout.add(jMenuItemAbout);

        add(jMenuAbout);
    }

    public JMenuItem getjMenuItemLogOut() {
        return jMenuItemLogOut;
    }

    public JMenuItem getjMenuItemLogIn() {
        return jMenuItemLogIn;
    }
}
