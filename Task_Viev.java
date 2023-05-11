package p2;

import p2.Enums.PacMovement;
import p2.FRAMES.ChoosePlayerName;
import p2.FRAMES.NewGame;
import p2.ImagePanels.ImagePanel;
import p2.Operations.FatchVievOperation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task_Viev implements Runnable {

    Game game;
    NewGame newGame;
    BufferedImage tileSet = null;
    public ImagePanel pacmanPanel;


    public Task_Viev(NewGame newGame, Game game) {
        this.game = game;
        this.newGame = newGame;

    }

    @Override
    public void run() {

        try {
            tileSet = ImageIO.read(new File("src\\p2\\tileSet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                changePacman();
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.performOperation(fatchVievOperation);
                newGame.updateScore();

                float cellSize = newGame.table.getColumnModel().getColumn(0).getWidth();
                int result1 = (int) (fatchVievOperation.getPacXposition() * cellSize) - 16;
                int result2 = (int) (fatchVievOperation.getPacYposition() * cellSize) - 16;
                pacmanPanel.setBounds(result1, result2, 25, 25);
                newGame.cellRenderer.setMap(fatchVievOperation.map);

                if (newGame.getCountdownPanel().isCountdownFinished()) {
                    SwingUtilities.invokeLater(() -> new ChoosePlayerName(game, game.getPlayerScore()));
                    newGame.dispose();
                    break;
                }

                Thread.sleep(50);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void changePacman() {
        if (game.pacMovement == PacMovement.MOVE_RIGHT) {
            if (System.currentTimeMillis() % 2 == 0)
                setPacmanImage(35, 3);
            else
                setPacmanImage(3, 3);
        } else if (game.pacMovement == PacMovement.MOVE_lEFT) {
            if (System.currentTimeMillis() % 2 == 0)
                setPacmanImage(37, 35);
            else
                setPacmanImage(3, 35);
        } else if (game.pacMovement == PacMovement.MOVE_UP) {
            if (System.currentTimeMillis() % 2 == 0)
                setPacmanImage(35, 69);
            else
                setPacmanImage(3, 69);
        } else if (game.pacMovement == PacMovement.MOVE_DOWN) {
            if (System.currentTimeMillis() % 2 == 0)
                setPacmanImage(35, 99);
            else
                setPacmanImage(3, 99);
        } else {
            setPacmanImage(67, 3);
        }

    }

    public void setPacmanImage(int x, int y) {
        BufferedImage pacmanTile = tileSet.getSubimage(x, y, 25, 25);
        if (pacmanPanel == null) {
            pacmanPanel = new ImagePanel(pacmanTile);
            pacmanPanel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(pacmanPanel);
        } else {
            pacmanPanel.setImage(pacmanTile);
            pacmanPanel.repaint();
        }
    }
}

