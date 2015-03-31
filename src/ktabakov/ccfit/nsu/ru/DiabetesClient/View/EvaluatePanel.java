package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.Controller.Controller;

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

    private Controller controller = null;
    private DataTablePanel dataTablePanel = null;

    public EvaluatePanel(Controller controller, DataTablePanel dataTablePanel) {

        this.controller = controller;
        this.dataTablePanel = dataTablePanel;

        createEvaluatePanel();
    }

    private void createEvaluatePanel() {

        JButton choseFileButton = new JButton("Загрузить");
        JButton evaluateButton = new JButton("Расчитать");

        JCheckBox checkBox = new JCheckBox("Учитывать этот файл при расчете модели");

        choseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File pathToFile = fileChooser();
                System.out.print(pathToFile);
                TableModel modelForRealData = controller.getTableModelForRealData(pathToFile);
                dataTablePanel.setModelForRealData(modelForRealData);


            }
        });

        evaluateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.checkDataRealDownload()) {
                    controller.sendJSONWithRealData();
                }
                else {
                    new ErrorDialog().showErrorDialog("Вы не загрузили данные");
                }

                TableModel modelForPredictData = controller.getTableModelForPredictData();
                dataTablePanel.setModelForPredictData(modelForPredictData);

            }
        });

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
           return chooser.getSelectedFile();
        }
        else {
            new ErrorDialog().showErrorDialog("net takogo fiyla");
            return null;
        }

    }

}
