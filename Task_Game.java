package p2;

import p2.Operations.*;

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
//                long deltaTime = 300;
                PacStepOperation pacStepOperation = new PacStepOperation(deltaTime, now);
                game.performOperation(pacStepOperation);
//
                GhostStepOperation ghost0StepOperation = new GhostStepOperation(0,deltaTime, now);
                game.performOperation(ghost0StepOperation);
                GhostStepOperation ghost1StepOperation = new GhostStepOperation(1,deltaTime, now);
                game.performOperation(ghost1StepOperation);
                GhostStepOperation ghost2StepOperation = new GhostStepOperation(2,deltaTime, now);
                game.performOperation(ghost2StepOperation);
                GhostStepOperation ghost3StepOperation = new GhostStepOperation(3,deltaTime, now);
                game.performOperation(ghost3StepOperation);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
