package p2.FRAMES;

import p2.*;
import p2.Enums.PacMovement;
import p2.ImagePanels.CountdownPanel;
import p2.ImagePanels.LivesPanel;
import p2.Models.CustomTableCellRenderer;
import p2.Models.CustomTableModel;
import p2.Operations.MovePacOperation;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewGame extends JFrame {
    private int width;
    private int height;


    //PANELS
    private JPanel header = new JPanel();
    private JLabel score;


    private LivesPanel livesPanel;
    private CountdownPanel countdownPanel;
    private JPanel body;

    public JTable table;

    private JPanel footer = new JPanel();
    public CustomTableModel tableModel;
    public CustomTableCellRenderer cellRenderer;


    // VALUES from Constructor

    private Game game;
    private int boardSize;
    private int cellSize;
    private boolean isBigMap;
    private int gameTimeInSeconds;
    int livesCount;


    //OTHER

    public float realCellSizeX;
    public float realCellSizeY;

    public NewGame(Game game, int boardSize, int gameTimeInSeconds, int livesCount) {
        this.game = game;
        this.boardSize = boardSize;
        this.gameTimeInSeconds = gameTimeInSeconds;
        this.livesCount = livesCount;


        if (boardSize < 30) {
            this.cellSize = 24;
        } else if (boardSize > 30 ) {
            this.cellSize = 16;
        }
        if (boardSize > 45) {
            this.isBigMap = true;
        }


        this.width = cellSize * boardSize;
        this.height = width + 105;
        setTitle("Pacman - Menu Główne");

        getContentPane().setLayout(new BorderLayout());
        header.setBackground(Constants.MY_BLACK);
        fillHeader();
        getContentPane().add(header, BorderLayout.NORTH);

        if (isBigMap) {
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());
            tablePanel.setPreferredSize(new Dimension(10, height));
            createTable(boardSize);
            tablePanel.add(table, BorderLayout.CENTER);
            height = 800;
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }else{
            body = new JPanel();
            body.setPreferredSize(new Dimension(500, 500));
            getContentPane().add(body, BorderLayout.CENTER);
            createTable(boardSize);
            body.setLayout(new BorderLayout());
            body.add(table, BorderLayout.CENTER);
            if (boardSize<15){
                footer.setBackground(Constants.MY_BLACK);
                getContentPane().add(footer, BorderLayout.SOUTH);
            }

        }

        getContentPane().setBackground(Constants.MY_BLACK);
        setPreferredSize(new Dimension(width, height));
        pack();

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_RIGHT);
                    game.performOperation(movePacOperation);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_lEFT);
                    game.performOperation(movePacOperation);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_UP);
                    game.performOperation(movePacOperation);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_DOWN);
                    game.performOperation(movePacOperation);
                } else if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                    SwingUtilities.invokeLater(() -> new MainMenu(game));
                    dispose();
                }
            }
        });


        Rectangle cellRect = table.getCellRect(0, 0, false);
        Rectangle cellRect2 = table.getCellRect(boardSize-1, boardSize-1, false);

        realCellSizeX = (float) (cellRect2.x - cellRect.x) /(float) (boardSize-1);
        realCellSizeY = (float)(cellRect2.y - cellRect.y) /(float)(boardSize-1);



    }

    private void fillHeader() {
        JLabel scoreName = new JLabel("Score: ");
        scoreName.setForeground(Constants.MY_ORANGE);
        scoreName.setFont(Constants.MY_FONT2);
        score = new JLabel(game.getPlayerScore() +"   ");
        score.setForeground(Constants.MY_ORANGE);
        score.setFont(Constants.MY_FONT2);
        livesPanel = new LivesPanel(livesCount);
        JLabel space = new JLabel("   ");
        countdownPanel = new CountdownPanel(gameTimeInSeconds);

        header.add(scoreName);
        header.add(score);
        header.add(livesPanel);
        if (boardSize>=15) {
            header.add(space);
            header.add(countdownPanel);
        }
        else {
            footer.add(countdownPanel);
        }

    }
    private void createTable(int boardSize) {

        tableModel = new CustomTableModel(boardSize);
        table = new JTable(tableModel);
        table.setCellSelectionEnabled(false);
        table.setBounds(0, 0, 500, 500);
        cellRenderer = new CustomTableCellRenderer(cellSize);
        table.setDefaultRenderer(Object.class, cellRenderer);
        table.setPreferredSize(new Dimension(500, 500));
        table.setFocusable(false);
        table.setBackground(Constants.MY_BLACK);
        table.setRowHeight(cellSize);

    }

    public CountdownPanel getCountdownPanel() {
        return countdownPanel;
    }

    public LivesPanel getLivesPanel() {
        return livesPanel;
    }

    public void updateScore(){
        score.setText(game.getPlayerScore()+"   ");
    }
    public int getCellSize() {
        return cellSize;
    }

}

