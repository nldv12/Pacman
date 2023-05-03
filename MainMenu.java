package p2;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public MainMenu(){
        setTitle("Pacman - Menu Główne");


        // panel na przyciski
        JPanel panel = new JPanel();
        panel.setBackground(Constants.MY_BLACK);
        add(panel);

        // przycisk "New Game"
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new ChooseBoardSize());
            dispose();
        });

        JButton highScores = new JButton("High Scores");


        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            dispose(); // zamknięcie okna
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setButtonAppearance(JButton button) {
        button.setPreferredSize(new Dimension(WIDTH, 80));
        button.setBackground(Constants.MY_BLACK);
        button.setForeground(Constants.MY_ORANGE);
        button.setFont(Constants.MY_FONT1);
    }

}
