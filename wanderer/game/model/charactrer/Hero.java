package game.model.charactrer;

import game.model.Position;

public class Hero extends Character {


    public Hero(Position position, String url) {
        int chance = (int) (Math.random() * ( 6 -1)) + 1;
        this.position = position;
        this.url = url;
        this.HP = 20 + 3 * chance ;
        this.DP = 2 * chance;
        this.SP = 5 + chance;
    }

}
