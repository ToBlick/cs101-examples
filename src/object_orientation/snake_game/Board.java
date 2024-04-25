package object_orientation.snake_game;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Board {
    // properties
    private PrintWriter out;
    private Scanner in;
    private Random rng;

    private static int highscore = 1; // static variable to keep track of the highscore across multiple games

    private int[][] board;
    private boolean legalMove;
    private int freeCells;
    private boolean isWon;

    private int[] headPosition;
    
    // public methods

    /**
     * Constructor for the board. Creates a N x N matrices, places the head 
     * of the snake, and places food.
     * 
     * @param N size of the board is N x M
     * @param M size of the board is N x M
     */
    public Board(int N, int M) {
        this.out = new PrintWriter(System.out, true);
        this.in = new Scanner(System.in);
        this.rng = new Random();
        this.isWon = false;
        this.legalMove = true;
        this.board = new int[N][M];
        this.headPosition = new int[2];

        this.initBoard();
        this.freeCells = N * M - 1; 
        this.putFood();
    }

    /**
     * Convenience constructor for the board with N = 9.
     */
    public Board(int N) {
        this(N, N);
    }

    /**
     * Print the board.
     * Snake:       🟩 (U+1F7E9) 
     * Snake head:  👀 (U+1F440) 
     * Food:        🟨 (U+1F7E8) 
     * Field:       ⬛ (U+2B1B)  
     */
    public void printBoard() {
        int boardMax = this.snakeLength();
        for (int[] row : this.board) {
            String toPrint = "";
            for (int cell : row) {
                if (cell == 0) { // Field
                    toPrint += "⬛";     
                } else if (cell == boardMax) { // Snake head
                    toPrint += "👀";       
                } else if (cell == -1) {// Food   
                    toPrint += "🟨";     
                } else { // Snake
                    toPrint += "🟩";
                }
            }
            this.out.println(toPrint); // we need to use PrintWriter.println() instead of System.out.println() to avoid the Unicode characters being printed as question marks
        }
    }

    /**
     * Move the snake based on user input.
     * 
     * @return true if the move is legal, false otherwise.
     */
    public void userMove() {
        boolean isValidInput = false;
        String move = "w";
        while (!isValidInput) { // input loop
            System.out.println("Enter w, a, s, or d to move:");
            move = in.nextLine();
            isValidInput = validateInput(move);
        }
        int[] newPosition = calculateNewPosition(move);
        handleMove(newPosition);
    }

    /**
     * Determine if a move is legal
     * 
     * @return legalMove
     */
    public boolean legalGame() {
        return this.legalMove;
    }

    /**
     * Check if the game is won.
     * 
     * @return true if the game is won, false otherwise.
     */
    public boolean checkWin() {
        return this.isWon;
    }

    public int getHightscore() {
        return Board.highscore;
    }

    /**
     * Get the value of the head (aka the snake's length)
     * 
     * @return value of the head
     */
    public int snakeLength() {
        return getCell(this.headPosition[0], this.headPosition[1]);
    }

    // private methods

    private int getWidth() {
        return this.board.length;
    }

    private int getHeight() {
        return this.board[0].length;
    }

    /**
     * Set the value of a specific cell on the board.
     * 
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @param value The value to set for the cell.
     */
    private void setCell(int i, int j, int value) {
        if (i < getWidth() && j < getHeight() && i >= 0 && j >= 0) { // perform bounds check
            this.board[i][j] = value;
        }
    }

    private int getCell(int i, int j) {
        return this.board[i][j];
    }

    /**
     * Check whether the move is valid
     * 
     * @param move user input
     * @return true if the input is valid
     */
    private boolean validateInput(String move) {
        return move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d");
    }

    /**
     * Initialize the board. Set all cells to 0 and place the snake 
     * at a random position.
     */
    private void initBoard() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                setCell(i, j, 0); // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
            }
        }
        this.headPosition[0] = rng.nextInt(getWidth());
        this.headPosition[1] = rng.nextInt(getHeight());
        setCell(this.headPosition[0], this.headPosition[1], 1); // snake
    }

    /**
     * Calculate the new position of the snake's head based on user input.
     * 
     * @param move The user's input as a String. Can be "w", "a", "s", or "d".
     * @return The new position of the snake's head as an array of two integers.
     */
    private int[] calculateNewPosition(String move) {
        int[] newPosition = Arrays.copyOf(this.headPosition, 2);
        switch (move) {
            case "w":
                newPosition[0]--; // move up
                break;
            case "s":
                newPosition[0]++; // move down
                break;
            case "a":
                newPosition[1]--; // move left
                break;
            default: // "d"
                newPosition[1]++; // move right
                break;
        }
        return newPosition;
    }

    /**
     * Place one cell of food on the board board at a random position.
     */
    private void putFood() {
        // check if there is space
        if (!this.isThereSpace()) { 
            // when no space to put food, we win!
            this.isWon = true;
            return; // do nothing
        }
        while (true) { // there are better ways to do this
            int i = rng.nextInt(this.board.length);
            int j = rng.nextInt(this.board[i].length);
            if (getCell(i, j) == 0) {
                setCell(i, j, -1);
                this.freeCells -= 1;
                return;
            }
        }
    }

    /**
     * Look if there is free space on the board.
     * 
     * @return true if there is free space, false otherwise.
     */
    private boolean isThereSpace() {
        return this.freeCells != 0;
    }

    /**
     * Handle the move of the snake.
     * 
     * @param newPosition The new position of the snake's head as an array of two integers.
     * @return true if the move is legal, false otherwise.
     */
    private void handleMove(int[] newPosition) {
        int row = newPosition[0];
        int col = newPosition[1];
        if (row < 0 || row >= getWidth() || col < 0 || col >= getHeight()) { // check bounds
            this.legalMove = false;
        } else {
            int oldValue = this.snakeLength();
            int newValue = oldValue + 1; // we increment the value of the snake's head by one, so that after one round of starving, it has effectively moved forward by one cell, unless it has eaten food, in which case it grows by one cell in the moving direction.
            switch (getCell(row, col)) {
                case -1: // food
                    setCell(row, col, newValue); // move by one cell
                    this.feedSnake(); // this offsets the starving of the snake which always takes place after moving
                    this.putFood();
                    this.legalMove = true;
                    break;
                case 0: // field
                    setCell(row, col, newValue); // move by one cell
                    this.legalMove = true;
                    break;
                default: // snake
                this.legalMove = false;  
                    break;
            }
        }
        if (this.legalMove) {
            this.headPosition = newPosition;
            this.starveSnake();
            if (this.snakeLength() > highscore) {
                highscore = this.snakeLength();
            }
        }
    }

    /**
     * Starve the snake by decrementing the value of each cell in the body by 1. 
     * If the value of a cell becomes 0, the snake has moved off that cell.
     */
    private void starveSnake() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getCell(i, j) > 0) {
                    setCell(i, j, this.board[i][j] - 1);
                }
            }
        }
    }

   /**
     * Feed the snake by incrementing the value of each cell in the body by 1.
     */
    private void feedSnake() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getCell(i, j) > 0) {
                    setCell(i, j, getCell(i, j) + 1);
                    // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
                }
            }
        }
    }

}
