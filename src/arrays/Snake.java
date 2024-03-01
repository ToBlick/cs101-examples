package arrays;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Snake {

    static PrintWriter printWriter = new PrintWriter(System.out,true);
    static Scanner in = new Scanner(System.in);
    static Random rng = new Random();

    public static void printGame(int[][] game) {
        for (int[] row : game) {
            String toPrint = "";
            for (int cell : row) {
                if (cell == 0) {
                    toPrint += "⬛";
                } else if (cell == -1) {
                    toPrint += "🟨";
                } else {
                    toPrint += "🟩";
                }
            }
            printWriter.println(toPrint);
        }
    }

    public static void initGame(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
               game[i][j] = 0;
            }
        }
        int i = rng.nextInt(game.length);
        int j = rng.nextInt(game[i].length);
        game[i][j] = 1;
    }

    public static void putFood(int[][] game) {
        while (true) {
            int i = rng.nextInt(game.length);
            int j = rng.nextInt(game[i].length);
            if (game[i][j] == 0) {
                game[i][j] = -1;
                return;
            }
        }
    }
    
    public static int[] snakeHead(int[][] game) {
        int max = 0;
        int[] position = {0, 0};
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j] > max) {
                    max = game[i][j];
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    public static boolean checkWin(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j] == 0 || game[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void feedSnake(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j] > 0) {
                    game[i][j]++;
                }
            }
        }
    }

    public static void starveSnake(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j] > 0) {
                    game[i][j]--;
                }
            }
        }
    }

    public static boolean userMove(int[][] game) {
        int[] oldPosition = snakeHead(game);
        int[] newPosition = snakeHead(game);
        System.out.println("Enter w, a, s, or d to move:");
        String move = in.nextLine();
        switch (move) {
            case "w":
                newPosition[0]--;
                break;
            case "s":
                newPosition[0]++;
                break;
            case "a":
                newPosition[1]--;
                break;
            case "d":
                newPosition[1]++;
                break;
            default:
                System.out.println("Invalid input, try again:");
                return true;
        }
        // validate move
        if ( newPosition[0] > game.length - 1 ||
             newPosition[0] < 0 ||
             newPosition[1] > game[newPosition[0]].length - 1 ||
             newPosition[1] < 0 ) {
                return false;
        }
        switch (game[newPosition[0]][newPosition[1]]) {
            case -1:
                feedSnake(game);
                putFood(game);
                break;
            case 0:
                break;
            default:
                return false;
        }
        // move
        game[newPosition[0]][newPosition[1]] = game[oldPosition[0]][oldPosition[1]] + 1;
        starveSnake(game);
        return true;
    }

    public static void main(String[] args) {

        int[][] myGame = new int[10][10];
        initGame(myGame);
        putFood(myGame);
        boolean validInput = true;
        while (validInput) {
            System.out.flush();
            printGame(myGame);
            validInput = userMove(myGame);
            if (checkWin(myGame)) {
                System.out.println("You win! :)");
                return;
            }
        }
        System.out.println("You lose :(");
    }
}
