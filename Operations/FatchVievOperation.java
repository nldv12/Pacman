package p2.Operations;

import p2.Game;

public class FatchVievOperation extends GameOperation{

    private int pacXposition;
    private int pacYposition;
    @Override
    public void doOperation(Game game) {
        pacXposition = (int)game.pucManX;
        pacYposition = (int)game.pucManY;
    }

    public int getPacXposition() {
        return pacXposition;
    }
    public int getPacYposition() {
        return pacYposition;
    }
}
