package p2;

import p2.GUI.NewGame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();


//        SwingUtilities.invokeLater(() -> new MainMenu(game));
//        SwingUtilities.invokeLater(() -> new ChooseBoardSize(game));
//        SwingUtilities.invokeLater(() -> new NewGame(game,40));
//        SwingUtilities.invokeLater(() -> new NewGame(game,40));
        SwingUtilities.invokeLater(() -> {// do przeniesienia we właściwe miejsce czyli do chooseBoardSize
            NewGame newGame = new NewGame(game,40);

            Task_Viev task_viev = new Task_Viev(newGame,game);
            Thread viev = new Thread(task_viev);
            viev.start();

            Task_Game task_game = new Task_Game(game);
            Thread gameThread = new Thread(task_game);
            gameThread.start();


        });







    }


}
