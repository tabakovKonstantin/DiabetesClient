package ktabakov.ccfit.nsu.ru.DiabetesClient.View;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Константин on 29.03.2015.
 */
public class DataTablePanel  extends JPanel {

    private TableModel modelForRealData = null;
    private TableModel modelForPredictData = null;

    public DataTablePanel() {

        Box boxForTable = Box.createHorizontalBox();

        JTable tableRealData  = new JTable(modelForRealData);
        JTable tablePredictData  = new JTable(modelForPredictData);

        JPanel panelForTableRealData = new JPanel();
        JPanel panelForTablePredictData = new JPanel();

        JLabel labelRealData = new JLabel("Real");
        JLabel labelPredictData = new JLabel("Predict");

        panelForTableRealData.setLayout(new BorderLayout());
        panelForTablePredictData.setLayout(new BorderLayout());

        panelForTableRealData.add(labelRealData, BorderLayout.SOUTH);
        panelForTablePredictData.add(labelPredictData, BorderLayout.SOUTH);

        JScrollPane scrollPaneForRealDataTable = new JScrollPane(tableRealData,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane1ForPredictDataTable = new JScrollPane(tablePredictData,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );

        scrollPaneForRealDataTable.setPreferredSize(new Dimension(200, 300));
        scrollPane1ForPredictDataTable.setPreferredSize(new Dimension(200, 300));

        panelForTableRealData.add(scrollPaneForRealDataTable);
        panelForTablePredictData.add(scrollPane1ForPredictDataTable);

        boxForTable.add(panelForTableRealData);
        boxForTable.add(Box.createHorizontalStrut(10));
        boxForTable.add(panelForTablePredictData);

        add(boxForTable);

        setVisible(true);
    }

    public void setModelForRealData(TableModel modelForRealData) {
        this.modelForRealData = modelForRealData;

    }

    public TableModel setModelForPredictData() {
        return modelForPredictData;
    }
}
