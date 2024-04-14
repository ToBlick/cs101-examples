package videogame.mechanics;

public class Effect {

    protected EffectTypes type;
    protected int value;

    public Effect(EffectTypes type, int value) {
        this.type = type;
        this.value = value;
    }

}
