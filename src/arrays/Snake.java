package arrays;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Snake class represents a simple snake game.
 * The game is played on a grid where the snake (represented by green squares) moves around and tries to eat the food (represented by yellow squares).
 * The objective of the board is to eat as much food as possible without colliding with the boundaries of the grid or the snake's own body.
 * The game ends when the snake collides with a boundary or its own body or when it fills up the entire board.
 * The user can make the snake move up, down, left, or right using the 'w', 'a', 's', and 'd' keys respectively.
 * Internally, the game is represented by a 2D array of integers. The snake is represented by positive integers that decrease by one from the snake's head to its tail. The food is represented by -1. The field is represented by 0. Whenever the snake moves, the cell in the direction it is moving becomes its new head with a value one greater than the previous head. After every move, the value of every snake-cell is decremented by one. If the value of a snake-cell becomes zero, the snake has moved off that cell. If the snake eats food, the value of every snake-cell is incremented by one, effectivelt growing it in length by one cell.
 * The game is played by calling the main method of the Snake class.
 */
public class Snake {

    static PrintWriter printWriter = new PrintWriter(System.out, true);
    static Scanner in = new Scanner(System.in);
    static Random rng = new Random();

    /**
     * Print the board.
     * Snake:       🟩 (U+1F7E9)
     * Snake head:  👀 (U+1F440)
     * Food:        🟨 (U+1F7E8)
     * Field:       ⬛ (U+2B1B)
     * @param board
     */
    public static void printboard(int[][] board) {
        int boardMax = boardMaximum(board);
        for (int[] row : board) {
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
            printWriter.println(toPrint); // we need to use printWriter.println() instead of System.out.println() to avoid the Unicode characters being printed as question marks
        }
    }

    /**
     * Initialize the board. Set all cells to 0 and place the snake at a random position.
     * @param board The board to initialize.
     */
    public static void initBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0; // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
            }
        }
        int i = rng.nextInt(board.length);
        int j = rng.nextInt(board[i].length);
        board[i][j] = 1; // snake
    }

    /**
     * Place one cell of food on the board board at a random position.
     * @param board The board to place the food on.
     */
    public static void putFood(int[][] board) {
        // check if there is space
        if (!isThereSpace(board)) { // there is only snake and food on the board
            return; // do nothing
        }
        while (true) { // there are better ways to do this
            int i = rng.nextInt(board.length);
            int j = rng.nextInt(board[i].length);
            if (board[i][j] == 0) {
                board[i][j] = -1;
                return;
            }
        }
    }
    /**
     * Look if there is free space on the board.
     * @param board The board to search.
     * @return true if there is free space, false otherwise.
     */
    public static boolean isThereSpace(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Find the position of the snake's head.
     * @param board The board to search.
     * @return The position of the snake's head as an array of two integers.
     */
    public static int[] snakeHead(int[][] board) {
        int max = 0;
        int[] position = { 0, 0 };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    public static int boardMaximum(int[][] board) {
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
     * Check if the game is won.
     * @param board The board to check.
     * @return true if the game is won, false otherwise.
     */
    public static boolean checkWin(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0 || cell == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Feed the snake by incrementing the value of each cell by 1.
     * @param board The board to feed the snake on.
     */
    public static void feedSnake(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    board[i][j]++; // note that we cannot iterate using (int cell : row) because we need to modify the cell _value_
                }
            }
        }
    }

    /**
     * Starve the snake by decrementing the value of each cell by 1. If the value of a cell becomes 0, the snake has moved off that cell.
     * @param board The board the snake is on.
     */
    public static void starveSnake(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    board[i][j]--; 
                }
            }
        }
    }

    /**
     * Move the snake based on user input.
     * @param board The board the snake is on.
     * @return true if the move is legal, false otherwise.
     */
    public static boolean userMove(int[][] board) {
        int[] oldPosition = snakeHead(board);
        int[] newPosition = oldPosition.clone();
        boolean isValidInput = false;
        String move = "w";
        while (!isValidInput) { // input loop
            System.out.println("Enter w, a, s, or d to move:");
            move = in.nextLine();
            isValidInput = validateInput(move);
        }
        newPosition = calculateNewPosition(move, newPosition, board);
        return handleMove(newPosition, oldPosition, board);
    }

    public static boolean validateInput(String move) {
        return move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d");
    }

    /**
     * Calculate the new position of the snake's head based on user input.
     * @param move The user's input as a String. Can be "w", "a", "s", or "d".
     * @param currentPosition The current position of the snake's head as an array of two integers.
     * @return The new position of the snake's head as an array of two integers.
     */
    public static int[] calculateNewPosition(String move, int[] currentPosition, int[][] board) {
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
     * Handle the move of the snake based on the new position of the snake's head.
     * @param newPosition The new position of the snake's head as an array of two integers.
     * @param oldPosition The old position of the snake's head as an array of two integers.
     * @param board The board the snake is on.
     */
    public static boolean handleMove(int[] newPosition, int[] oldPosition, int[][] board) {
        boolean legalMove = false;
        int row = newPosition[0];
        int col = newPosition[1];
        if (row < 0 && row >= board.length && col < 0 && col >= board[row].length) { // check bounds
            legalMove = false;  // strictly speaking, this is not necessary, but it makes the code more readable
        } else {
            int oldValue = board[oldPosition[0]][oldPosition[1]];
            int newValue = oldValue + 1; // we increment the value of the snake's head by one, so that after one round of starving, it has effectively moved forward by one cell, unless it has eaten food, in which case it grows by one cell in the moving direction.
            switch (board[row][col]) {
                case -1: // food
                    board[row][col] = newValue; // move by one cell
                    feedSnake(board); // this offsets the starving of the snake which always takes place after moving
                    putFood(board);
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
        starveSnake(board);
        return legalMove;
    }

    /**
     * Main method to play the game.
     * @param args
     */
    public static void main(String[] args) {

        int N = 9; // size of the board is N x N

        int[][] myBoard = new int[N][N];
        initBoard(myBoard);
        putFood(myBoard);
        boolean legalMove = true;
        while (legalMove) {
            printboard(myBoard);
            legalMove = userMove(myBoard);
            if (checkWin(myBoard)) {
                System.out.println("You win! :)");
                return;
            }
        }
        System.out.println("You lose :(");
    }
}