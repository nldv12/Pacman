package p2.Operations;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Game;

import java.util.Random;

public class GhostStepOperation extends GameOperation {
    int index;
    long deltaTime;
    boolean pased5Seconds;
    double ghostSpeed = 0.004;

    public GhostStepOperation(int index, long deltaTime, boolean pased5Seconds) {
        this.index = index;
        this.deltaTime = deltaTime;
        this.pased5Seconds = pased5Seconds;
    }

    @Override
    public void doOperation(Game game) {
        if (game.ghosts.get(index).getGhostMove() == null)
            game.ghosts.get(index).setGhostMove(GhostMovement.MOVE);


        if (game.ghosts.get(index).getGhostMove() != GhostMovement.STAY) {
            if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE||pased5Seconds) {
                Random rand = new Random();
                int randomNumber = rand.nextInt(4) + 1;

                switch (randomNumber) {
                    case 1 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_UP);
                    case 2 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_DOWN);
                    case 3 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_lEFT);
                    case 4 -> game.ghosts.get(index).setGhostMove(GhostMovement.MOVE_RIGHT);
                }
            }


            float restX = (game.ghosts.get(index).getGhostX() - (int) game.ghosts.get(index).getGhostX());
            float restY = (game.ghosts.get(index).getGhostY() - (int) game.ghosts.get(index).getGhostY());

            if (restX != 0.5 || restY != 0.5) {
                finishMovement(game);
            }

            if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_RIGHT) {
                int nextXposition = (int) (game.ghosts.get(index).getGhostX() + 1);
                int nextYposition = (int) (game.ghosts.get(index).getGhostY());
                handleDrop(game, nextXposition - 1, nextYposition);
                moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());
            } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_lEFT) {
                int nextXposition = (int) (game.ghosts.get(index).getGhostX() - 1);
                int nextYposition = (int) (game.ghosts.get(index).getGhostY());
                handleDrop(game, nextXposition + 1, nextYposition);
                moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());

            } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_UP) {
                int nextXposition = (int) (game.ghosts.get(index).getGhostX());
                int nextYposition = (int) (game.ghosts.get(index).getGhostY() - 1);
                handleDrop(game, nextXposition, nextYposition + 1);
                moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());

            } else if (game.ghosts.get(index).getGhostMove() == GhostMovement.MOVE_DOWN) {
                int nextXposition = (int) (game.ghosts.get(index).getGhostX());
                int nextYposition = (int) (game.ghosts.get(index).getGhostY() + 1);
                handleDrop(game, nextXposition, nextYposition - 1);
                moveGhost(game, nextXposition, nextYposition, game.ghosts.get(index).getGhostMove());
            }
        }
    }

    public void moveGhost(Game game, int nextXposition, int nextYposition, GhostMovement ghostMovement) {
        if (game.map[nextYposition][nextXposition] != FieldValue.WALL) {
            switch (ghostMovement) {

                case MOVE_RIGHT ->
                        game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() + (float) (deltaTime * ghostSpeed));
                case MOVE_lEFT ->
                        game.ghosts.get(index).setGhostX(game.ghosts.get(index).getGhostX() - (float) (deltaTime * ghostSpeed));
                case MOVE_UP ->
                        game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() - (float) (deltaTime * ghostSpeed));
                case MOVE_DOWN ->
                        game.ghosts.get(index).setGhostY(game.ghosts.get(index).getGhostY() + (float) (deltaTime * ghostSpeed));
            }
        } else {
            game.ghosts.get(index).setGhostMove(GhostMovement.MOVE);
        }
    }

    public void finishMovement(Game game) {
        GhostMovement direction = game.ghosts.get(index).getGhostMove();
        if (direction==GhostMovement.MOVE_UP)
            game.ghosts.get(index).setGhostX((int) game.ghosts.get(index).getGhostX() + 0.5f);
        if (direction==GhostMovement.MOVE_DOWN)
            game.ghosts.get(index).setGhostX((int) game.ghosts.get(index).getGhostX() + 0.5f);
        if (direction==GhostMovement.MOVE_lEFT)
            game.ghosts.get(index).setGhostY((int) game.ghosts.get(index).getGhostY() + 0.5f);
        if (direction==GhostMovement.MOVE_RIGHT)
            game.ghosts.get(index).setGhostY((int) game.ghosts.get(index).getGhostY() + 0.5f);
    }
    public void handleDrop(Game game, int currentXPossition, int currentYposition) {
        if (pased5Seconds) {
            Random random = new Random();
            int randomProbability = random.nextInt(100);
            if (randomProbability < 99) {
                int powerUp = random.nextInt(5) + 1;

                if (game.map[currentYposition][currentXPossition] == FieldValue.DOT)
                    game.decDotCount();
                if (game.map[currentYposition][currentXPossition] == FieldValue.BIG_DOT)
                    game.decBigDotCount();

                switch (powerUp) {
                    case 1 -> game.map[currentYposition][currentXPossition] = FieldValue.CHERRY;
                    case 2 -> game.map[currentYposition][currentXPossition] = FieldValue.STRAWBERRY;
                    case 3 -> game.map[currentYposition][currentXPossition] = FieldValue.ORANGE;
                    case 4 -> game.map[currentYposition][currentXPossition] = FieldValue.APPLE;
                    case 5 -> game.map[currentYposition][currentXPossition] = FieldValue.TIME;
                }
            }
        }
    }
}

