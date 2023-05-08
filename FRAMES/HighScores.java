package p2.FRAMES;

import p2.Constants;
import p2.Game;

import p2.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class HighScores extends JFrame {
    Game game;
    public HighScores(Game game){
        this.game = game;
        setTitle("Pacman - High Scores");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(Constants.MY_BLACK);


        game.addRecord(new Player("Robert", 5000));
        game.addRecord(new Player("Robert", 5000));
        game.addRecord(new Player("Robert", 5000));


        LinkedList lista = new LinkedList<>(game.getRecords());
        // TODO: do dopracowania!!!!!!!!!!!!!!

        for (Object o : lista) {
            System.out.println(o);
        }






        JList jList = new JList();
        jList.setBackground(Constants.MY_BLACK);
        jList.setForeground(Constants.MY_ORANGE);
        JScrollPane jScrollPane = new JScrollPane(jList);

        panel.add(jScrollPane, BorderLayout.CENTER);
        panel.add(panel1,BorderLayout.PAGE_START);

        JButton button = new JButton("MAIN MENU");
        button.setForeground(Constants.MY_BLACK);
        button.setBackground(Constants.MY_ORANGE);
        panel1.add(button);

        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new MainMenu(game));
            dispose();
        });
        pack();
        getContentPane().setBackground(Constants.MY_BLACK);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
