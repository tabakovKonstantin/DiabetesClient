package ktabakov.ccfit.nsu.ru.DiabetesClient.View;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class View extends JFrame {

    private MenuBar menuBar = null;

    public View() {

        menuBar = new MenuBar();

        setLocation(100, 100);
        setSize(500, 500);
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


        EvaluatePanel evaluatePanel = new EvaluatePanel();
        PredictDataTablePanel predictDataTablePanel = new PredictDataTablePanel();

        //add(predictDataTablePanel);
        //add(evaluatePanel);
        DataTablePanel da = new DataTablePanel();
        add(da);

        JScrollPane scrollPane = new JScrollPane(da, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);
        setVisible(true);

        //pack();

        /*JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);*/



    }



}
