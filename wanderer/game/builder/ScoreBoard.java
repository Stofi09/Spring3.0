package game.builder;

import game.model.charactrer.Hero;

import java.awt.*;

public class ScoreBoard {


    public static void showResult(Graphics graphics, Hero hero){
        graphics.drawString("Hero Hp: "+ hero.getHP(), 600,100);
        graphics.drawString("Hero Dp: "+ hero.getDP(), 600,120);
        graphics.drawString("Hero Sp: "+ hero.getSP(), 600,140);
        graphics.drawString("Hero Level: "+ hero.getLevel(), 600,160);
    }
}
