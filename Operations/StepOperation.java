package p2.Operations;

import p2.Enums.FieldValue;
import p2.Game;
import p2.Enums.PacMovement;

public class StepOperation extends GameOperation {
    long deltaTime;
    long now;

    public StepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {
        if (game.pacMovement == PacMovement.MOVE_RIGHT) {
            int checkWall = (int) (game.pucManX + 0.5);
            if (game.map[(int)game.pucManY][checkWall] != FieldValue.WALL){
                game.pucManX += deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_lEFT) {
            int checkWall = (int) (game.pucManX - 0.5);
            if (game.map[(int)game.pucManY][checkWall] != FieldValue.WALL){
                game.pucManX -= deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_UP) {
            int checkWall = (int) (game.pucManY - 0.5);
            if (game.map[checkWall][(int)game.pucManX] != FieldValue.WALL){
                game.pucManY -= deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_DOWN) {
            int checkWall = (int) (game.pucManY + 0.5);
            if (game.map[checkWall][(int)game.pucManX] != FieldValue.WALL){
                game.pucManY += deltaTime * 0.004;
            }
        }

    }
}
