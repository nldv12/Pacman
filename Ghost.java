package p2;

import p2.Enums.GhostMovement;

public class Ghost {
    public Ghost(float ghostX, float ghostY) {
       this.ghostX = ghostX;
       this.ghostY = ghostY;
    }



    private float ghostX;
    private float ghostY;


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


    public float getGhostX() {
        return ghostX;
    }

    public float getGhostY() {
        return ghostY;
    }



    //SETTERS

    public void setGhostX(float ghostX) {
        this.ghostX = ghostX;
    }

    public void setGhostY(float ghostY) {
        this.ghostY = ghostY;
    }
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
