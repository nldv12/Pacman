package p2.Models;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
    private final int size;

    public CustomTableModel(int size) {
        this.size = size;
    }

    @Override
    public int getRowCount() {
        return size;
    }
    @Override
    public int getColumnCount() {
        return size;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return null;
    }

    public boolean isCellEditable(int row, int col) {
        // nie chcemy, aby użytkownik mógł zmieniać wartości komórek planszy
        return false;
    }

}