package p2;

import p2.GUI.NewGame;
import p2.Operations.FatchVievOperation;

public class Task_Viev implements Runnable{

    Game game;
    NewGame newGame;

    public Task_Viev(NewGame newGame ,Game game){
        this.game = game;
        this.newGame = newGame;

    }
    @Override
    public void run() {


        try {
            while (true){
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.performOperation(fatchVievOperation);
                newGame.pacmanPanel.setBounds(fatchVievOperation.getPacXposition(),fatchVievOperation.getPacYposition(),32,32);


                Thread.sleep(100);
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
