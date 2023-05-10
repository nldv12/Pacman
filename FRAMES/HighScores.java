package p2.FRAMES;

import p2.Constants;
import p2.Game;

import p2.Player;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class HighScores extends JFrame {
    Game game;
    public HighScores(Game game){
        this.game = game;
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
            SwingUtilities.invokeLater(() -> new MainMenu(game));
            dispose();
        });

        panel1.add(button);

        JPanel panel2 = new JPanel(new GridLayout(1, 1));
        panel2.setBackground(Constants.MY_BLUE);

        DefaultListModel model = new DefaultListModel();
        for (Object obj : game.getRecords()) {
            model.addElement(obj);
        }

        JList jList = new JList(model);
        jList.setFont(Constants.MY_FONT1);
        jList.setBackground(Constants.MY_BLACK);
        jList.setForeground(Constants.MY_ORANGE);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)jList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setVerticalAlignment(JLabel.CENTER);

        JScrollPane jScrollPane = new JScrollPane(jList);
        UIManager.put("ScrollBar.arrowButtonBackground", Color.RED);
        jScrollPane.setBorder(null);

        jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
                this.trackColor = Constants.MY_GREY;
            }
            // kod zmiany koloru suwaka
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

        panel2.add(jScrollPane);

        panel.add(panel1,BorderLayout.PAGE_START);
        panel.add(panel2,BorderLayout.CENTER);

        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
