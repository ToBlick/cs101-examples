package object_orientation.snake_game;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Board {
    // properties
    private PrintWriter printWriter;
    private Scanner in;
    private Random rng;

    private int[][] board;
    private boolean legalMove;
    private int numberOfField;
    private boolean isWin;

    private int[] headPos;
    
    // actions
    /**
     * Constructor for Board. Create a N x N matrices, put the head 
     * of the snake, and put the food.
     * 
     * @param N size of the board is N x N
     */
    public Board(int N) {
        this.printWriter = new PrintWriter(System.out, true);
        this.in = new Scanner(System.in);
        this.rng = new Random();

        this.board = new int[N][N];
        this.legalMove = true;
        this.headPos = new int[2];
        this.initBoard();
        this.numberOfField = N * N - 1;
        this.isWin = false;
        this.putFood();
    }

    /**
     * Constructor for Board. Create a 9 x 9 matrices, put the head 
     * of the snake, and put the food.
     */
    public Board() {
        this(9);
    }

    /**
     * Print the board.
     * Snake:       🟩 (U+1F7E9)       x
     * Snake head:  👀 (U+1F440)       H
     * Food:        🟨 (U+1F7E8)       F
     * Field:       ⬛ (U+2B1B)        -
     */
    public void printBoard() {
        int boardMax = this.headValue();
        for (int[] row : this.board) {
            String toPrint = "";
            for (int cell : row) {
                if (cell == 0) { // Field
                    toPrint += "-";//"⬛";     
                } else if (cell == boardMax) { // Snake head
                    toPrint += "H";//"👀";       
                } else if (cell == -1) {// Food   
                    toPrint += "F";//"🟨";     
                } else { // Snake
                    toPrint += "x";//"🟩";
                }
            }
            this.printWriter.println(toPrint); // we need to use printWriter.println() instead of System.out.println() to avoid the Unicode characters being printed as question marks
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
            isValidInput = this.validateInput(move);
        }
        int[] newPosition = this.calculateNewPosition(move);
        this.handleMove(newPosition);
    }

    /**
     * Whether the move is legal
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
        return this.isWin;
    }

    /**
     * Check whether the move is legal
     * 
     * @param move user input
     * @return true if the input is legal
     */
    private boolean validateInput(String move) {
        return move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d");
    }

    /**
     * Initialize the board. Set all cells to 0 and place the snake 
     * at a random position.
     */
    private void initBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = 0; // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
            }
        }
        this.headPos[0] = rng.nextInt(this.board.length);
        this.headPos[1] = rng.nextInt(this.board[0].length);
        this.board[this.headPos[0]][this.headPos[1]] = 1; // snake
    }

    /**
     * Calculate the new position of the snake's head based on user input.
     * 
     * @param move The user's input as a String. Can be "w", "a", "s", or "d".
     * @return The new position of the snake's head as an array of two integers.
     */
    private int[] calculateNewPosition(String move) {
        int[] newPosition = Arrays.copyOf(this.headPos, 2);
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
            this.isWin = true;
            return; // do nothing
        }
        while (true) { // there are better ways to do this
            int i = rng.nextInt(this.board.length);
            int j = rng.nextInt(this.board[i].length);
            if (this.board[i][j] == 0) {
                this.board[i][j] = -1;
                this.numberOfField -= 1;
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
        return this.numberOfField != 0;
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
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) { // check bounds
            this.legalMove = false;  // strictly speaking, this is not necessary, but it makes the code more readable
        } else {
            int oldValue = this.headValue();
            int newValue = oldValue + 1; // we increment the value of the snake's head by one, so that after one round of starving, it has effectively moved forward by one cell, unless it has eaten food, in which case it grows by one cell in the moving direction.
            switch (board[row][col]) {
                case -1: // food
                    board[row][col] = newValue; // move by one cell
                    this.feedSnake(); // this offsets the starving of the snake which always takes place after moving
                    this.putFood();
                    this.legalMove = true;
                    break;
                case 0: // field
                    board[row][col] = newValue; // move by one cell
                    this.legalMove = true;
                    break;
                default: // snake
                this.legalMove = false;  
                    break;
            }
        }
        this.headPos = newPosition;
        this.starveSnake();
    }

    /**
     * Starve the snake by decrementing the value of each cell in the body by 1. 
     * If the value of a cell becomes 0, the snake has moved off that cell.
     */
    private void starveSnake() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] > 0) {
                    this.board[i][j]--; 
                }
            }
        }
    }

    /**
     * Get the value of the head
     * 
     * @return value of the head
     */
    private int headValue() {
        return this.board[this.headPos[0]][this.headPos[1]];
    }

   /**
     * Feed the snake by incrementing the value of each cell in the body by 1.
     */
    private void feedSnake() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] > 0) {
                    this.board[i][j]++; // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
                }
            }
        }
    }

}
