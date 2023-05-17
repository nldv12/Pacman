package p2.FRAMES;

import p2.Constants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class HighScores extends JFrame {
    public HighScores(){
        setTitle("Pacman - High Scores");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.CYAN);
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Constants.MY_BLACK);

        JButton button = new JButton("MAIN MENU");
        button.setForeground(Constants.MY_BLACK);
        button.setBackground(Constants.MY_ORANGE);
        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new MainMenu());
            dispose();
        });

        panel1.add(button);

        JPanel panel2 = new JPanel(new GridLayout(1, 1));
        panel2.setBackground(Constants.MY_BLUE);

        DefaultListModel model = new DefaultListModel();
        for (Object obj : getRecords()) {
            model.addElement(obj);
        }

        JList jList = new JList(model);
        jList.setFont(Constants.MY_FONT_BIG);
        jList.setBackground(Constants.MY_BLACK);
        jList.setForeground(Constants.MY_ORANGE);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)jList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setVerticalAlignment(JLabel.CENTER);

        JScrollPane jScrollPane = new JScrollPane(jList);
        jScrollPane.setBorder(null);

        jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
                this.trackColor = Constants.MY_GREY;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = super.createDecreaseButton(orientation);
                decreaseButton.setBackground(Constants.MY_GREY);
                return decreaseButton;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton increaseButton = super.createIncreaseButton(orientation);
                increaseButton.setBackground(Constants.MY_GREY);
                return increaseButton;
            }
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                    SwingUtilities.invokeLater(() -> new MainMenu());
                    dispose();
                }
            }
        });

        setFocusable(true);



        panel2.add(jScrollPane);

        panel.add(panel1,BorderLayout.PAGE_START);
        panel.add(panel2,BorderLayout.CENTER);


        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static LinkedList<String> getRecords() {
        LinkedList<String> records = new LinkedList<>();
        try {
            // Otwieramy plik do odczytu
            FileReader reader = new FileReader("HighScores.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Odczytujemy kolejne linie z pliku i dodajemy je do listy
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                records.add(line);
            }
            // Zamykamy plik
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Plik HighScores.txt nie istnieje", "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd podczas odczytu pliku HighScores.txt", "Błąd", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
        }
        records.sort((line1, line2) -> {
            int score1 = getScoreValue(line1);
            int score2 = getScoreValue(line2);
            return Integer.compare(score2,score1);
        });

        return records;
    }

    private static int getScoreValue(String line) {
        int scoreStartIndex = line.indexOf("score") + 5;
        int pointsStartIndex = line.indexOf("points");

        String scoreValue = line.substring(scoreStartIndex, pointsStartIndex).trim();
        return Integer.parseInt(scoreValue);
    }
}
