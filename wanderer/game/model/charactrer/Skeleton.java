package game.model.charactrer;

import game.model.Position;

public class Skeleton extends Character{
    public Skeleton(Position position, String url, int level) {
        int chance = (int) (Math.random() * ( 6 -1)) + 1;
        this.position = position;
        this.url = url;
        this.HP = 2 * level* chance;
        this.DP = level/2 * chance;
        this.SP = level * chance;
    }
}
