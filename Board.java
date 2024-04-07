public class Board {

    private Cell[][] board;

    public Board(int rows, int cols) {
        board = new Cell[rows][cols];
        clearBoard();
        placeFirstGen();
        displayBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(false);
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeFirstGen() {
        board[3][6] = new Cell(true);
        board[1][5] = new Cell(true);
    }

    public int countLiveNeighbors(int a, int b) {
        int count = 0;
        for (int i = a - 1; i <= a + 1; i++) {
            for (int j = b - 1; j <= b + 1; j++) {
                //looping through the board
                if (i > 0 && i < board.length - 1) {
                    if (j > 0 && j < board[0].length - 1) {
                        // check all the diagnols
                        if (board[i - 1][j - 1].getIsAlive()) {
                            // down left
                            count++;
                        }
                        if (board[i - 1][j + 1].getIsAlive()) {
                            count++;
                            // down right
                        }
                        if (board[i + 1][j - 1].getIsAlive()) {
                            count++;
                            // up left
                        }
                        if (board[i + 1][j + 1].getIsAlive()) {
                            count++;
                            // up right
                        }
                    }
                }
                // now check the stright away left right up down neihbors
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    // check in bounds?
                    if (board[i][j].getIsAlive()) {
                        if ((i != a || j != b)) {
                            count++;
                            // ITS ALIVE!
                        }
                    }
                }

            }
        }
        return count;
    }


    public void createNewGeneration() {
        Cell[][] nextGenBoard = new Cell[board.length][board[0].length];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                // loop through
                int count = countLiveNeighbors(r, c);
                // is curr alive?
                boolean isalive = false;
                // check all rules
                if (board[r][c].getIsAlive()) {
                    if (count == 2 || count == 3) {
                        isalive = true; // Any live cell with 2 or 3 live neighbors survives
                    }
                } else {
                    if (count == 3) {
                        isalive = true; // Any dead cell with exactly 3 live neighbors becomes alive
                    }
                }

                nextGenBoard[r][c] = new Cell(isalive);
            }
        }
        board = nextGenBoard;
    }



}