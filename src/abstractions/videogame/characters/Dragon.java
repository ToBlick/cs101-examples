package videogame.characters;

import videogame.mechanics.DamageTypes;

public class Dragon extends Character {

    int baseDamage = 10;
    int bonusDamage = 0;

    boolean flying = false;

    public Dragon() {
        weakness = DamageTypes.MAGIC;
    }

    public Dragon(int health) {
        this.health = health;
        weakness = DamageTypes.MAGIC;
    }

    public String getName() {
        return "Dragon";
    }

    public void info() {
        System.out.println("Dragon abilities:");
        System.out.println("1. Attack: Breathe fire. Does " + (baseDamage + bonusDamage) + " damage.");
        System.out.println("2. Defend: Fly high. Avoid the next attack.");
        System.out.println("3. Special: Roar. Next attack does double damage.");
    }

    public String toString() {
        if (flying) {
            return "🐉🪽";
        } else
            return "🐉";
    }

    public void attack(Character target) {
        flying = false;
        target.getHitByAttack(10 + bonusDamage, DamageTypes.FIRE);
        bonusDamage = 0;
        System.out.println(this.toString() + " 🔥 " + target.toString());
        System.out.println(health + "   " + target.getHealth());
    }

    public void defend() {
        System.out.println("The dragon flies up.");
        flying = true;
    }

    public void special(Character target) {
        flying = false;
        System.out.println("The dragon roars.");
        System.out.println(this.toString() + "   " + target.toString());
        bonusDamage += baseDamage + bonusDamage;
    }

    public boolean handleImmunity() {
        if (flying) {
            System.out.println("The flying dragon avoids the attack.");
            return true;
        }
        return false;
    }

}
