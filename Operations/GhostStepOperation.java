package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Enums.PacMovement;
import p2.Game;

import java.util.Random;

public class GhostStepOperation extends GameOperation {
    long deltaTime;
    long now;
    int index;
    double ghostSpeed = 0.004;

    public GhostStepOperation(int index, long deltaTime, long now) {
        this.index = index;
        this.deltaTime = deltaTime;
        this.now = now;
    }

    @Override
    public void doOperation(Game game) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1;
        
            switch (randomNumber) {
                case 1 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_UP);
                case 2 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_DOWN);
                case 3 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_lEFT);
                case 4 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_RIGHT);
            }


        float restX = (game.ghosts.get(index).getGhostX() - (int) game.ghosts.get(index).getGhostX());
        float restY = (game.ghosts.get(index).getGhostY() - (int) game.ghosts.get(index).getGhostY());

        if (restX != 0.5 && restY != 0.5) {
            finishMovement(game);
        }

        if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_RIGHT) {
            int nextXposition = (int) (game.ghosts.get(index).getGhostX() + 1);
            int nextYposition = (int) (game.ghosts.get(index).getGhostY());

            handleEating(game, nextXposition, nextYposition);
            moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());
        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_lEFT) {
            int nextXposition = (int) (game.ghosts.get(index).getGhostX() - 1);
            int nextYposition = (int) (game.ghosts.get(index).getGhostY());
            handleEating(game, nextXposition, nextYposition);
            moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());

        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_UP) {
            int nextXposition = (int) (game.ghosts.get(index).getGhostX());
            int nextYposition = (int) (game.ghosts.get(index).getGhostY() - 1);
            handleEating(game, nextXposition, nextYposition);
            moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());

        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_DOWN) {
            int nextXposition = (int) (game.ghosts.get(index).getGhostX());
            int nextYposition = (int) (game.ghosts.get(index).getGhostY()+1);
            handleEating(game, nextXposition, nextYposition);
            moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());
        }
    }

    public void moveGhost(Game game, int nextXposition, int nextYposition, GhostMovement ghostMovement) {
        if (game.map[nextYposition][nextXposition] != FieldValue.WALL) {
            switch (ghostMovement) {
//                case MOVE_RIGHT -> game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() + 1);
//                case MOVE_lEFT -> game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() - 1);
//                case MOVE_UP -> game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() - 1);
//                case MOVE_DOWN -> game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() + 1);

                case MOVE_RIGHT -> game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() + (float) (deltaTime * ghostSpeed));
                case MOVE_lEFT -> game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() - (float) (deltaTime * ghostSpeed));
                case MOVE_UP -> game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() - (float) (deltaTime * ghostSpeed));
                case MOVE_DOWN -> game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() + (float) (deltaTime * ghostSpeed));
            }
//
        } else {
//            finishMovement(game);
        }
    }

    public void finishMovement(Game game) {
        game.ghosts.get(index).setGhostX( (int) game.ghosts.get(index).getGhostX() +0.5f );
        game.ghosts.get(index).setGhostY( (int) game.ghosts.get(index).getGhostY() +0.5f );
    }

    public void handleEating(Game game, int nextXposition, int nextYposition) {
        int pacmanRow = game.getCurrentRow();
        int pacmanColumn = game.getCurrentColumn();
        if (nextYposition == pacmanRow && nextXposition == pacmanColumn)
            game.setPacmanDead(true);

    }


}

