package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Enums.PacMovement;
import p2.Game;

import java.util.Random;

public class GhostStepOperation extends GameOperation {
    long deltaTime;
    long now;

    public GhostStepOperation(long deltaTime, long now) {
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1;

        if (game.ghostMovement!=GhostMovement.STAY){
            switch (randomNumber) {
                case 1 -> game.ghostMovement = GhostMovement.MOVE_UP;
                case 2 -> game.ghostMovement = GhostMovement.MOVE_DOWN;
                case 3 -> game.ghostMovement = GhostMovement.MOVE_lEFT;
                case 4 -> game.ghostMovement = GhostMovement.MOVE_RIGHT;
            }
        }

        if (game.ghostMovement == GhostMovement.MOVE_RIGHT) {
            int nextPosition = (int) (game.ghostX + 0.5);
            if (game.map[(int)game.ghostY][nextPosition] != FieldValue.WALL){
                game.ghostX += deltaTime * 0.004;
            }
        } else if (game.ghostMovement == GhostMovement.MOVE_lEFT) {
            int nextPosition = (int) (game.ghostX - 0.5);
            if (game.map[(int)game.ghostY][nextPosition] != FieldValue.WALL){
                game.ghostX -= deltaTime * 0.004;
            }
        } else if (game.ghostMovement == GhostMovement.MOVE_UP) {
            int nextPosition = (int) (game.ghostY - 0.5);
            if (game.map[nextPosition][(int)game.ghostX] != FieldValue.WALL){
                game.ghostY -= deltaTime * 0.004;
            }
        } else if (game.ghostMovement == GhostMovement.MOVE_DOWN){
            int nextPosition = (int) (game.ghostY + 0.5);
            if (game.map[nextPosition][(int)game.ghostX] != FieldValue.WALL){
                game.ghostY += deltaTime * 0.004;
            }
        }

    }
}
