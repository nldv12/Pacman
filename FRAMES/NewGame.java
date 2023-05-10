package p2.FRAMES;

import p2.*;
import p2.Enums.PacMovement;
import p2.ImagePanels.CountdownPanel;
import p2.ImagePanels.LivesPanel;
import p2.Models.CustomTableCellRenderer;
import p2.Models.CustomTableModel;
import p2.Operations.MovePacOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewGame extends JFrame {
    JPanel header = new JPanel();
    JPanel body;
    public JTable table;
    public CustomTableCellRenderer cellRenderer;
    Game game;
    int cellSize;
    int width;
    int height;
    boolean isBigMap;

    private CountdownPanel countdownPanel;


    public NewGame(Game game, int boardSize) {
        this.game = game;
        if (boardSize < 30) {
            this.cellSize = 25;
        } else if (boardSize > 30 ) {
            this.cellSize = 16;
        }
        if (boardSize > 45) {
            this.isBigMap = true;
        }

        this.width = cellSize * boardSize;
        this.height = width + 100;
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
        }

        getContentPane().setBackground(Constants.MY_BLACK);
        setPreferredSize(new Dimension(width, height));
        pack();

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillHeader() {
//        header.setLayout();
        JLabel scoreName = new JLabel("Score: ");
        scoreName.setForeground(Constants.MY_ORANGE);
        scoreName.setFont(Constants.MY_FONT2);
        JLabel score = new JLabel(game.getPlayerScore() +"   ");
        score.setForeground(Constants.MY_ORANGE);
        score.setFont(Constants.MY_FONT2);
        LivesPanel livesPanel = new LivesPanel(5);

        JLabel space = new JLabel("      ");

        countdownPanel = new CountdownPanel(120);



        header.add(scoreName);
        header.add(score);
        header.add(livesPanel);
        header.add(space);
        header.add(countdownPanel);
    }
    private void createTable(int boardSize) {
        table = new JTable(new CustomTableModel(boardSize));
        table.setCellSelectionEnabled(false);
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

}

