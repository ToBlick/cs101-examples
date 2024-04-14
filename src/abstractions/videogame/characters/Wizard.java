package videogame.characters;

import videogame.mechanics.DamageTypes;

public class Wizard extends Character {

    private int energy = 10;

    public String getName() {
        return "Wizard";
    }

    public Wizard() {
        weakness = DamageTypes.SWORD;
    } 

    public Wizard(int health) {
        this.health = health;
        weakness = DamageTypes.SWORD;
    }

    public void info() {
        System.out.println("Wizard abilities:");
        System.out.println("1. Attack: Cast a spell. Does " + 25 + " damage.");
        System.out.println("2. Defend: Cast a magic protection spell.");
        System.out.println("3. Special: Medidate. Restore all energy.");
    }

    public void attack(Character target) {
        if (energy < 5) {
            System.out.println("The wizard is out of energy and meditates.");
            special(target);
            return;
        }
        energy -= 5;
        target.getHitByAttack(20, DamageTypes.MAGIC);
        System.out.println(this.toString() + " 💫 " + target.toString());
        System.out.println(health + "   " + target.getHealth());
    }

    public void defend() {
        if (energy < 5) {
            System.out.print("The wizard is out of energy. ");
            special(new Knight()); // dummy target
            return;
        }
        System.out.println("The wizard casts a protection spell.");
        energy -= 5;
        health += 10;
    }

    public void special(Character target) {
        System.out.println("The wizard meditates.");
        System.out.println(this.toString() + "   " + target.toString());
        energy = 10;
    }

    public boolean handleImmunity() {
        return false;
    }

    public String toString() {
        return "🧙";
    }

}
