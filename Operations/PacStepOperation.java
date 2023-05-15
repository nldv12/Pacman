package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.PacMovement;
import p2.Game;

public class PacStepOperation extends GameOperation {
    long deltaTime;
    long now;

    double pacSpeed = 0.005;


    public PacStepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {
        float restY = (game.getPucManY() - (int) game.getPucManY());
        float restX = (game.getPucManX() - (int) game.getPucManX());

        if (restX != 0.5 && restY != 0.5) {
            finishMovement(game);
            game.setPacMovement(game.getNextMove());
        } else {
            game.setPacMovement(game.getNextMove());
        }


        if (game.getPacMovement() == PacMovement.MOVE_RIGHT) {
            int nextRowNumber = game.getCurrentRow();
            int nextColumnNumber = game.getCurrentColumn()+1;
            handleEating(game, nextRowNumber, nextColumnNumber);
            movePacman(game, nextRowNumber, nextColumnNumber, game.getPacMovement());
        } else if (game.getPacMovement() == PacMovement.MOVE_lEFT) {
            int nextRowNumber = game.getCurrentRow();
            int nextColumnNumber = game.getCurrentColumn()-1;
            handleEating(game, nextRowNumber, nextColumnNumber);
            movePacman(game, nextRowNumber, nextColumnNumber, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_UP) {
            int nextRowNumber = game.getCurrentRow()-1;
            int nextColumnNumber = game.getCurrentColumn();
            handleEating(game, nextRowNumber, nextColumnNumber);
            movePacman(game, nextRowNumber, nextColumnNumber, game.getPacMovement());

        } else if (game.getPacMovement() == PacMovement.MOVE_DOWN) {
            int nextRowNumber = game.getCurrentRow()+1;
            int nextColumnNumber = game.getCurrentColumn();
            handleEating(game, nextRowNumber, nextColumnNumber);
            movePacman(game, nextRowNumber, nextColumnNumber, game.getPacMovement());
        }
    }

    public void movePacman(Game game, int nextRowNumber, int nextColumnNumber, PacMovement pacMovement) {
        if (game.map[nextRowNumber][nextColumnNumber] != FieldValue.WALL) {
            switch (pacMovement) {
                case MOVE_RIGHT -> game.setPucManX(game.getPucManX() + 1);
                case MOVE_lEFT -> game.setPucManX(game.getPucManX() - 1);
                case MOVE_UP -> game.setPucManY(game.getPucManY() - 1);
                case MOVE_DOWN -> game.setPucManY(game.getPucManY() + 1);


//                case MOVE_RIGHT -> game.setPucManX(game.getPucManX() + (float) (deltaTime * pacSpeed));
//                case MOVE_lEFT -> game.setPucManX(game.getPucManX() - (float) (deltaTime * pacSpeed));
//                case MOVE_UP -> game.setPucManY(game.getPucManY() - (float) (deltaTime * pacSpeed));
//                case MOVE_DOWN -> game.setPucManY(game.getPucManY() + (float) (deltaTime * pacSpeed));
            }
            int row = game.getCurrentRow();
            int column = game.getCurrentColumn();
            game.setCurrentRow(nextRowNumber);
            game.setCurrentColumn(nextColumnNumber);
            row = game.getCurrentRow();
            column = game.getCurrentColumn();
            System.out.println();
        } else {
            finishMovement(game);
            game.setPacMovement(PacMovement.STAY);
        }
    }

    public void finishMovement(Game game) {
        game.setPucManX((int) game.getPucManX() + 0.5f);
        game.setPucManY((int) game.getPucManY() + 0.5f);
    }

    public void handleEating(Game game, int nextRowNumber, int nextColumnNumber) {
        if (game.map[nextRowNumber][nextColumnNumber] == FieldValue.DOT) {
            game.incPlayerScore(1);
            game.decDotCount();
            game.map[nextRowNumber][nextColumnNumber] = FieldValue.SPACE;
        } else if (game.map[nextRowNumber][nextColumnNumber] == FieldValue.BIG_DOT) {
            game.incPlayerScore(10);
            game.decBigDotCount();
            game.map[nextRowNumber][nextColumnNumber] = FieldValue.SPACE;
        }
    }


}

