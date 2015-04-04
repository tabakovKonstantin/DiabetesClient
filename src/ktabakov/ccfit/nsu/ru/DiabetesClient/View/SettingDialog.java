package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import ktabakov.ccfit.nsu.ru.DiabetesClient.res.Strings;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class SettingDialog extends JDialog {

    public SettingDialog() {
        initComponents();
        setTitle(Strings.NAME_SETTING_DIALOG);
        setSize(200, 200);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();

        JButton okButton = new JButton(Strings.OK_BUTTON_NAME);
        JButton cancelButton = new JButton(Strings.CANCEL_BUTTON_NAME);

        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);

    }
}
