package p2.Models;

import javax.swing.event.TableModelListener;
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
    @Override
    public Class<?> getColumnClass(int col) {
        // ustawianie klasy komórek
        return String.class;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        // zmiana wartości w komórce
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // dodawanie słuchacza zdarzeń tabeli
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // usuwanie słuchacza zdarzeń tabeli
    }


    @Override
    public String getColumnName(int col) {
        // ustawianie nazwy kolumny
        return "";
    }


}