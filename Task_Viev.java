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
    public ImagePanel ghostPanel;
    boolean ispowerUp = false;



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
                changeGhost();
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.performOperation(fatchVievOperation);
                newGame.updateScore();

                float cellSize = newGame.table.getColumnModel().getColumn(0).getWidth();
                // co to - 16
                int result1 = (int) (fatchVievOperation.getPacXposition() * cellSize) - 16;
                int result2 = (int) (fatchVievOperation.getPacYposition() * cellSize) - 16;
//                ghostPanel.setBounds(60,60,25, 25);
                pacmanPanel.setBounds(result1, result2, 24, 24);
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

        if (newGame.getCellSize() == 24){
            if (game.pacMovement == PacMovement.MOVE_RIGHT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(35, 3);
                else
                    setPacmanImage(6, 3);
            } else if (game.pacMovement == PacMovement.MOVE_lEFT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(36, 32);
                else
                    setPacmanImage(7, 32);
            } else if (game.pacMovement == PacMovement.MOVE_UP) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(35, 62 );
                else
                    setPacmanImage(6, 62);
            } else if (game.pacMovement == PacMovement.MOVE_DOWN) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(35, 90);
                else
                    setPacmanImage(6, 90);
            } else {
                setPacmanImage(65, 3);
            }
        }else {
            if (game.pacMovement == PacMovement.MOVE_RIGHT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 102);
                else
                    setPacmanImage(226, 102);
            } else if (game.pacMovement == PacMovement.MOVE_lEFT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 120);
                else
                    setPacmanImage(228, 120);
            } else if (game.pacMovement == PacMovement.MOVE_UP) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 140);
                else
                    setPacmanImage(227, 140);
            } else if (game.pacMovement == PacMovement.MOVE_DOWN) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 158);
                else
                    setPacmanImage(227, 158);
            } else {
                setPacmanImage(201, 124);
            }
        }

    }

    public void setPacmanImage(int x, int y) {
        BufferedImage pacmanTile;

        if (newGame.getCellSize() == 24)
            pacmanTile = tileSet.getSubimage(x, y, 24, 24);
        else
            pacmanTile = tileSet.getSubimage(x, y, 16, 16);

        if (pacmanPanel == null) {
            pacmanPanel = new ImagePanel(pacmanTile);
            pacmanPanel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(pacmanPanel);
        } else {
            pacmanPanel.setImage(pacmanTile);
            pacmanPanel.repaint();
        }
    }

    private void changeGhost() {
        if (ispowerUp){
            setGhostImage(69, 132);
        }else{
            if (System.currentTimeMillis() % 2 == 0)
                setGhostImage(6, 132);
            else
                setGhostImage(38, 132);
        }
    }

    public void setGhostImage(int x, int y) {
        BufferedImage ghostTile = tileSet.getSubimage(x, y, 25, 25);
        if (ghostPanel == null) {
            ghostPanel = new ImagePanel(ghostTile);
            ghostPanel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(ghostPanel);
        } else {
            ghostPanel.setImage(ghostTile);
            ghostPanel.repaint();
        }
    }
}

