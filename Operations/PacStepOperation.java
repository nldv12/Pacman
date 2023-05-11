package p2.Operations;

import p2.Enums.FieldValue;
import p2.Game;
import p2.Enums.PacMovement;

public class PacStepOperation extends GameOperation {
    long deltaTime;
    long now;

    public PacStepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {
        if (game.pacMovement == PacMovement.MOVE_RIGHT) {
            int nextPosition = (int) (game.pucManX + 0.5);
            if (game.map[(int)game.pucManY][nextPosition] == FieldValue.DOT){
                game.incPlayerScore(1);
                game.map[(int)game.pucManY][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManY][nextPosition] == FieldValue.BIG_DOT){
                game.incPlayerScore(10);
                game.map[(int)game.pucManY][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManY][nextPosition] != FieldValue.WALL){
                game.pucManX += deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_lEFT) {
            int nextPosition = (int) (game.pucManX - 0.5);
            if (game.map[(int)game.pucManY][nextPosition] == FieldValue.DOT){
                game.incPlayerScore(1);
                game.map[(int)game.pucManY][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManY][nextPosition] == FieldValue.BIG_DOT){
                game.incPlayerScore(10);
                game.map[(int)game.pucManY][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManY][nextPosition] != FieldValue.WALL){
                game.pucManX -= deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_UP) {
            int nextPosition = (int) (game.pucManY - 0.5);
            if (game.map[(int)game.pucManX][nextPosition] == FieldValue.DOT){
                game.incPlayerScore(1);
                game.map[(int)game.pucManX][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManX][nextPosition] == FieldValue.BIG_DOT){
                game.incPlayerScore(10);
                game.map[(int)game.pucManX][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[nextPosition][(int)game.pucManX] != FieldValue.WALL){
                game.pucManY -= deltaTime * 0.004;
            }
        } else if (game.pacMovement == PacMovement.MOVE_DOWN) {
            int nextPosition = (int) (game.pucManY + 0.5);
            if (game.map[(int)game.pucManX][nextPosition] == FieldValue.DOT){
                game.incPlayerScore(1);
                game.map[(int)game.pucManX][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[(int)game.pucManX][nextPosition] == FieldValue.BIG_DOT){
                game.incPlayerScore(10);
                game.map[(int)game.pucManX][nextPosition] = FieldValue.SPACE;
            }
            else if (game.map[nextPosition][(int)game.pucManX] != FieldValue.WALL){
                game.pucManY += deltaTime * 0.004;
            }
        }

    }
}
