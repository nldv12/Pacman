package p2;

import p2.Enums.FieldValue;
import p2.Enums.PacMovement;
import p2.FRAMES.ChoosePlayerName;
import p2.ImagePanels.ImagePanel;
import p2.Operations.GameOperation;

import javax.swing.*;
import java.io.*;
import java.util.*;

import java.io.File;

public class Game {
    private int boardSize = 30;
    private static final String FILE_NAME = "HighScores.txt";
    public String currPlayerName;
    //    private String currPlayerName;
    public float pucManX;
    public float pucManY;
    public PacMovement pacMovement;

    public FieldValue[][] map;

    private int playerScore = 500;


    public Game(int boardSize) {
        this.boardSize = boardSize;
        map = generatedBoard(boardSize);
        int a = 0;
        int b = 0;
        boolean succes = false;
        while (!succes) {
            if (map[a][b] == FieldValue.SPACE) {
                pucManX = a;
                pucManY = b;
                succes = true;
            } else {
                if (b == boardSize - 1) {
                    a++;
                    b = 0;
                } else {
                    b++;
                }
            }
        }
    }

    public FieldValue[][] generatedBoard(int size) {
        FieldValue[][] board = new FieldValue[size][size];
        for (int i = 0; i < size; i++) {
            board[i][0] = FieldValue.WALL;
            board[0][i] = FieldValue.WALL;
            board[i][size - 1] = FieldValue.WALL;
            board[size - 1][i] = FieldValue.WALL;
        }
        Random rand = new Random();
        int dotCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    if (rand.nextDouble() < 0.89) {
                        board[i][j] = FieldValue.SPACE;
                        if (dotCount < size * size / 2 && rand.nextDouble() < 0.5) {
                            board[i][j] = FieldValue.DOT;
                            dotCount++;
                        }
                    } else {
                        board[i][j] = FieldValue.WALL;
                    }
                }
            }
        }

        int maxDotCount = size / 5;
        int bigDotCount = 0;

        while (bigDotCount != maxDotCount) {
            int i = rand.nextInt(size);
            int j = rand.nextInt(size);
            if (board[i][j] == FieldValue.DOT) {
                board[i][j] = FieldValue.BIG_DOT;
                bigDotCount++;
            }
        }

        System.out.println();


        return board;
    }

    public synchronized void performOperation(GameOperation operation) {

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
            JOptionPane.showMessageDialog(null, "Plik HighScores.txt nie istnieje", "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd podczas odczytu pliku HighScores.txt", "Błąd", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
        }
        return records;
    }

//    GETTERS

    public int getBoardSize() {
        return boardSize;
    }

    public int getPlayerScore() {
        return playerScore;
    }



//    SETTERS
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }



}
