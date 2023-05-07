package p2;

public class MovePacOperation extends GameOperation{
    @Override
    public void doOperation(Game game) {
        game.pacMovement = PacMovement.MOVE;
    }
}
