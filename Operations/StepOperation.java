package p2.Operations;

import p2.Game;
import p2.Enums.PacMovement;

public class StepOperation extends GameOperation{

    @Override
    public void doOperation(Game game) {
        if (game.pacMovement == PacMovement.MOVE_RIGHT){
            game.pucManX++;
        }
        else if (game.pacMovement == PacMovement.MOVE_lEFT) {
            game.pucManX--;
        }
        else if (game.pacMovement == PacMovement.MOVE_UP) {
            game.pucManY--;
        }
        else if (game.pacMovement == PacMovement.MOVE_DOWN) {
            game.pucManY++;
        }

    }
}
