package p2;

import p2.Enums.GhostMovement;
import p2.FRAMES.NewGame;
import p2.Operations.*;

import java.util.Iterator;
import java.util.Map;

public class Task_Game implements Runnable {

    Game game;
    boolean pased5Seconds;
    long before20Seconds = 0;


    public Task_Game(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            long prevTime = System.currentTimeMillis();
            long before5Seconds = System.currentTimeMillis();

            while (true) {
                before20Seconds = game.getBefore10Seconds();
                long now = System.currentTimeMillis();
//                long deltaTime = now - prevTime;
                long deltaTime = 109;

                if (now - before5Seconds > 5000) {
                    pased5Seconds = true;
                    before5Seconds = now;
                } else
                    pased5Seconds = false;
                run10SecTimer(now);
                movePacman(deltaTime);
                moveGhosts(deltaTime);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void run10SecTimer(long now) {
        if (game.isSpeedHigher()) {
            if (now - game.getBefore10Seconds() >= 10000) {
                game.setSpeedHigher(false);
                game.setPacSpeed(0.005);
                game.setIspowerUp(false);
            }
        }
        if (game.isGhostsFrozen()) {
            if (now - game.getBefore10Seconds() >= 10000) {
                game.ghosts.forEach((id, ghost) -> ghost.setGhostMove(GhostMovement.MOVE));
                game.setGhostsFrozen(false);
                game.setIspowerUp(false);
            }
        }



    }

    private void movePacman(long deltaTime) {
        PacStepOperation pacStepOperation = new PacStepOperation(deltaTime);
        game.performOperation(pacStepOperation);
    }

    private void moveGhosts(long deltaTime) {
        game.ghosts.forEach((id, ghost) -> {
            GhostStepOperation ghostStepOperation = new GhostStepOperation(id, deltaTime, pased5Seconds);
            game.performOperation(ghostStepOperation);
        });
        if (game.test !=9){
            game.ghosts.remove(game.test);
            game.test = 9;
        }



    }
}
