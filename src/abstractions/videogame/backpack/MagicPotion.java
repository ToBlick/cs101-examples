package abstractions.videogame.backpack;

import abstractions.videogame.mechanics.EffectTypes;
import abstractions.videogame.mechanics.Effect;

public class MagicPotion extends Item implements Usable{

    public void info() {
        System.out.println("Magic potion that restores health.");
    }

    public Effect getEffect() {
        return new Effect(EffectTypes.BONUSHEALTH, 10);
    }

    public MagicPotion() {
        this.name = "Magic Potion";
        this.value = 0;
        this.weight = 1;
    }

}
