package p2.FRAMES;

import p2.Constants;
import p2.Enums.PacMovement;
import p2.Game;
import p2.Operations.MovePacOperation;
import p2.Task_Game;
import p2.Task_Viev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChooseBoardSize extends JFrame {
    Game game;

    public int boardSize = 0;
    public ChooseBoardSize() {

        setTitle("Pacman - Choose Board Size");

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Wprowadź liczbę od 10 do 100");
        label.setForeground(Constants.MY_ORANGE);
        label.setFont(Constants.MY_FONT_MEDIUM);
        panel.add(label);

        JTextField textField = new JTextField(10);
        textField.setBackground(Constants.MY_GREY);
        textField.setForeground(Constants.MY_ORANGE);
        textField.setFont(Constants.MY_FONT_MEDIUM);

        panel.add(textField);

        JButton button = new JButton("OK");
        button.setForeground(Constants.MY_BLACK);
        button.setBackground(Constants.MY_ORANGE);

        panel.add(button);

        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageBackground", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Constants.MY_ORANGE);
        UIManager.put("OptionPane.messageFont", Constants.MY_FONT_MEDIUM);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.background", Constants.MY_BLACK);
        UIManager.put("Button.foreground", Constants.MY_ORANGE);

        ActionListener okActionListener = e -> {
            String text = textField.getText();
            try {
                int value = Integer.parseInt(text);

                if (value >= 10 && value <= 100) {
                    boardSize = value;
                    game = new Game(boardSize, 300);
                    SwingUtilities.invokeLater(() -> {
                        NewGame newGame = new NewGame(game, boardSize);
                        Task_Viev task_viev = new Task_Viev(newGame, game);
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
        setContentPane(panel);
        pack();

        getContentPane().setBackground(Constants.MY_BLACK);
        setSize(350, 120);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
