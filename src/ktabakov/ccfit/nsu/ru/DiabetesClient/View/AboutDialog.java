package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;
import javax.swing.text.StringContent;

/**
 * Created by Константин on 13.03.2015.
 */
public class AboutDialog extends JDialog {

    String message = "Я вообще считаю, что те, кто искренне верит в \n" +
                     "демократию, люди не просто заблуждающиеся, \n" +
                     "а недалёкие. Но не Борис.";

    public AboutDialog() {
        JOptionPane.showMessageDialog(null, message, "О програме", 1);
    }
}
