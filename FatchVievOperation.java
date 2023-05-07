package p2;

public class FatchVievOperation extends GameOperation{

    int pacXposition;
    @Override
    public void doOperation(Game game) {
        pacXposition = (int)game.pucManX;


    }
}
