package p2;

import p2.Enums.PacMovement;
import p2.Game;
import p2.Operations.StepOperation;

public class Task_Game implements Runnable {

    Game game;

    public Task_Game(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        try {
            long prevTime = System.currentTimeMillis();

            while (true) {
                long now = System.currentTimeMillis();
                long deltaTime = now - prevTime;

                StepOperation stepOperation = new StepOperation(deltaTime, now);
                game.performOperation(stepOperation);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
