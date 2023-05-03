package p2;

import javax.swing.*;

public class Okno extends JFrame {
    public Okno() {
        generateFrame();
    }

    public void generateFrame() {


        Object [] [] elementy = {
                {"Adam", "Kowalski", 30},
                {"Jan", "Nowak", 23},
                {"Michał", "Jabłoński", 22},
                {"Sławomir", "Oleksiak", 18}
        };

        String[] kolumny = {"Imię", "Nazwisko", "Wiek"};


        JTable jTable = new JTable();

        jTable.setModel(new MyTableModel(elementy,kolumny));

        JScrollPane jScrollPane = new JScrollPane(jTable);


        add(jScrollPane);

        setSize(200, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
