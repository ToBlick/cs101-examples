package object_orientation.snake_game;

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
    /**
     * Main method to play the game.
     * @param args
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {

            Board game = new Board(4,4);
            while (game.legalGame()) {
                game.printBoard();
                game.userMove();
                if (game.checkWin()) {
                    System.out.println("You win! :)");
                    break;
                }
            }
            if (!game.checkWin()) {
                System.out.println("You lose :(");
            }
            System.out.println("The snake grew to a length of " + game.snakeLength() + " cells.");
            System.out.println("The highscore is " + game.getHightscore() + " cells.");
            System.out.println("Do you want to play again? [y/n]");
            String userInput = in.nextLine();
            keepPlaying = userInput.equals("y");
        }
        in.close();
    }
}