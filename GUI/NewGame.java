package p2.GUI;

import p2.Enums.PacMovement;
import p2.Game;
import p2.ImagePanel;
import p2.Models.CustomTableCellRenderer;
import p2.Models.CustomTableModel;
import p2.Operations.MovePacOperation;

import  javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewGame extends JFrame {

    JPanel panel1 = new JPanel();
    JPanel panel2 ;
    JTable table;
    JPanel panel3 = new JPanel();
    int count = 0;


    Game game;
    BufferedImage tileSet = null;
    public ImagePanel pacmanPanel;



    public NewGame (Game game, int boardSize){

        this.game = game;
        setTitle("Pacman - Menu Główne");

        getContentPane().setLayout(new BorderLayout());

        panel1.setBackground(Color.BLUE);
//        panel1.setPreferredSize(new Dimension(500,40));
        getContentPane().add(panel1, BorderLayout.NORTH);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(500,500));
        panel2.setBackground(Color.RED);
        getContentPane().add(panel2, BorderLayout.CENTER);
        createTable(boardSize);

        panel3.setBackground(Color.GREEN);
        getContentPane().add(panel3, BorderLayout.SOUTH);
//        panel3.setPreferredSize(new Dimension(500,40));


        getContentPane().setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(500,600));
        setSize(500, 600);

        setVisible(true);
        setLocationRelativeTo(null);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            tileSet = ImageIO.read(new File("src\\p2\\tileSet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPacmanImage(3,3);



    }

    private void createTable(int boardSize) {

        int cellSize = 2000 / boardSize;
        table = new JTable(new CustomTableModel(boardSize));
        table.setPreferredSize(new Dimension(500,500));
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    setPacmanImage(3,3);
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_RIGHT);
                    game.performOperation(movePacOperation);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    setPacmanImage(8,35);
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_lEFT);
                    game.performOperation(movePacOperation);
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setPacmanImage(3,69);
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_UP);
                    game.performOperation(movePacOperation);
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setPacmanImage(3,99);
                    MovePacOperation movePacOperation = new MovePacOperation(PacMovement.MOVE_DOWN);
                    game.performOperation(movePacOperation);
                }
            }
        });

        table.setBounds(0,0,500,500);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer(cellSize));
        panel2.setLayout(new BorderLayout());
        panel2.add(table, BorderLayout.CENTER);
    }
    public void setPacmanImage(int x, int y){
        BufferedImage pacmanTile = tileSet.getSubimage(x,y,25,25);
        if (pacmanPanel == null) {
            pacmanPanel = new ImagePanel(pacmanTile);
            pacmanPanel.setBackground(new Color(0,0,0,0));
            table.add(pacmanPanel);
        } else {
            pacmanPanel.setImage(pacmanTile);
            pacmanPanel.repaint();
        }

//        pacmanPanel = new ImagePanel(pacmanTile);
//        pacmanPanel.setBackground(new Color(0,0,0,0));
//        table.add(pacmanPanel);


    }


}

