package p2.FRAMES;

import p2.Constants;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public MainMenu(){
        setTitle("Pacman - Menu Główne");

        JPanel panel = new JPanel();
        panel.setBackground(Constants.MY_BLACK);
        add(panel);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new ChooseBoardSize());
            dispose();
        });

        JButton highScores = new JButton("High Scores");
        highScores.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new HighScores());
            dispose();
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        setButtonAppearance(newGame);
        setButtonAppearance(highScores);
        setButtonAppearance(exit);

        panel.add(newGame);
        panel.add(highScores);
        panel.add(exit);


        getContentPane().setBackground(Constants.MY_BLACK);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setButtonAppearance(JButton button) {
        button.setPreferredSize(new Dimension(WIDTH, 80));
        button.setBackground(Constants.MY_BLACK);
        button.setForeground(Constants.MY_ORANGE);
        button.setFont(Constants.MY_FONT_BIG);
    }

}
