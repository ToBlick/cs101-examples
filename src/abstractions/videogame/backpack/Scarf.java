package videogame.backpack;

import videogame.mechanics.EffectTypes;
import videogame.mechanics.Effect;

public class Scarf extends Item implements Usable, Equipment {

    public void info() {
        System.out.println("Cool scarf that can be used as a bandage if need be.");
    }

    public Effect getEffect() {
        return new Effect(EffectTypes.BONUSHEALTH, 5);
    }

    public Scarf() {
        this.name = "Scarf";
        this.value = 100;
        this.weight = 5;
    }

}
