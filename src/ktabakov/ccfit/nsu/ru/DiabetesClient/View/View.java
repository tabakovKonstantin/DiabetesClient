package ktabakov.ccfit.nsu.ru.DiabetesClient.View;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Константин on 13.03.2015.
 */
public class View extends JFrame {

    private MenuBar menuBar = null;

    public View() {

        menuBar = new MenuBar();

        setLocation(700, 500);
        setSize(700, 200);
        setTitle("DiabetesClient");
        setJMenuBar(menuBar);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*AuthorizationDialog authorizationDialog = new AuthorizationDialog();
        SettingDialog settingDialog = new SettingDialog();
        AboutDialog aboutDialog = new AboutDialog();
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.showErrorDialog("Hello world");
        RegistrationDialog registrationDialog = new RegistrationDialog();*/

        /*JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);*/

//

    }



}
