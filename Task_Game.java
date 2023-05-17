package p2;

import p2.Enums.GhostMovement;
import p2.Operations.*;

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
                before20Seconds = game.getTimeBeforePowerUp();
                long now = System.currentTimeMillis();
                long deltaTime = now - prevTime;

                if (now - before5Seconds > 5000) {
                    pased5Seconds = true;
                    before5Seconds = now;
                } else{
                    pased5Seconds = false;
                }
                runTimer(now);
                movePacman(deltaTime);
                moveGhosts(deltaTime);

                prevTime = now;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void runTimer(long now) {
        if (game.isSpeedHigher()) {
            game.addPowerUpName("  Super Speed");
            if (now - game.getTimeBeforePowerUp() >= 10000) {
                game.removeShortCountdownPanel();
                game.addPowerUpName("");
                game.setSpeedHigher(false);
                game.setPacSpeed(0.005);
                game.setIspowerUp(false);
            }
        }
        if (game.isGhostsFrozen()) {
            game.addPowerUpName("  Ghosts Frozen");
            if (now - game.getTimeBeforePowerUp() >= 10000) {
                game.removeShortCountdownPanel();
                game.addPowerUpName("");
                game.ghosts.forEach((id, ghost) -> ghost.setGhostMove(GhostMovement.MOVE));
                game.setGhostsFrozen(false);
                game.setIspowerUp(false);
            }
        }
        if (game.ateApple()){
            game.addPowerUpName("  + 20 points!");
            if (now - game.getTimeBeforePowerUp() >= 1000) {
                game.addPowerUpName("");
                game.setAteApple(false);
            }
        }
        if (game.ateStrawberry()){
            game.addPowerUpName("  + 1 extra life");
            if (now - game.getTimeBeforePowerUp() >= 1000) {
                game.addPowerUpName("");
                game.setAteStrawberry(false);
            }
        }
        if (game.ateTime()){
            game.addPowerUpName("  + 20 seconds");
            if (now - game.getTimeBeforePowerUp() >= 1000) {
                game.addPowerUpName("");
                game.setAteTime(false);
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
        if (game.getGhostToBeRemoved() !=999){
            game.ghosts.remove(game.getGhostToBeRemoved());
            game.setGhostToBeRemoved(999);
        }
    }
}
