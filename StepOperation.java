package p2;

public class StepOperation extends GameOperation{
    @Override
    public void doOperation(Game game) {
        if (game.pacMovement == PacMovement.MOVE){
            game.pucManX++;
        }
    }
}
