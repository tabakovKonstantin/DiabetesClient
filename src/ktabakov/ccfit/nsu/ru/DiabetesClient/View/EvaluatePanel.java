package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by Константин on 13.03.2015.
 */
public class EvaluatePanel extends JPanel {

    private File pathToFile = null;
    public EvaluatePanel() {
        createEvaluatePanel();

    }

    private void createEvaluatePanel() {

        JButton choseFileButton = new JButton("Загрузить");
        JButton evaluateButton = new JButton("Расчитать");

        JCheckBox checkBox = new JCheckBox("Учитывать этот файл при расчете модели");

        add(choseFileButton);
        add(checkBox);
        add(evaluateButton);

        setSize(500, 500);
        setVisible(true);
    }

    private void fileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mmg Files", "mmg");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           pathToFile = chooser.getSelectedFile();
        }
    }

    public File getPathToFile() {
        return pathToFile;
    }
}
