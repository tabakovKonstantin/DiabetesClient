package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

import com.healthmarketscience.jackcess.Table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Константин on 30.03.2015.
 */


public class TableModelData implements TableModel {

    private List<BGLevel> bgLevels = null;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public TableModelData(List<BGLevel> bgLevels) {
        this.bgLevels = bgLevels;
    }

    @Override
    public int getRowCount() {
        return bgLevels.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
        return "Time";
        case 1:
        return "Value";
    }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return int.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return bgLevels.get(rowIndex).getTimeMeasurement();
            case 1:
                return bgLevels.get(rowIndex).getValueMeasurement();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
