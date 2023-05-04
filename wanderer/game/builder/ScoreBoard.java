package game.builder;

import game.model.charactrer.Hero;

import java.awt.*;

public class ScoreBoard {


    public static void showResult(Graphics graphics, Hero hero, int mapLevel){
        int variable = 72 * mapLevel+2;
        graphics.drawString("Hero Hp: "+ hero.getHP(), 600 + variable,100);
        graphics.drawString("Hero Dp: "+ hero.getDP(), 600+ variable,120);
        graphics.drawString("Hero Sp: "+ hero.getSP(), 600+ variable,140);
        graphics.drawString("Hero Level: "+ hero.getLevel(), 600+ variable,160);
    }
}
