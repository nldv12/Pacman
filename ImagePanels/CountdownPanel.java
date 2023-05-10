package p2.ImagePanels;

import p2.Constants;

import javax.swing.*;
import java.awt.*;

public class CountdownPanel extends JPanel {

    public boolean isCountdownFinished() {
        return countdownFinished;
    }

    private boolean countdownFinished = false;
    private int secondsLeft;
    private JLabel timerLabel;

    public CountdownPanel(int timeInSec) {
        this.setLayout(new BorderLayout());
        this.setBackground(Constants.MY_BLACK);

        // Ustaw czas początkowy
        this.secondsLeft = timeInSec;

        // Utwórz etykietę do wyświetlania czasu
        timerLabel = new JLabel(getTimeString(secondsLeft));
        timerLabel.setForeground(Constants.MY_ORANGE);
        timerLabel.setFont(Constants.MY_FONT1);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Dodaj etykietę do panelu
        this.add(timerLabel, BorderLayout.CENTER);

        // Uruchom wątek odliczający czas
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

    // Metoda formatująca czas w formacie mm:ss
    private String getTimeString(int seconds) {
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        return String.format("%02d:%02d", minutes, secondsLeft);
    }
}



