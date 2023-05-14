package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.PacMovement;
import p2.Game;

public class PacStepOperation extends GameOperation {
    long deltaTime;
    long now;

    double pacSpeed = 0.004;


    public PacStepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {
        float restY = (game.pucManY - (int) game.pucManY);
        float restX = (game.pucManX - (int) game.pucManX);

        if (restX != 0.5 && restY != 0.5) {
            game.pucManX = (int) game.pucManX + 0.5f;
            game.pucManY = (int) game.pucManY + 0.5f;
            game.setPacMovement(game.getNextMove());
        }else {
            game.setPacMovement(game.getNextMove());
        }


        if (game.getPacMovement() == PacMovement.MOVE_RIGHT) {
            int nextXposition = (int) (game.pucManX + 1);
            int nextYposition = (int) (game.pucManY);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());
        } else if (game.getPacMovement() == PacMovement.MOVE_lEFT) {
            int nextXposition = (int) (game.pucManX - 1);
            int nextYposition = (int) (game.pucManY);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_UP) {
            int nextXposition = (int) (game.pucManX);
            int nextYposition = (int) (game.pucManY - 1);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_DOWN) {
            int nextXposition = (int) (game.pucManX);
            int nextYposition = (int) (game.pucManY + 1);
            handleEating(game, nextXposition, nextYposition);
            movePacman(game, nextXposition, nextYposition, game.getPacMovement());

        }
    }

    public void movePacman(Game game, int nextXposition, int nextYposition, PacMovement pacMovement) {
        if (game.map[nextYposition][nextXposition] != FieldValue.WALL) {
            switch (pacMovement) {
//                case MOVE_RIGHT -> game.pucManX += 1;
//                case MOVE_lEFT -> game.pucManX -= 1;
//                case MOVE_UP -> game.pucManY -= 1;
//                case MOVE_DOWN -> game.pucManY += 1;
                case MOVE_RIGHT -> game.pucManX += deltaTime * pacSpeed;
                case MOVE_lEFT -> game.pucManX -= deltaTime * pacSpeed;
                case MOVE_UP -> game.pucManY -= deltaTime * pacSpeed;
                case MOVE_DOWN -> game.pucManY += deltaTime * pacSpeed;
            }
        } else {
            game.pucManX = (int) game.pucManX + 0.5f;
            game.pucManY = (int) game.pucManY + 0.5f;
            game.setPacMovement(PacMovement.STAY);
        }
    }

    public void handleEating(Game game, int nextXposition, int nextYposition) {
        if (game.map[nextYposition][nextXposition] == FieldValue.DOT) {
            game.incPlayerScore(1);
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
            System.out.println();
        } else if (game.map[nextYposition][nextXposition] == FieldValue.BIG_DOT) {
            game.incPlayerScore(10);
            game.map[nextYposition][nextXposition] = FieldValue.SPACE;
            System.out.println();
        }
    }


}

