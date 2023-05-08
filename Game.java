package p2;

import p2.Enums.PacMovement;
import p2.Operations.GameOperation;

public class Game {

    public float pucManX;
    public float pucManY;
    public PacMovement pacMovement;

//    int[][] map = new int[][];

//    TODO: enum space, dot, big dot, wall
    public synchronized void performOperation(GameOperation operation){

        operation.doOperation(this);
    }




}
