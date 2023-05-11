package p2.FRAMES;

import p2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePlayerName extends JFrame {
    Game game;

    private int score ;

    public ChoosePlayerName(Game game, int score) {
        this.game = game;
        this.score = score;

        setTitle("Pacman - Choose Player Name");

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Wprowadź nazwę gracza");
        label.setForeground(Constants.MY_ORANGE);
        label.setFont(Constants.MY_FONT2);
        panel.add(label);

        JTextField textField = new JTextField(10);
        textField.setBackground(Constants.MY_GREY);
        textField.setForeground(Constants.MY_ORANGE);
        textField.setFont(Constants.MY_FONT2);

        panel.add(textField);

        JButton button = new JButton("OK");
        button.setForeground(Constants.MY_BLACK);
        button.setBackground(Constants.MY_ORANGE);

        panel.add(button);

        ActionListener okActionListener = e -> {
            String name = textField.getText();
            if (name.length() > 1 && name.length() < 16) {
                game.addRecord(new Player(name, score));
                SwingUtilities.invokeLater(() -> new HighScores(game));
                dispose();
            } else {
                JOptionPane.showMessageDialog(ChoosePlayerName.this, "Imię może mieć min 2 znaki max 15", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        };


        button.addActionListener(okActionListener);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okActionListener.actionPerformed(e);
            }
        });

        setContentPane(panel);
        pack();
        getContentPane().setBackground(Constants.MY_BLACK);
        setSize(350, 120);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
