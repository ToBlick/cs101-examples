package videogame;

import java.util.Random;
import java.util.Scanner;

import videogame.characters.Character;
import videogame.characters.Dragon;
import videogame.characters.Knight;
import videogame.characters.Wizard;

public class Play {

    static Scanner in = new Scanner(System.in);
    static Random rand = new Random();
    public static void main(String[] args) {
        System.out.println("What character would you like to play as?");
        String character = in.nextLine();
        Character Player;
        switch (character) {
            case "Knight":
                Player = new Knight();
                break;
            case "Wizard":
                Player = new Wizard();
                break;
            case "Dragon":
                Player = new Dragon();
                break;
            default:
                System.out.println("Invalid character. Defaulting to Knight.");
                Player = new Knight();
                break;
        }
        Character Opponent = getOpponent(Player);
        System.out.println(Player.toString() + "\t \t" + Opponent.toString());
        System.out.println("Type 1 to attack, 2 to defend, 3 for special abilities, and ? for help.");;
        while (Player.getHealth() > 0 && Opponent.getHealth() > 0) {
            boolean askingForInput = true;
            while (askingForInput) {
                String action = in.nextLine();
                switch (action) {
                    case "1":
                        Player.attack(Opponent);
                        askingForInput = false;
                        break;
                    case "2":
                        Player.defend();
                        askingForInput = false;
                        break;
                    case "3":
                        Player.special(Opponent);
                        askingForInput = false;
                        break;
                    case "?":
                        Player.info();
                        break;
                    default:
                        System.out.println("Invalid action. Type 1 to attack, 2 to defend, 3 for special abilities, and ? for help.");
                        break;
                }
            }
            if (Opponent.getHealth() > 0) {
                switch (rand.nextInt(3)) {
                    case 0:
                        Opponent.attack(Player);
                        break;
                    case 1:
                        Opponent.defend();
                        break;
                    default:
                        Opponent.special(Player);
                        break;
                }
            }
        }
        System.out.println("Game over.");
        if (Player.getHealth() > 0) {
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