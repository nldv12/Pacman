package p2.Operations;

import p2.Enums.FieldValue;
import p2.Game;

public class FatchVievOperation extends GameOperation{

    private float pacXposition;
    private float pacYposition;

    public FieldValue[][] map;

    @Override
    public void doOperation(Game game) {
        pacXposition = game.getPucManX();
        pacYposition = game.getPucManY();
        map = game.map.clone();

    }

    public float getPacXposition() {
        return pacXposition;
    }
    public float getPacYposition() {
        return pacYposition;
    }
}
