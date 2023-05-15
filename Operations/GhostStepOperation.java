package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Game;

import java.util.Random;

public class GhostStepOperation extends GameOperation {
    long deltaTime;
    long now;
    
    int index;



    public GhostStepOperation(int index,long deltaTime, long now) {
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
            

        if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_RIGHT) {
            int nextRowNumber = game.ghosts.get(index).getGhostCurrentRow();
            int nextColumnNumber = game.ghosts.get(index).getGhostCurrentColumn()+1;
            handleEating(game, nextRowNumber, nextColumnNumber);
            moveGhost(game, nextRowNumber, nextColumnNumber, game.ghosts.get(index).getGhostMove());
        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_lEFT) {
            int nextRowNumber = game.ghosts.get(index).getGhostCurrentRow();
            int nextColumnNumber = game.ghosts.get(index).getGhostCurrentColumn()-1;
            handleEating(game, nextRowNumber, nextColumnNumber);
            moveGhost(game, nextRowNumber, nextColumnNumber, game.ghosts.get(index).getGhostMove());

        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_UP) {
            int nextRowNumber = game.ghosts.get(index).getGhostCurrentRow()-1;
            int nextColumnNumber = game.ghosts.get(index).getGhostCurrentColumn();
            handleEating(game, nextRowNumber, nextColumnNumber);
            moveGhost(game, nextRowNumber, nextColumnNumber, game.ghosts.get(index).getGhostMove());

        } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_DOWN) {
            int nextRowNumber = game.ghosts.get(index).getGhostCurrentRow()+1;
            int nextColumnNumber = game.ghosts.get(index).getGhostCurrentColumn();
            handleEating(game, nextRowNumber, nextColumnNumber);
            moveGhost(game, nextRowNumber, nextColumnNumber, game.ghosts.get(index).getGhostMove());
        }
    }

    public void moveGhost(Game game, int nextRowNumber, int nextColumnNumber, GhostMovement ghostMovement) {
        if (game.map[nextRowNumber][nextColumnNumber] != FieldValue.WALL) {
                game.ghosts.get(index).setGhostCurrentRow(nextRowNumber);
                game.ghosts.get(index).setGhostCurrentColumn(nextColumnNumber);
        }
    }

    public void handleEating(Game game, int nextRowNumber, int nextColumnNumber) {
        int pacmanRow = game.getCurrentRow();
        int pacmanColumn = game.getCurrentColumn();
        if (nextRowNumber == pacmanRow && nextColumnNumber == pacmanColumn)
            game.setPacmanDead(true);

    }


}

