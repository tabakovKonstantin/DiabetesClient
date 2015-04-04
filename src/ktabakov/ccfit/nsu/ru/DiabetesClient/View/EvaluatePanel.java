package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;
import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Константин on 13.03.2015.
 */
public class EvaluatePanel extends JPanel {

    JButton choseFileButton = null;
    JButton evaluateButton = null;

    private Controller controller = null;
    private DataTablePanel dataTablePanel = null;

    public EvaluatePanel(Controller controller, DataTablePanel dataTablePanel) {

        this.controller = controller;
        this.dataTablePanel = dataTablePanel;

        initComponents();
        setActionListener();

    }

    private void initComponents() {

        choseFileButton = new JButton(Strings.DOWNLOAD_BUTTON_NAME);
        evaluateButton = new JButton(Strings.EVALUATE_BUTTON_NAME);
        evaluateButton.setEnabled(false);

        JCheckBox checkBox = new JCheckBox("Учитывать этот файл при расчете модели");

        add(choseFileButton);
        add(evaluateButton);
        add(checkBox);

        setSize(500, 500);
        setVisible(true);
    }

    private File fileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mmg Files", "mmg");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if(!chooser.getSelectedFile().equals("")) {
                evaluateButton.setEnabled(true);
                return chooser.getSelectedFile();
            }
            return null;

        }
        else {
            new ErrorDialog().showErrorDialog(Strings.ERROR_MASSAGE_DONT_SELECT_FILE);
            return null;
        }

    }

    private void setActionListener() {
        choseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathToFile = fileChooser().toString();
                System.out.print(pathToFile);
                TableModel modelForRealData = controller.getTableModelForRealData(pathToFile);
                dataTablePanel.setModelForRealData(modelForRealData);


            }
        });

        evaluateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(controller.checkDataRealDownload()) {
                    controller.sendJSONWithRealData();
//                }
//                else {
//                    new ErrorDialog().showErrorDialog("Вы не загрузили данные");
//                }

                TableModel modelForPredictData = controller.getTableModelForPredictData();
                dataTablePanel.setModelForPredictData(modelForPredictData);

            }
        });
    }

}
