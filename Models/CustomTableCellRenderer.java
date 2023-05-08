package p2.Models;

import p2.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private final int cellSize;

    public CustomTableCellRenderer(int cellSize) {
        this.cellSize = cellSize;
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof Boolean) {
            boolean val = (Boolean) value;
            if (val == true) {
                c.setForeground(Constants.MY_BOWN);
                c.setBackground(Constants.MY_BOWN);
            } else {
                c.setForeground(Constants.MY_BLACK);
                c.setBackground(Constants.MY_BLACK);
            }
            if (!isSelected) {
                // usuwamy obramowanie komórki
                setBorder(BorderFactory.createEmptyBorder());
            }
        }
        return c;
    }
}