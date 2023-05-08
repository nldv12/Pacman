package p2.GUI;

import p2.Constants;
import p2.Game;

import javax.swing.*;
import java.awt.*;

public class ChooseBoardSize extends JFrame {
    Game game;
    public ChooseBoardSize(Game game){
        this.game = game;
    }

    public int boardSize = 0;
    public ChooseBoardSize(){
        setTitle("Pacman - Choose Board Size");

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Wprowadź liczbę od 10 do 100");
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

        button.addActionListener(e -> {
            String text = textField.getText();
            try {
                int value = Integer.parseInt(text);
                if (value >= 10 && value <= 100) {
                    boardSize = value;
                    SwingUtilities.invokeLater(() -> new NewGame(game, boardSize));
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ChooseBoardSize.this, "Wprowadzona wartość musi być z zakresu od 10 do 100", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ChooseBoardSize.this, "Wprowadzona wartość musi być liczbą całkowitą", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        setContentPane(panel);
        pack();


        getContentPane().setBackground(Constants.MY_BLACK);
        setSize(350, 120);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
