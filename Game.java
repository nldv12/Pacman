package p2;

import p2.Enums.PacMovement;
import p2.Operations.GameOperation;
import java.io.*;
import java.util.*;

import java.io.File;

public class Game {
    private static final String FILE_NAME = "HighScores.txt";
    public String currPlayerName;
//    private String currPlayerName;
    public float pucManX;
    public float pucManY;
    public PacMovement pacMovement;

//    int[][] map = new int[][];

//    TODO: enum space, dot, big dot, wall
    public synchronized void performOperation(GameOperation operation){

        operation.doOperation(this);
    }


    public String getCurrPlayerName() {
        return currPlayerName;
    }

    public void setCurrPlayerName(String currPlayerName) {
        this.currPlayerName = currPlayerName;
    }

    public float getPucManX() {
        return pucManX;
    }

    public void setPucManX(float pucManX) {
        this.pucManX = pucManX;
    }

    public float getPucManY() {
        return pucManY;
    }

    public void setPucManY(float pucManY) {
        this.pucManY = pucManY;
    }

    public PacMovement getPacMovement() {
        return pacMovement;
    }

    public void setPacMovement(PacMovement pacMovement) {
        this.pacMovement = pacMovement;
    }



    public static void addRecord(Player player) {
        try {
            // Sprawdzamy, czy plik już istnieje, a jeśli nie, to go tworzymy
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            // Dopisujemy nowy rekord do pliku
            FileWriter writer = new FileWriter(file, true);
            writer.write(player.toString() + "\n");
            writer.close();

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania rekordu gracza.");
            e.printStackTrace();
        }
    }

    public static LinkedList<String> getRecords() {
        LinkedList<String> records = new LinkedList<>();
        try {
            // Otwieramy plik do odczytu
            FileReader reader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Odczytujemy kolejne linie z pliku i dodajemy je do listy
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                records.add(line);
            }
            // Zamykamy plik
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Plik HighScores.txt nie istnieje.");
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku HighScores.txt.");
            e.printStackTrace();
        }

        return records;
    }

}
