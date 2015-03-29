package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Константин on 29.03.2015.
 */
public class PredictDataTablePanel extends JPanel {
    public  PredictDataTablePanel() {

        setLayout( new BorderLayout() );
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(50));

        JPanel panel = new JPanel( new BorderLayout() );
        JTable table = new JTable(10, 5);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table);
        box.add( panel );

        box.add(Box.createVerticalStrut(50));

        JTable table2 = new JTable(100, 5);
        table2.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane2 = new JScrollPane( table2 );
        box.add( scrollPane2 );

        box.add(Box.createVerticalStrut(50));

        JTable table3 = new JTable(10, 5);
        box.add( table3 );

        box.add(Box.createVerticalStrut(50));

        add( new JScrollPane(box) );

        /*setLayout( new BorderLayout() );
        Box box = Box.createHorizontalBox();

        box.add(Box.createHorizontalStrut(10));

        JPanel panel = new JPanel( new BorderLayout() );
        JTable table = new JTable(10, 5);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table);
        box.add( panel );
        box.add(Box.createHorizontalStrut(50));
        box.add(Box.createVerticalStrut(50));

        JTable table2 = new JTable(100, 5);
        table2.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane2 = new JScrollPane( table2 );
        box.add( scrollPane2 );

        box.add(Box.createVerticalStrut(50));


        add( new JScrollPane(box) );*/
    }

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

    private void createRealDataTable() {

        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /*JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
*/
//        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       // table.add(scrollPane);


//        scrollPane.setPreferredSize(new Dimension(60, 60));

//scrollPane.add(table);
       add(table);
//        add(scrollPane);

        setSize(10, 10);
        setVisible(true);

    }




   /* JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    getContentPane().add(scrollPane);
//        this.setPreferredSize(new Dimension(450, 200));
    this.pack();
    this.setLocationRelativeTo(null);
//        this.setVisible(true);*/

//
}

