package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class MenuBar extends JMenuBar{
    public MenuBar() {

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

        JMenuItem jMenuItemLogIn = new JMenuItem("Вход");
        JMenuItem jMenuItemLogOut = new JMenuItem("Выход");
        JMenuItem jMenuItemRegistration = new JMenuItem("Регистрация");

        jMenuAuthorization.add(jMenuItemLogIn);
        jMenuAuthorization.add(jMenuItemLogOut);
        jMenuAuthorization.add(jMenuItemRegistration);

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
}
