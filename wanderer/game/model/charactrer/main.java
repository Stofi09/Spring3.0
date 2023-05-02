package game.model.charactrer;

import game.model.Position;
import game.rule.Battle;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Position p = new Position(1,1);
        Hero hero = new Hero(p,"url");
        Boss boss = new Boss(p,"url",2);
        hero.proba();
        boss.proba();
        System.out.println("-------------------");
        System.out.println(Battle.battle(hero,boss));
        hero.proba();
        boss.proba();
    }
}
