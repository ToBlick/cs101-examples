package arrays;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * The Snake class represents a simple snake board.
 * The board is played on a grid where the snake (represented by green squares) moves around and tries to eat the food (represented by yellow squares). After eating food, the snake grows by one square.
 * The objective of the board is to eat as much food as possible without colliding with the boundaries of the grid or the snake's own body.
 * The board ends when the snake collides with a boundary or its own body.
 * 
 * The board is controlled by the user, who can make the snake move up, down, left, or right using the 'w', 'a', 's', and 'd' keys respectively.
 * 
 * The Snake class provides methods for initializing the board, printing the board state, updating the board state based on user input, and checking for win or loss conditions.
 * 
 * The board is played by calling the main method of the Snake class.
 * 
 * Example usage:
 * 
 * int[][] myBoard = new int[9][9];
 * Snake.initBoard(myBoard);
 * Snake.putFood(myBoard);
 * boolean validInput = true;
 * while (validInput) {
 *     Snake.printboard(myBoard);
 *     validInput = Snake.userMove(myBoard);
 *     if (Snake.checkWin(myBoard)) {
 *         System.out.println("You win! :)");
 *         return;
 *     }
 * }
 * System.out.println("You lose :(");
 */
public class Snake {

    static PrintWriter printWriter = new PrintWriter(System.out, true);
    static Scanner in = new Scanner(System.in);
    static Random rng = new Random();


    /**
     * Print the board.
     * Snake: Green Square:  🟩 (U+1F7E9)
     * Food:  Yellow Square: 🟨 (U+1F7E8)
     * Field: Black Square:  ⬛ (U+2B1B)
     * @param board
     */
    public static void printboard(int[][] board) {
        for (int[] row : board) {
            String toPrint = "";
            for (int cell : row) {
                switch (cell) {
                    case 0:     // Field
                        toPrint += "⬛";
                        break;
                    case -1:    // Food
                        toPrint += "🟨";
                        break;
                    default:    // Snake
                        toPrint += "🟩";
                        break;
                }
            }
            printWriter.println(toPrint); // we need to use printWriter.println() instead of System.out.println() to avoid the Unicode characters being printed as question marks
        }
    }

    /**
     * Initialize the board. Set all cells to 0 and place the snake at a random position.
     * @param board The board to initialize.
     */
    @SuppressWarnings("unused")
    public static void initBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                cell = 0; // field
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
     * @return true if the move is valid, false otherwise.
     */
    public static boolean userMove(int[][] board) {
        int[] oldPosition = snakeHead(board);
        int[] newPosition = oldPosition.clone();
        System.out.println("Enter w, a, s, or d to move:");
        String move = in.nextLine();
        newPosition = calculateNewPosition(move, newPosition);
        if (!isValidMove(newPosition, board)) {
            return false;
        }
        handleMove(newPosition, oldPosition, board);
        return true;
    }

    /**
     * Calculate the new position of the snake's head based on user input.
     * @param move The user's input as a String. Can be "w", "a", "s", or "d".
     * @param currentPosition The current position of the snake's head as an array of two integers.
     * @return The new position of the snake's head as an array of two integers.
     */
    public static int[] calculateNewPosition(String move, int[] currentPosition) {
        int[] newPosition = currentPosition.clone();
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
            case "d":
                newPosition[1]++; // move right
                break;
            default:
                System.out.println("Invalid input, try again:");
                break;
        }
        return newPosition;
    }

    /**
     * Check if the new position of the snake's head is valid, i.e. if it is within the boundaries of the board.
     * @param newPosition The new position of the snake's head as an array of two integers.
     * @param board The board the snake is on.
     * @return true if the move is valid, false otherwise.
     */
    public static boolean isValidMove(int[] newPosition, int[][] board) {
        int row = newPosition[0];
        int col = newPosition[1];
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }

    /**
     * Handle the move of the snake based on the new position of the snake's head.
     * @param newPosition The new position of the snake's head as an array of two integers.
     * @param oldPosition The old position of the snake's head as an array of two integers.
     * @param board The board the snake is on.
     */
    public static void handleMove(int[] newPosition, int[] oldPosition, int[][] board) {
        int row = newPosition[0];
        int col = newPosition[1];
        int oldValue = board[oldPosition[0]][oldPosition[1]];
        int newValue = oldValue + 1; // we increment the value of the snake's head by one, so that after one round of starving, it has effectively moved forward by one cell, unless it has eaten food, in which case it grows by one cell in the moving direction.
        switch (board[row][col]) {
            case -1: // food
                board[row][col] = newValue; // move by one cell
                feedSnake(board); // this offsets the starving of the snake which always takes place after moving
                putFood(board);
                break;
            case 0: // field
                board[row][col] = newValue; // move by one cell
                break;
            default:
                break;
        }
        starveSnake(board);
    }

    /**
     * Main method to play the game.
     * @param args
     */
    public static void main(String[] args) {

        int N = 9; // board size is N x N

        int[][] myBoard = new int[N][N];
        initBoard(myBoard);
        putFood(myBoard);
        boolean validInput = true;
        while (validInput) {
            System.out.flush(); // clear the console
            printboard(myBoard);
            validInput = userMove(myBoard);
            if (checkWin(myBoard)) {
                System.out.println("You win! :)");
                return;
            }
        }
        System.out.println("You lose :(");
    }
}
