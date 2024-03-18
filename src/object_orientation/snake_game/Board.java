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
    public boolean legalMove;
    
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
        this.initBoard();
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
    public void printboard() {
        int boardMax = this.boardMaximum();
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
     * @return true if the move is legal, false otherwise.
     */
    public void userMove() {
        int[] oldPosition = this.snakeHead();
        int[] newPosition = oldPosition.clone();
        boolean isValidInput = false;
        String move = "w";
        while (!isValidInput) { // input loop
            System.out.println("Enter w, a, s, or d to move:");
            move = in.nextLine();
            isValidInput = this.validateInput(move);
        }
        newPosition = this.calculateNewPosition(move, newPosition);
        this.legalMove = handleMove(newPosition, oldPosition);
    }


    /**
     * Check if the game is won.
     * @return true if the game is won, false otherwise.
     */
    public boolean checkWin() {
        for (int[] row : this.board) {
            for (int cell : row) {
                if (cell == 0 || cell == -1) {
                    return false;
                }
            }
        }
        return true;
    }

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
        int i = rng.nextInt(this.board.length);
        int j = rng.nextInt(this.board[i].length);
        this.board[i][j] = 1; // snake
    }

    /**
     * Find the position of the snake's head.
     * @return The position of the snake's head as an array of two integers.
     */
    private int[] snakeHead() {
        int max = 0;
        int[] position = { 0, 0 };
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] > max) {
                    max = board[i][j];
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    /**
     * Calculate the new position of the snake's head based on user input.
     * @param move The user's input as a String. Can be "w", "a", "s", or "d".
     * @param currentPosition The current position of the snake's head as an array of two integers.
     * @return The new position of the snake's head as an array of two integers.
     */
    private int[] calculateNewPosition(String move, int[] currentPosition) {
        int[] newPosition = Arrays.copyOf(currentPosition, 2);
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
        if (!this.isThereSpace()) { // there is only snake and food on the board
            return; // do nothing
        }
        while (true) { // there are better ways to do this
            int i = rng.nextInt(this.board.length);
            int j = rng.nextInt(this.board[i].length);
            if (this.board[i][j] == 0) {
                this.board[i][j] = -1;
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
        for (int[] row : this.board) {
            for (int cell : row) {
                if (cell == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handle the move of the snake.
     * @param newPosition The new position of the snake's head as an array of two integers.
     * @param oldPosition The old position of the snake's head as an array of two integers.
     * @return true if the move is legal, false otherwise.
     */
    private boolean handleMove(int[] newPosition, int[] oldPosition) {
        boolean legalMove = false;
        int row = newPosition[0];
        int col = newPosition[1];
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) { // check bounds
            legalMove = false;  // strictly speaking, this is not necessary, but it makes the code more readable
        } else {
            int oldValue = board[oldPosition[0]][oldPosition[1]];
            int newValue = oldValue + 1; // we increment the value of the snake's head by one, so that after one round of starving, it has effectively moved forward by one cell, unless it has eaten food, in which case it grows by one cell in the moving direction.
            switch (board[row][col]) {
                case -1: // food
                    board[row][col] = newValue; // move by one cell
                    this.feedSnake(); // this offsets the starving of the snake which always takes place after moving
                    this.putFood();
                    legalMove = true;
                    break;
                case 0: // field
                    board[row][col] = newValue; // move by one cell
                    legalMove = true;
                    break;
                default: // snake
                    legalMove = false;  
                    break;
            }
        }
        this.starveSnake();
        return legalMove;
    }

    /**
     * Starve the snake by decrementing the value of each cell by 1. 
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
     * Find the max value in the board
     * 
     * @return maximum element of the board
     */
    private int boardMaximum() {
        int max = board[0][0];
        for (int[] row : board) {
            for (int cell : row) {
                if (cell > max) {
                    max = cell;
                }
            }
        }
        return max;
    }

   /**
     * Feed the snake by incrementing the value of each cell by 1.
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
