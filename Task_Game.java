package p2;

public class Task_Game implements Runnable {

    Game game;

    Task_Game(Game game){
        this.game = game;
    }

    @Override
    public void run() {

        try {
            while (true) {

                StepOperation stepOperation = new StepOperation();
                game.prformOperation(stepOperation);

                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
