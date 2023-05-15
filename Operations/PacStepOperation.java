package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.PacMovement;
import p2.Game;

public class PacStepOperation extends GameOperation {
    long deltaTime;
    long now;

    public PacStepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {


        game.setPacMovement(game.getNextMove());

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
            game.setCurrentRow(nextRowNumber);
            game.setCurrentColumn(nextColumnNumber);
        } else {
            game.setPacMovement(PacMovement.STAY);
        }
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

