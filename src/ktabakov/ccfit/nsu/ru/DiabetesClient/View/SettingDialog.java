package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class SettingDialog extends JDialog {

    public SettingDialog() {
        createSettingDialog();
    }

    private void createSettingDialog() {
        JPanel panel = new JPanel();

        JButton okButton = new JButton("Ок");
        JButton cancelButton = new JButton("Cancel");

        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);

        setTitle("Настроки модели прогнозирования");
        setSize(200, 200);
        setVisible(true);
    }
}
