package abstractions.videogame.characters;

import abstractions.videogame.mechanics.DamageTypes;

public abstract class Character {

    protected int health = 100;
    protected DamageTypes weakness = null;

    public int getHealth() {
        return health;
    }

    public abstract void info();

    public abstract void attack(Character t);
    public abstract void defend();
    public abstract void special(Character t);
    public abstract boolean handleImmunity();
    public abstract String getName();

    public void getHitByAttack(int damage, DamageTypes damageType) {
        if (handleImmunity()) {
            // Do nothing
        } else {
            if (weakness == damageType) {
                damage *= 2;
            }
            System.out.println("The " + getName() + " takes " + damage + " damage.");
            health -= damage;
        }
    }
}
