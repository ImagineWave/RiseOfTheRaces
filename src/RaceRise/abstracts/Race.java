package RaceRise.abstracts;

import org.bukkit.entity.Player;

public interface HasRace {

}


//Decorator pattern
abstract class PlayerWithRaceDecorator implements Player, HasRace {

    private Player player;

    public PlayerWithRaceDecorator(Player player) {

    }

}

public abstract class Race {


    public abstract void useAbility();

    Player p;

    String name = p.getName();




}


class Item {

    public String attack; // [1...10]
    public String defence;// [3...7]
    public String stamina;// [0.1....0.75]


    public static Item createItem(String s1){
        return new Item(
                AttackGenerator.generateAttack(seed);
                AttackGenerator.generateAttack(seed);
                AttackGenerator.generateAttack(seed);
        );
    }

}

