package p2;

public class Game {

    float pucManX;
    float pucManY;
    PacMovement pacMovement;
    public synchronized void prformOperation(GameOperation operation){
        operation.doOperation(this);
    }




}
