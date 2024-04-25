package abstractions.videogame.characters;

import abstractions.videogame.mechanics.DamageTypes;

public class Knight extends Character {

    int baseDamage = 10;
    int bonusDamage = 0;

    boolean shieldUp = false;

    public Knight() {
        weakness = DamageTypes.FIRE;
    }

    public Knight(int health) {
        this.health = health;
        weakness = DamageTypes.FIRE;
    }

    public String getName() {
        return "Knight";
    }

    public void info() {
        System.out.println("Knight abilities:");
        System.out.println("1. Attack: Stab with sword. Does " + (baseDamage + bonusDamage) + " damage.");
        System.out.println("2. Defend: Raise shield. Blocks next attack.");
        System.out.println("3. Special: Sharpen sword. Subsequent attacks do 5 extra damage.");
    }

    public String toString() {
        if (shieldUp) {
            return "🛡️🤺";
        } else
            return "🤺";
    }

    public void attack(Character target) {
        shieldUp = false;
        target.getHitByAttack(10 + bonusDamage, DamageTypes.SWORD);
        System.out.println(this.toString() + " 🔪 " + target.toString());
        System.out.println(health + "   " + target.getHealth());
    }

    public void defend() {
        System.out.println("The knight raises their shield.");
        shieldUp = true;
    }

    public void special(Character target) {
        shieldUp = false;
        System.out.println("The knight sharpens their sword.");
        System.out.println(this.toString() + "   " + target.toString());
        bonusDamage += 5;
    }

    public boolean handleImmunity() {
        if (shieldUp) {
            System.out.println("The knight blocks the attack with their shield.");
            return true;
        }
        return false;
    }

}
