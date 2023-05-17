package p2.FRAMES;

import p2.Constants;
import p2.Game;
import p2.Task_Game;
import p2.Task_Viev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseBoardSize extends JFrame {
    Game game;
//    public ChooseBoardSize(Game game){
//        this.game = game;
//    }

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

        ActionListener okActionListener = e -> {
            String text = textField.getText();
            try {
                int value = Integer.parseInt(text);
                if (value >= 10 && value <= 100) {
                    boardSize = value;
                    game = new Game(boardSize,300);
                    SwingUtilities.invokeLater(() -> {
                        NewGame newGame = new NewGame(game,boardSize);
                        Task_Viev task_viev = new Task_Viev(newGame,game);
                        Thread viev = new Thread(task_viev);
                        viev.start();
                        Task_Game task_game = new Task_Game(game);
                        Thread gameThread = new Thread(task_game);
                        gameThread.start();
                    });
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ChooseBoardSize.this, "Wprowadzona wartość musi być z zakresu od 10 do 100", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ChooseBoardSize.this, "Wprowadzona wartość musi być liczbą całkowitą", "Błąd", JOptionPane.ERROR_MESSAGE);
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
