package p2.ImagePanels;

import p2.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LivesPanel extends JPanel {

    private BufferedImage tileSet;
    private BufferedImage heartTile;

    private int heartCount;

    public LivesPanel(int heartCount) {
        this.heartCount = heartCount;
        setBackground(Constants.MY_BLACK);

        try {
            tileSet = ImageIO.read(new File("tileSet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        heartTile = tileSet.getSubimage(270, 67, 22, 22);

        setPreferredSize(new Dimension(heartCount * 22, 22));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < heartCount; i++) {
            g.drawImage(heartTile, i * 22, 0, null);
        }
    }


    public int getHeartCount() {
        return heartCount;
    }

    public void decHeartCount() {
        this.heartCount --;
    }


}