package abstractions.videogame;

import java.util.Random;
import java.util.Scanner;

import abstractions.videogame.characters.Character;
import abstractions.videogame.characters.Dragon;
import abstractions.videogame.characters.Knight;
import abstractions.videogame.characters.Wizard;

public class Play {

    static Scanner in = new Scanner(System.in);
    static Random rand = new Random();
    public static void main(String[] args) {
        System.out.println("What character would you like to play as?");
        String selectedCharacter = in.nextLine();
        Character player;
        switch (selectedCharacter) {
            case "Knight":
                player = new Knight();
                break;
            case "Wizard":
                player = new Wizard();
                break;
            case "Dragon":
                player = new Dragon();
                break;
            default:
                System.out.println("Invalid character. Defaulting to Knight.");
                player = new Knight();
                break;
        }
        Character opponent = getOpponent(player);
        System.out.println(player.toString() + "\t \t" + opponent.toString());
        System.out.println("Type 1 to attack, 2 to defend, 3 for special abilities, and ? for help.");;
        while (player.getHealth() > 0 && opponent.getHealth() > 0) {
            boolean askingForInput = true;
            while (askingForInput) {
                String action = in.nextLine();
                switch (action) {
                    case "1":
                        player.attack(opponent);
                        askingForInput = false;
                        break;
                    case "2":
                        player.defend();
                        askingForInput = false;
                        break;
                    case "3":
                        player.special(opponent);
                        askingForInput = false;
                        break;
                    case "?":
                        player.info();
                        break;
                    default:
                        System.out.println("Invalid action. Type 1 to attack, 2 to defend, 3 for special abilities, and ? for help.");
                        break;
                }
            }
            if (opponent.getHealth() > 0) {
                switch (rand.nextInt(3)) {
                    case 0:
                        opponent.attack(player);
                        break;
                    case 1:
                        opponent.defend();
                        break;
                    default:
                        opponent.special(player);
                        break;
                }
            }
        }
        System.out.println("Game over.");
        if (player.getHealth() > 0) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose.");
        }
    }

    static Character getOpponent(Character Player) {
        Character Opponent;
            switch (rand.nextInt(3)) {
                case 0:
                    Opponent = new Dragon();
                    break;
                case 1:
                    Opponent = new Knight();
                    break;
                default:
                    Opponent = new Wizard();
                    break;
            }
            while ( (Player instanceof Knight && Opponent instanceof Knight) ||
                    (Player instanceof Wizard && Opponent instanceof Wizard) ||
                    (Player instanceof Dragon && Opponent instanceof Dragon) ) {
                switch (rand.nextInt(3)) {
                    case 0:
                        Opponent = new Dragon();
                        break;
                    case 1:
                        Opponent = new Knight();
                        break;
                    default:
                        Opponent = new Wizard();
                        break;
                }
            } // while
        return Opponent;
    }
}
