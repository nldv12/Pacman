package p2.ImagePanels;

import p2.Constants;
import p2.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CountdownPanel extends JPanel {


    private boolean countdownFinished = false;
    private int secondsLeft;
    private JLabel timerLabel;
    private BufferedImage tileSet;
    private BufferedImage timeTile;

    public CountdownPanel(int timeInSec) {
        this.setLayout(new BorderLayout());
        this.setBackground(Constants.MY_BLACK);

        this.secondsLeft = timeInSec;

        timerLabel = new JLabel(getTimeString(secondsLeft));
        timerLabel.setForeground(Constants.MY_ORANGE);
        timerLabel.setFont(Constants.MY_FONT1);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(timerLabel, BorderLayout.CENTER);

        JPanel timeImagePanel = new JPanel(new BorderLayout());
        timeImagePanel.setOpaque(false);
        try {
            tileSet = ImageIO.read(new File("tileSet.png"));
            timeTile = tileSet.getSubimage(302, 65, 27, 27);
            JLabel imageLabel = new JLabel(new ImageIcon(timeTile));
            timeImagePanel.add(imageLabel, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // odstęp między timerLabel a imagePanel
        timeImagePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        this.add(timeImagePanel, BorderLayout.EAST);

        new Thread(() -> {
            while (secondsLeft > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                secondsLeft--;
                if(secondsLeft ==0)
                    countdownFinished = true;
                timerLabel.setText(getTimeString(secondsLeft));
            }
        }).start();
    }
    private String getTimeString(int seconds) {
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        return String.format("%02d:%02d", minutes, secondsLeft);
    }
    public boolean isCountdownFinished() {
        return countdownFinished;
    }

    public void addSeconds(int seconds) {
        secondsLeft += seconds;
        timerLabel.setText(getTimeString(secondsLeft));
    }
}



