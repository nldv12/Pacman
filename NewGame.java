package p2;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NewGame extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 ;
    JPanel panel3 = new JPanel();
    public NewGame (int boardSize){
        setTitle("Pacman - Menu Główne");

        getContentPane().setLayout(new BorderLayout());

        panel1.setBackground(Color.BLUE);
        panel1.setPreferredSize(new Dimension(500,100));
        getContentPane().add(panel1, BorderLayout.NORTH);

        panel3.setBackground(Color.GREEN);
//        getContentPane().add(panel3, BorderLayout.SOUTH);
        panel3.setPreferredSize(new Dimension(500,100));
        createTable(boardSize);


        getContentPane().setBackground(Color.BLACK);
        setSize(500, 700);
        setVisible(true);
        setLocationRelativeTo(null);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createTable(int boardSize) {
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(500,500));
        panel2.setBackground(Color.RED);
        getContentPane().add(panel2, BorderLayout.CENTER);

        int cellSize = 2000 / boardSize;

        JTable table = new JTable(new CustomTableModel(boardSize));
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer(cellSize));



        panel2.setLayout(new BorderLayout());
        panel2.add(table, BorderLayout.CENTER);
    }

}

