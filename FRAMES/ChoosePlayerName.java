package p2.FRAMES;

import p2.*;
import p2.Enums.PacMovement;
import p2.Operations.MovePacOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChoosePlayerName extends JFrame {
    Game game;

    public ChoosePlayerName(Game game, int score) {
        this.game = game;

        setTitle("Pacman - Choose Player Name");


        JPanel panel = new JPanel(new FlowLayout());


        JLabel label = new JLabel("Wprowadź nazwę gracza");
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
            String name = textField.getText();
            if (name.length() > 1 && name.length() < 16) {
                game.addRecord(new Player(name, score));
                SwingUtilities.invokeLater(() -> new HighScores());
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
