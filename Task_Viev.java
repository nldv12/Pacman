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
            tileSet = ImageIO.read(new File("tileSet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                changePacman();
//                changeGhost();
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.performOperation(fatchVievOperation);
                newGame.updateScore();
//                ghostPanel.setBounds(60,60,25, 25);


                showPacman(fatchVievOperation);

                newGame.cellRenderer.setMap(fatchVievOperation.map);


                boolean outOfTime = newGame.getCountdownPanel().isCountdownFinished();
                boolean ateAllDots = game.getBigDotCount() == 0 && game.getDotCount() == 0;

                if (outOfTime || ateAllDots) {
                    if (outOfTime)
                        JOptionPane.showMessageDialog(null, "Skończył ci się czas ale nadal możesz zapisać swój wynnik, bo nie zginąłęś", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    if (ateAllDots)
                        JOptionPane.showMessageDialog(null, "Brawo! Wygrałeś", "Game Over", JOptionPane.INFORMATION_MESSAGE);

                    SwingUtilities.invokeLater(() -> new ChoosePlayerName(game, game.getPlayerScore()));

                    newGame.dispose();
                    break;
                }


                Thread.sleep(20);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void showPacman(FatchVievOperation fatchVievOperation) {

        int row = game.getCurrentRow();
        int column = game.getCurrentColumn();


//        if (restX != 0.5 && restY != 0.5) {
//        int vievPacX = (int) ((fatchVievOperation.getPacXposition() * newGame.realCellSizeX) - newGame.realCellSizeX / 2);
//        int vievPacY = (int) ((fatchVievOperation.getPacYposition() * newGame.realCellSizeY) - newGame.realCellSizeY / 2);
//        pacmanPanel.setBounds(vievPacX, vievPacY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);

//        Rectangle cellRect = newGame.table.getCellRect(row, column, true);
//
//        int vievPacX = (int) ((fatchVievOperation.getPacXposition() * cellRect.width) - cellRect.width / 2);
//        int vievPacY = (int) ((fatchVievOperation.getPacYposition() * cellRect.height) - cellRect.height / 2);
//        pacmanPanel.setBounds(vievPacX, vievPacY, cellRect.width, cellRect.height);



//        }else {
            Rectangle cellRect = newGame.table.getCellRect(row, column, true);
            int cellX = cellRect.x;
            int cellY = cellRect.y;
            pacmanPanel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//        }


    }

    private void changePacman() {

        if (newGame.getCellSize() == 24) {
            if (game.getPacMovement() == PacMovement.MOVE_RIGHT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(31, 3);
                else
                    setPacmanImage(5, 3);
            } else if (game.getPacMovement() == PacMovement.MOVE_lEFT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(32, 29);
                else
                    setPacmanImage(6, 29);
            } else if (game.getPacMovement() == PacMovement.MOVE_UP) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(32, 56);
                else
                    setPacmanImage(6, 56);
            } else if (game.getPacMovement() == PacMovement.MOVE_DOWN) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(32, 81);
                else
                    setPacmanImage(6, 81);
            } else {
                setPacmanImage(58, 3);
            }
        } else {
            if (game.getPacMovement() == PacMovement.MOVE_RIGHT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 102);
                else
                    setPacmanImage(226, 102);
            } else if (game.getPacMovement() == PacMovement.MOVE_lEFT) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 120);
                else
                    setPacmanImage(228, 120);
            } else if (game.getPacMovement() == PacMovement.MOVE_UP) {
                if (System.currentTimeMillis() % 2 == 0)
                    setPacmanImage(245, 140);
                else
                    setPacmanImage(227, 140);
            } else if (game.getPacMovement() == PacMovement.MOVE_DOWN) {
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
            pacmanTile = tileSet.getSubimage(x, y, 22, 22);
        else
            pacmanTile = tileSet.getSubimage(x, y, 16, 16);

        if (pacmanPanel == null) {
            pacmanPanel = new ImagePanel(pacmanTile);
            pacmanPanel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(pacmanPanel);
        } else {
            pacmanPanel.setImage(pacmanTile);
//            pacmanPanel.repaint();
        }
    }

    private void changeGhost() {
        if (ispowerUp) {
            setGhostImage(69, 132);
        } else {
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

