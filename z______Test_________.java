package p2;

import p2.ImagePanels.CountdownPanel;

import javax.swing.*;

public class z______Test_________ {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CountdownPanel livesPanel = new CountdownPanel(120);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(livesPanel);
        frame.pack();
        frame.setVisible(true);

    }
}

