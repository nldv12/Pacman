package p2.Models;

import p2.Constants;
import p2.Enums.FieldValue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private BufferedImage wallImage;
    private BufferedImage dotImage;
    private BufferedImage bigDotImage;
    private FieldValue[][] map;
    private final int cellSize;
//    public FieldValue[][] map;


    public CustomTableCellRenderer(int cellSize) {
        this.cellSize = cellSize;

        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);

        try {
            BufferedImage tileSet = ImageIO.read(new File("tileSet.png"));
            if (cellSize == 16) {
                wallImage = tileSet.getSubimage(304, 105, 16, 16);
                dotImage = tileSet.getSubimage(377, 43, 16, 16);
                bigDotImage = tileSet.getSubimage(343, 43, 16, 16);
            }
            else if (cellSize == 24) {
                wallImage = tileSet.getSubimage(268, 100, 24, 24);
                dotImage = tileSet.getSubimage(381, 62, 24, 24);
                bigDotImage = tileSet.getSubimage(346, 63, 24, 24);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (map != null) {
            switch (map[row][column]) {
                case SPACE:
                    c.setBackground(Constants.MY_BLACK);
                    break;
                case WALL:
                    JLabel wallLabel = new JLabel(new ImageIcon(wallImage));
                    wallLabel.setHorizontalAlignment(JLabel.CENTER);
                    wallLabel.setVerticalAlignment(JLabel.CENTER);
                    return wallLabel;
                case DOT:
                    JLabel dotLabel = new JLabel(new ImageIcon(dotImage));
                    dotLabel.setHorizontalAlignment(JLabel.CENTER);
                    dotLabel.setVerticalAlignment(JLabel.CENTER);
                    return dotLabel;
                case BIG_DOT:
                    JLabel bigDotLabel = new JLabel(new ImageIcon(bigDotImage));
                    bigDotLabel.setHorizontalAlignment(JLabel.CENTER);
                    bigDotLabel.setVerticalAlignment(JLabel.CENTER);
                    return bigDotLabel;
            }
        }
        return c;
    }

    public void setMap(FieldValue[][] map) {
        this.map = map;
    }
}