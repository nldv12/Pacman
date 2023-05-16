package p2;

import p2.Enums.FieldValue;
import p2.Enums.GhostMovement;
import p2.Enums.PacMovement;
import p2.Operations.GameOperation;

import javax.swing.*;
import java.io.*;
import java.util.*;

import java.io.File;

public class Game {
    // BOARD
    private int boardSize;
    private int dotCount;
    private int bigDotCount;
    private static final String FILE_NAME = "HighScores.txt";
    public FieldValue[][] map;
    private int playerScore = 0;


    //PACMAN
    private PacMovement pacMovement;
    private PacMovement prevMove;
    private PacMovement nextMove;
    // do usunięcia
    private int currentRow;
    private int currentColumn;



    private boolean pacmanDead;



    private float pucManY;
    private float pucManX;


    public Map<Integer, Ghost> ghosts = new LinkedHashMap<>();




    public Game(int boardSize) {
        this.boardSize = boardSize;
        map = generatedBoard(boardSize);
        placePacman();
        placeGhosts();
    }

    public void placePacman(){
        int a = 0;
        int b = 0;
        boolean succes = false;
//        pucManX = 18.5f;
//        pucManY = 18.5f;
        while (!succes) {
            if (map[a][b] == FieldValue.SPACE && map[a][b + 1] == FieldValue.SPACE && map[a][b + 2] == FieldValue.SPACE && map[a][b + 3] == FieldValue.SPACE) {
                pucManX = a + 0.5f;
                pucManY = b + 0.5f;
                setCurrentRow(a);
                setCurrentColumn(b);
                succes = true;
            } else {
                if (b == boardSize - 1) {
                    a++;
                    b = 0;
                } else {
                    b++;
                }
            }
            if (a == boardSize) {
                a = 0;
                b = 0;
                map = generatedBoard(boardSize);
            }
        }
    }
    public void placeGhosts() {
        int a = boardSize - 1;
        int b = boardSize - 1;
        boolean succes = false;

        while (!succes) {
            if (map[a][b] == FieldValue.SPACE
                    && map[a][b - 1] == FieldValue.SPACE
                    && map[a][b - 2] == FieldValue.SPACE
                    && map[a][b - 3] == FieldValue.SPACE) {
                ghosts.put(0,new Ghost(a +0.5f,b+0.5f));
                ghosts.put(1,new Ghost(a+0.5f,b - 1 +0.5f));
                ghosts.put(2,new Ghost(a+0.5f,b-2 +0.5f));
                ghosts.put(3,new Ghost(a+0.5f,b-3 +0.5f));
                succes = true;
            } else {
                if (b == 0) {
                    a--;
                    b = boardSize - 1;
                } else {
                    b--;
                }
            }
            if (a == -1 ) {
                a = boardSize - 1;
                b = boardSize - 1;
                map = generatedBoard(boardSize);
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
        dotCount = 0;
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
        bigDotCount = 0;

        while (bigDotCount != maxDotCount) {
            int i = rand.nextInt(size);
            int j = rand.nextInt(size);
            if (board[i][j] == FieldValue.DOT) {
                board[i][j] = FieldValue.BIG_DOT;
                bigDotCount++;
                dotCount--;
            }
        }

        return board;
    }

    public synchronized void performOperation(GameOperation operation) {
        operation.doOperation(this);
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

//    GETTERS ==============================================================

    public int getPlayerScore() {
        return playerScore;
    }

    public PacMovement getPacMovement() {
        return pacMovement;
    }

    public PacMovement getPrevMove() {
        return prevMove;
    }

    public PacMovement getNextMove() {
        return nextMove;
    }

    public float getPucManX() {
        return pucManX;
    }

    public float getPucManY() {
        return pucManY;
    }

    public int getDotCount() {
        return dotCount;
    }

    public int getBigDotCount() {
        return bigDotCount;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }
    public boolean isPacmanDead() {
        return pacmanDead;
    }




    //    SETTERS ==============================================================

    public void setPacmanDead(boolean pacmanDead) {
        this.pacmanDead = pacmanDead;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public void decDotCount() {
        this.dotCount--;
    }

    public void decBigDotCount() {
        this.bigDotCount--;
    }

    public void setPucManX(float pucManX) {
        this.pucManX = pucManX;
    }

    public void setPucManY(float pucManY) {
        this.pucManY = pucManY;
    }

    public void setPrevMove(PacMovement prevMove) {
        this.prevMove = prevMove;
    }

    public void setNextMove(PacMovement nextMove) {
        this.nextMove = nextMove;
    }

    public void setPacMovement(PacMovement pacMovement) {
        this.pacMovement = pacMovement;
    }

    public void incPlayerScore(int number) {
        this.playerScore += number;
    }

}
