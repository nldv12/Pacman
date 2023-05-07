package p2;

public class Task_Viev implements Runnable{

    Game game;
    NewGame newGame;

    public Task_Viev(NewGame newGame ,Game game){
        this.game = game;
        this.newGame = newGame;

    }
    @Override
    public void run() {


        try {
            while (true){
                FatchVievOperation fatchVievOperation = new FatchVievOperation();
                game.prformOperation(fatchVievOperation);
                newGame.pacmanPanel.setBounds(fatchVievOperation.pacXposition,100,32,32);


                Thread.sleep(100);
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
