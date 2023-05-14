package p2.Operations;

import p2.Game;
import p2.Enums.PacMovement;

public class MovePacOperation extends GameOperation{

    PacMovement direction;
    public MovePacOperation(PacMovement direction) {
        this.direction = direction;
    }

    @Override
    public void doOperation(Game game) {
        game.setNextMove(direction);
    }
}
