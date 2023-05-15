package p2;

import p2.Enums.GhostMovement;

public class Ghost {
    public Ghost(int ghostCurrentRow, int ghostCurrentColumn) {
        this.ghostCurrentRow = ghostCurrentRow;
        this.ghostCurrentColumn = ghostCurrentColumn;
    }

    private int ghostCurrentRow;
    private int ghostCurrentColumn;
    private GhostMovement ghostMove;

    // GETTERS
    public GhostMovement getGhostMove() {
        return ghostMove;
    }
    public int getGhostCurrentRow() {
        return ghostCurrentRow;
    }
    public int getGhostCurrentColumn() {
        return ghostCurrentColumn;
    }

    //SETTERS
    public void setGhostCurrentRow(int ghostCurrentRow) {
        this.ghostCurrentRow = ghostCurrentRow;
    }
    public void setGhostCurrentColumn(int ghostCurrentColumn) {
        this.ghostCurrentColumn = ghostCurrentColumn;
    }
    public void setGhostMove(GhostMovement ghostMove) {
        this.ghostMove = ghostMove;
    }
}
