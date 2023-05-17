package p2;

import p2.Enums.GhostMovement;

public class Ghost {
    private float ghostX;
    private float ghostY;
    private GhostMovement ghostMove;
    public Ghost(float ghostX, float ghostY) {
        this.ghostX = ghostX;
        this.ghostY = ghostY;
    }

    // GETTERS
    public GhostMovement getGhostMove() {
        return ghostMove;
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
    public void setGhostMove(GhostMovement ghostMove) {
        this.ghostMove = ghostMove;
    }
}
