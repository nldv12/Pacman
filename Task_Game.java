package p2;

import p2.Operations.PacStepOperation;

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
//                long deltaTime = now - prevTime;
                long deltaTime = 100;

                PacStepOperation pacStepOperation = new PacStepOperation(deltaTime, now);
                game.performOperation(pacStepOperation);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
