package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Константин on 29.03.2015.
 */
public class DataTablePanel  extends JPanel{
    public DataTablePanel() {

        String[] columnNames = {
                "Name",
                "Last modified",
                "Type",
                "Size"
        };

        String[][] data = {
                {"addins", "02.11.2006 19:15", "Folder", ""},
                {"AppPatch", "03.10.2006 14:10", "Folder", ""},
                {"assembly", "02.11.2006 14:20", "Folder", ""},
                {"Boot", "13.10.2007 10:46", "Folder", ""},
                {"Branding", "13.10.2007 12:10", "Folder", ""},
                {"Cursors", "23.09.2006 16:34", "Folder", ""},
                {"Debug", "07.12.2006 17:45", "Folder", ""},
                {"Fonts", "03.10.2006 14:08", "Folder", ""},
                {"Help", "08.11.2006 18:23", "Folder", ""},
                {"explorer.exe", "18.10.2006 14:13", "File", "2,93MB"},
                {"helppane.exe", "22.08.2006 11:39", "File", "4,58MB"},
                {"twunk.exe", "19.08.2007 10:37", "File", "1,08MB"},
                {"nsreg.exe", "07.08.2007 11:14", "File", "2,10MB"},
                {"avisp.exe", "17.12.2007 16:58", "File", "12,67MB"},
        };

        Box box = Box.createHorizontalBox();



    JTable table  = new JTable();
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new JLabel("test"));

        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        scrollPane.setPreferredSize(new Dimension(200,300));
        panel.add(scrollPane, BorderLayout.SOUTH);
        box.add(panel);

        box.add(Box.createHorizontalStrut(10));

        JTable table1  = new JTable(100, 2);
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane1 = new JScrollPane(table1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        scrollPane1.setPreferredSize(new Dimension(200,300));
        box.add(scrollPane1);

        add(box);

        setVisible(true);


    }
}
