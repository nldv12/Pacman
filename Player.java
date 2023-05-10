package p2;

import java.io.Serializable;


public class Player implements Serializable {
    public String name;
    public int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }


    @Override
    public String toString() {
        return name + "   score   " + score + "   points";
    }
}

