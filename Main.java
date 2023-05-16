package p2;

import p2.FRAMES.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int boardSize = 15;
        Game game = new Game(boardSize);
//        SwingUtilities.invokeLater(() -> new MainMenu(game));
//        SwingUtilities.invokeLater(() -> new HighScores(game));
//        SwingUtilities.invokeLater(() -> new ChoosePlayerName(game,50));
//        SwingUtilities.invokeLater(() -> new ChooseBoardSize());
        SwingUtilities.invokeLater(() -> {// do usunięcia (ale najpierw zamień w ChooseBoardSize)
            NewGame newGame = new NewGame(game,boardSize,300);
            Task_Viev task_viev = new Task_Viev(newGame,game);
            Thread viev = new Thread(task_viev);
            viev.start();
            Task_Game task_game = new Task_Game(game);
            Thread gameThread = new Thread(task_game);
            gameThread.start();
        });

    }


}
