package ktabakov.ccfit.nsu.ru.DiabetesClient.View;


import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class View extends JFrame {

    private Controller controller = null;
    private MenuBar menuBar = null;
    private EvaluatePanel evaluatePanel = null;
    private DataTablePanel dataTablePanel = null;

    public View() {

        /*AuthorizationDialog authorizationDialog = new AuthorizationDialog();
        SettingDialog settingDialog = new SettingDialog();
        AboutDialog aboutDialog = new AboutDialog();
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.showErrorDialog("Hello world");
        RegistrationDialog registrationDialog = new RegistrationDialog();*/

        controller = new Controller();
        menuBar = new MenuBar(controller);
        dataTablePanel = new DataTablePanel();
        evaluatePanel = new EvaluatePanel(controller, dataTablePanel);

        add(dataTablePanel, BorderLayout.NORTH);
        add(evaluatePanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(dataTablePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);

        setLocation(100, 100);
        setSize(500, 500);
        setTitle("DiabetesClient");
        setJMenuBar(menuBar);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
