package game.model.charactrer;

import game.model.Position;

public class Boss extends Character{

    public Boss(Position position, String url, int level) {
        int chance = (int) (Math.random() * ( 6 -1)) + 1;
        this.position = position;
        this.url = url;
        this.HP = 2 * level* chance + chance;
        this.DP = level/2 * chance + chance / 2;
        this.SP = level * chance + level;
    }
}
