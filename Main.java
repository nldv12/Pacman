package p2;

import p2.FRAMES.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int boardSize = 20;
        Game game = new Game(boardSize,300);
//        SwingUtilities.invokeLater(() -> new MainMenu());
        SwingUtilities.invokeLater(() -> new HighScores());
//        SwingUtilities.invokeLater(() -> new ChoosePlayerName(game,50));
//        SwingUtilities.invokeLater(() -> new ChooseBoardSize());
//        SwingUtilities.invokeLater(() -> {// do usunięcia (ale najpierw zamień w ChooseBoardSize)
//            NewGame newGame = new NewGame(game,boardSize);
//            Task_Viev task_viev = new Task_Viev(newGame,game);
//            Thread viev = new Thread(task_viev);
//            viev.start();
//            Task_Game task_game = new Task_Game(game);
//            Thread gameThread = new Thread(task_game);
//            gameThread.start();
//        });

    }


}
