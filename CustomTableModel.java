package p2;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

class CustomTableModel extends AbstractTableModel {
    private final int size;
    private Object[][] data;

    CustomTableModel(int size) {
        this.size = size;
        this.data = new Object[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == 0 || col == 0 || row == size - 1 || col == size - 1) {
                    // jeśli to jest krawędź planszy, to ustawiamy wartość true (ściana)
                    data[row][col] = true;
                } else {
                    // w przeciwnym razie losujemy, czy na tym polu jest punkt czy nie
                    data[row][col] = Math.random() > 0.2;
//                    data[row][col] = false;
                }
            }
        }
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
        return data[row][col];
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