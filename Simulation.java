public class Simulation {

    private int tickLength;
    private Board board;

    public Simulation(int t) {
        tickLength = t;
        board = new Board(11, 11);
        startSimulation();
    }

    public void startSimulation() {
        while (true) {
            pause();
            tick();
        }
    }

    /* each tick, you should:
    1) call the method createNewGeneration()
    2) display the board
    */
    public void tick() {
        board.createNewGeneration();
        board.displayBoard();
    }

    public void pause() {
        try {
            Thread.sleep(tickLength);
        }
        catch(InterruptedException e){
            System.out.println("Whoops my code had a boo-boo");
        }
    }
}