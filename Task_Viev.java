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

    public ImagePanel ghost0Panel;
    public ImagePanel ghost1Panel;
    public ImagePanel ghost2Panel;
    public ImagePanel ghost3Panel;
    boolean ispowerUp = false;
    boolean initialRepainted;
    long lastImageChangeTime;


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
                changeGhost();
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.performOperation(fatchVievOperation);
                newGame.updateScore();
//                ghostPanel.setBounds(60,60,25, 25);


                showPacman(fatchVievOperation);
                showGhosts(fatchVievOperation);

                newGame.cellRenderer.setMap(fatchVievOperation.map);



                if (initialRepainted== false){
                    newGame.table.repaint();
                    initialRepainted = true;
                }

                boolean outOfTime = newGame.getCountdownPanel().isCountdownFinished();
                boolean ateAllDots = game.getBigDotCount() == 0 && game.getDotCount() == 0;
                boolean dead = game.isPacmanDead();


                if (outOfTime || ateAllDots || dead) {
                    UIManager.put("OptionPane.background", Color.BLACK);
                    UIManager.put("OptionPane.messageBackground", Color.BLACK);
                    UIManager.put("OptionPane.messageForeground", Constants.MY_ORANGE);
                    UIManager.put("OptionPane.messageFont", Constants.MY_FONT2);
                    UIManager.put("Panel.background", Color.BLACK);
                    UIManager.put("Button.background", Constants.MY_BLACK);
                    UIManager.put("Button.foreground", Constants.MY_ORANGE);
                    if (outOfTime)
                        JOptionPane.showMessageDialog(null, "Skończył ci się czas, ale możesz zapisać swój wynnik", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    if (ateAllDots)
                        JOptionPane.showMessageDialog(null, "Brawo! Wygrałeś - możesz zapisać swój wynnik", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    if (dead)
                        JOptionPane.showMessageDialog(null, "Przykro mi, zostałeś zjedzony, ale możesz zapisać swój wynnik", "Game Over", JOptionPane.INFORMATION_MESSAGE);
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
//
//        int row = game.getCurrentRow();
//        int column = game.getCurrentColumn();


//        if (restX != 0.5 && restY != 0.5) {
        int cell1IndexX = (int)(game.getPucManX() - 0.5);
        int cell2IndexX = (int)(game.getPucManX() - 0.5)+1;
        int cell1IndexY = (int)(game.getPucManY() - 0.5);
        int cell2IndexY = (int)(game.getPucManY() - 0.5)+1;


        Rectangle cellRect = newGame.table.getCellRect(0, 0, false);



//        int centreX = newGame.table.ge


        int vievPacX = (int) ((fatchVievOperation.getPacXposition() * newGame.realCellSizeX) - newGame.realCellSizeX / 2);
        int vievPacY = (int) ((fatchVievOperation.getPacYposition() * newGame.realCellSizeY) - newGame.realCellSizeY / 2);
        pacmanPanel.setBounds(vievPacX, vievPacY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);

//        Rectangle cellRect = newGame.table.getCellRect(row, column, true);
//
//        int vievPacX = (int) ((fatchVievOperation.getPacXposition() * cellRect.width) - cellRect.width / 2);
//        int vievPacY = (int) ((fatchVievOperation.getPacYposition() * cellRect.height) - cellRect.height / 2);
//        pacmanPanel.setBounds(vievPacX, vievPacY, cellRect.width, cellRect.height);



//        }else {
//            Rectangle cellRect = newGame.table.getCellRect(row, column, true);
//            int cellX = cellRect.x;
//            int cellY = cellRect.y;
//            pacmanPanel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//        }


    }
    private void showGhosts(FatchVievOperation fatchVievOperation) {
        game.ghosts.forEach((id, ghost) -> {

            int vievGhostX = (int) ((ghost.getGhostX() * newGame.realCellSizeX) - newGame.realCellSizeX / 2);
            int vievGhostY = (int) ((ghost.getGhostY() * newGame.realCellSizeY) - newGame.realCellSizeY / 2);
            switch (id) {
                case 0 -> ghost0Panel.setBounds(vievGhostX, vievGhostY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);
                case 1 -> ghost1Panel.setBounds(vievGhostX, vievGhostY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);
                case 2 -> ghost2Panel.setBounds(vievGhostX, vievGhostY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);
                case 3 -> ghost3Panel.setBounds(vievGhostX, vievGhostY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);
            }


//            pacmanPanel.setBounds(vievGhostX, vievGhostY, (int) newGame.realCellSizeX, (int) newGame.realCellSizeY);





//            int row = ghost.getGhostCurrentRow();
//            int column = ghost.getGhostCurrentColumn();
//
//            Rectangle cellRect = newGame.table.getCellRect(row, column, true);
//            int cellX = cellRect.x;
//            int cellY = cellRect.y;
//
//            switch (id) {
//                case 0 -> ghost0Panel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//                case 1 -> ghost1Panel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//                case 2 -> ghost2Panel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//                case 3 -> ghost3Panel.setBounds(cellX, cellY, cellRect.width, cellRect.height);
//            }

        });


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
            setGhostImage( 69, 132);
        } else {
            if (System.currentTimeMillis() - lastImageChangeTime >= 500) {
                if (System.currentTimeMillis() % 2 == 0) {
                    setGhostImage( 6, 132);
                } else {
                    setGhostImage( 38, 132);
                }
                lastImageChangeTime = System.currentTimeMillis();
            }
        }
    }

    public void setGhostImage( int x, int y) {
        BufferedImage ghostTile = tileSet.getSubimage(x, y, 25, 25);

        if (ghost0Panel == null) {
            ghost0Panel = new ImagePanel(ghostTile);
            ghost0Panel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(ghost0Panel);
        } else {
            ghost0Panel.setImage(ghostTile);
            ghost0Panel.repaint();
        }

        if (ghost1Panel == null) {
            ghost1Panel = new ImagePanel(ghostTile);
            ghost1Panel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(ghost1Panel);
        } else {
            ghost1Panel.setImage(ghostTile);
            ghost1Panel.repaint();
        }



        if (ghost2Panel == null) {
            ghost2Panel = new ImagePanel(ghostTile);
            ghost2Panel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(ghost2Panel);
        } else {
            ghost2Panel.setImage(ghostTile);
            ghost2Panel.repaint();
        }



        if (ghost3Panel == null) {
            ghost3Panel = new ImagePanel(ghostTile);
            ghost3Panel.setBackground(new Color(0, 0, 0, 0));
            newGame.table.add(ghost3Panel);
        } else {
            ghost3Panel.setImage(ghostTile);
            ghost3Panel.repaint();
        }


    }
}

