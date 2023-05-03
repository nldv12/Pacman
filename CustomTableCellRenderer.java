package p2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private final int cellSize;

    CustomTableCellRenderer(int cellSize) {
        this.cellSize = cellSize;
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell.setOpaque(true);
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell.setPreferredSize(new Dimension(cellSize, cellSize));
        if (value.equals("#")) {
            cell.setBackground(Color.BLUE);
        } else if (value.equals("*")) {
            cell.setBackground(Color.YELLOW);
        } else if (value.equals(".")) {
            cell.setBackground(Color.BLACK);
        } else {
            cell.setBackground(Color.WHITE);
        }
        return cell;
    }
}