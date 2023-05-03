package p2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class SquareCellRenderer extends DefaultTableCellRenderer {
    private final int cellSize;

    SquareCellRenderer(int cellSize) {
        this.cellSize = cellSize;
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setPreferredSize(new Dimension(cellSize, cellSize));
        return c;
    }
}