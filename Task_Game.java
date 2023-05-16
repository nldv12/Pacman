package p2;

import p2.Operations.*;

public class Task_Game implements Runnable {

    Game game;
    boolean pased5Seconds;

    public Task_Game(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        try {
            long prevTime = System.currentTimeMillis();
            long before5Seconds = System.currentTimeMillis();

            while (true) {
                long now = System.currentTimeMillis();
                long deltaTime = now - prevTime;
//                long deltaTime = 300;
                PacStepOperation pacStepOperation = new PacStepOperation(deltaTime);
                game.performOperation(pacStepOperation);

                if (now-before5Seconds > 5000) {
                    pased5Seconds=true;
                    before5Seconds = now;
                }else
                    pased5Seconds=false;

                GhostStepOperation ghost0StepOperation = new GhostStepOperation(0,deltaTime, pased5Seconds);
                game.performOperation(ghost0StepOperation);
                GhostStepOperation ghost1StepOperation = new GhostStepOperation(1,deltaTime, pased5Seconds);
                game.performOperation(ghost1StepOperation);
                GhostStepOperation ghost2StepOperation = new GhostStepOperation(2,deltaTime, pased5Seconds);
                game.performOperation(ghost2StepOperation);
                GhostStepOperation ghost3StepOperation = new GhostStepOperation(3,deltaTime, pased5Seconds);
                game.performOperation(ghost3StepOperation);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
