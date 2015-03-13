package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;

/**
 * Created by Константин on 13.03.2015.
 */
public class ErrorDialog extends JDialog {

    public void showErrorDialog(String errorMassege) {
        JOptionPane.showMessageDialog(null, errorMassege, "Error", 2);
    }
}
