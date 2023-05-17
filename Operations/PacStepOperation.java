package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Enums.PacMovement;
import p2.FRAMES.NewGame;
import p2.Game;
import p2.ImagePanels.LivesPanel;

public class PacStepOperation extends GameOperation {
    long deltaTime;

    double pacSpeed ;



    public PacStepOperation(long deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public void doOperation(Game game) {
        this.pacSpeed = game.getPacSpeed();


        float restY = (game.getPucManY() - (int) game.getPucManY());
        float restX = (game.getPucManX() - (int) game.getPucManX());

        if (restX != 0.5 && restY != 0.5) {
            finishMovement(game);
            game.setPacMovement(game.getNextMove());
        } else {
            game.setPacMovement(game.getNextMove());
        }


        if (game.getPacMovement() == PacMovement.MOVE_RIGHT) {
            int nextXposition = (int) (game.getPucManX() + 1);
            int nextYposition = (int) (game.getPucManY());
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());
        } else if (game.getPacMovement() == PacMovement.MOVE_lEFT) {
            int nextXposition = (int) (game.getPucManX() - 1);
            int nextYposition = (int) (game.getPucManY());
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_UP) {
            int nextXposition = (int) (game.getPucManX());
            int nextYposition = (int) (game.getPucManY() - 1);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_DOWN) {
            int nextXposition = (int) (game.getPucManX());
            int nextYposition = (int) (game.getPucManY() + 1);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        }
    }

    public void movePacman(Game game, int nextXposition, int nextYposition, PacMovement pacMovement) {
        if (game.map[nextYposition][nextXposition] != FieldValue.WALL) {
            switch (pacMovement) {
//                case MOVE_RIGHT -> game.setPucManX(game.getPucManX() + 1);
//                case MOVE_lEFT -> game.setPucManX(game.getPucManX() - 1);
//                case MOVE_UP -> game.setPucManY(game.getPucManY() - 1);
//                case MOVE_DOWN -> game.setPucManY(game.getPucManY() + 1);

//
                case MOVE_RIGHT -> game.setPucManX(game.getPucManX() + (float) (deltaTime * pacSpeed));
                case MOVE_lEFT -> game.setPucManX(game.getPucManX() - (float) (deltaTime * pacSpeed));
                case MOVE_UP -> game.setPucManY(game.getPucManY() - (float) (deltaTime * pacSpeed));
                case MOVE_DOWN -> game.setPucManY(game.getPucManY() + (float) (deltaTime * pacSpeed));
            }
//            game.setCurrentRow(nextYposition);
//            game.setCurrentColumn(nextXposition);
        } else {
            finishMovement(game);
            game.setPacMovement(PacMovement.STAY);
        }
    }

    public void finishMovement(Game game) {
        game.setPucManX((int) game.getPucManX() + 0.5f);
        game.setPucManY((int) game.getPucManY() + 0.5f);
    }



    public void handleEating(Game game, int nextXposition, int nextYposition) {
        handleMeetingGhost(game);
        if (game.map[nextYposition][nextXposition] == FieldValue.DOT) {
            game.incPlayerScore(1);
            game.decDotCount();
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        } else if (game.map[nextYposition][nextXposition] == FieldValue.BIG_DOT) {
            game.incPlayerScore(10);
            game.decBigDotCount();
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }
        else if (game.map[nextYposition][nextXposition] == FieldValue.CHERRY) {
            game.setSpeedHigher(true);
            game.setBefore10Seconds(System.currentTimeMillis());
            game.setPacSpeed(0.009);
            game.setIspowerUp(true);
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }
        else if (game.map[nextYposition][nextXposition] == FieldValue.STRAWBERRY) {
            game.incLivesCount();
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }
        else if (game.map[nextYposition][nextXposition] == FieldValue.ORANGE) {
            game.finishGhostMovement();
            game.setBefore10Seconds(System.currentTimeMillis());
            game.setGhostsFrozen(true);
            game.setIspowerUp(true);
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }
        else if (game.map[nextYposition][nextXposition] == FieldValue.APPLE) {
            game.incPlayerScore(20);
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }
        else if (game.map[nextYposition][nextXposition] == FieldValue.TIME) {
            game.add20secondsToCountdown();
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
        }

    }

    private void handleMeetingGhost(Game game) {
        game.ghosts.forEach((id, ghost) -> {
            float ghostX = ghost.getGhostX();
            float ghostY = ghost.getGhostY();

            float pacmanX = game.getPucManX();
            float pacmanY = game.getPucManY();

            if ((int)ghostX == (int)pacmanX && (int)ghostY == (int)pacmanY){
                if (game.isPowerUp()) {
                   game.setGhostToBeRemoved(id);
                }
//                 else
//                   game.setPacmanDead(true);
            }


        });

    }


}

