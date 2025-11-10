package abstractions.videogame.backpack;

public class PlotArmor extends Item implements Equippable{

    public PlotArmor() {
        this.name = "Plot Armor";
        this.value = 1000;
        this.weight = 10;
    }

    public void info() {
        System.out.println("This is plot armor. It makes you immune to damage.");
    }

}
