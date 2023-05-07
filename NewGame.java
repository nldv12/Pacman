package p2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewGame extends JFrame {

    JPanel panel1 = new JPanel();
    JPanel panel2 ;
    JPanel panel3 = new JPanel();
    int count = 0;


    Game game;
    ImagePanel pacmanPanel;




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

    }

    private void createTable(int boardSize) {


        int cellSize = 2000 / boardSize;
        JTable table = new JTable(new CustomTableModel(boardSize));
        table.setPreferredSize(new Dimension(500,500));


        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    System.out.println("w prawo");
                    MovePacOperation movePacOperation = new MovePacOperation();
                    game.prformOperation(movePacOperation);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("w lewo");
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("w górę");
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("w dół");

                }

            }
        });


        BufferedImage tileSet = null;
        try {
            tileSet = ImageIO.read(new File("G:\\Mój dysk\\JAVA\\___GUI___\\__PROJECTS__\\src\\tileSet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage pacmanTile = tileSet.getSubimage(0,0,32,32);

        table.setBounds(0,0,500,500);
        pacmanPanel = new ImagePanel(pacmanTile);
        pacmanPanel.setBackground(new Color(0,0,0,0));


//        JPanel panelNew = new JPanel();
//        panelNew.setBounds(100, 100, 50, 50);
//        panelNew.setBackground(Color.YELLOW);
        table.add(pacmanPanel);

//        Timer timer = new Timer(50, event -> { // set delay to 1000ms (1 second)
//            count++;
//            imagePanel.setBounds(100 + count, 100, 200, 100);
//        });
//        timer.start();





        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer(cellSize));
        panel2.setLayout(new BorderLayout());
        panel2.add(table, BorderLayout.CENTER);
    }

}

