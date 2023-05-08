package p2;

import p2.Enums.PacMovement;
import p2.Game;
import p2.Operations.StepOperation;

public class Task_Game implements Runnable {

    Game game;

    Task_Game(Game game){
        this.game = game;
    }

    @Override
    public void run() {

        try {
            while (true) {
                StepOperation stepOperation = new StepOperation();
                game.performOperation(stepOperation);

                Thread.sleep(10);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
