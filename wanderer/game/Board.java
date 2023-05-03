package game;

import game.builder.MapBuilder;
import game.builder.WallBuilder;
import game.model.Orient;
import game.model.Position;
import game.model.charactrer.Boss;
import game.model.charactrer.Character;
import game.model.charactrer.Hero;
import game.rule.Battle;
import game.rule.CheckSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

    static Hero hero;
    static Boss boss;
    List<PositionedImage> map;
    List<PositionedImage> wall;

    public Board() {
        Position pos = new Position(0,0);
        hero = new Hero(pos,"wanderer-java/img/hero-down.png");
        Position pos2= new Position(432,432);
        boss = new Boss(pos2,"wanderer-java/img/boss.png",2);
        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
        map = new ArrayList<>();
        wall =  new ArrayList<>();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        //     graphics.fillRect(testBoxX, testBoxY, 100, 100); // that is the size
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
        //       PositionedImage image = new PositionedImage("wanderer-java/img/wall.png", 300, 300); // where do I want to display this

        MapBuilder.buildMap(map,graphics);
        WallBuilder.buildWall(wall,graphics);
        PositionedImage heroImage = new PositionedImage(hero.getUrl(), hero.getPosition().getX(), hero.getPosition().getY()); // where do I want to display this
        heroImage.draw(graphics);
        PositionedImage badGuy = new PositionedImage(boss.getUrl(), boss.getPosition().getX(),boss.getPosition().getY());
        badGuy.draw(graphics);
        // Each Png is 72X72
        // First the map, then the wall, then the player
    }

    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        // Notice (at the top) that we can only do this
        // because this Board class (the type of the board object) is also a KeyListener
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        Position newPosition;

        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            newPosition = new Position(hero.getPosition().getX(),hero.getPosition().getY() - MapBuilder.pixelSize);
            if(CheckSpace.checkNextStep(hero.getPosition().getY() - MapBuilder.pixelSize,newPosition, wall)){
                hero.setUrl(Orient.DOWN.getUrl());
            }else {
                hero.getPosition().setMinusY(MapBuilder.pixelSize);

                hero.setUrl(Orient.UP.getUrl());
            }
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            newPosition = new Position(hero.getPosition().getX(),hero.getPosition().getY() + MapBuilder.pixelSize);
            if(CheckSpace.checkNextStep(hero.getPosition().getY() + MapBuilder.pixelSize,newPosition, wall)){
               hero.setUrl(Orient.UP.getUrl()) ;
            }else {
                hero.getPosition().setPlusY(MapBuilder.pixelSize);
                hero.setUrl(Orient.DOWN.getUrl());
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            newPosition = new Position(hero.getPosition().getX() -  MapBuilder.pixelSize,hero.getPosition().getY());
            if(CheckSpace.checkNextStep(hero.getPosition().getX() - MapBuilder.pixelSize,newPosition, wall)){
                hero.setUrl( Orient.RIGHT.getUrl());
            }else {
                hero.getPosition().setMinusX(MapBuilder.pixelSize);
                hero.setUrl(Orient.LEFT.getUrl());

            }
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            newPosition = new Position(hero.getPosition().getX() +  MapBuilder.pixelSize,hero.getPosition().getY());
            if(CheckSpace.checkNextStep(hero.getPosition().getX() + MapBuilder.pixelSize,newPosition, wall)){
                hero.setUrl(Orient.LEFT.getUrl());
            }else {
                hero.getPosition().setPlusX(MapBuilder.pixelSize);
                hero.setUrl(Orient.RIGHT.getUrl());
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && CheckSpace.isSamePosForFight(hero,boss)){
            System.out.println("sikerult");
            fight(hero,boss);
        }

        // and redraw to have a new picture with the new coordinates
        repaint();

    }
    private static void fight(Character c1, Character c2){
        boolean result = Battle.battle(c1,c2);
        if(result){
            if(c1.isDead()){
                c1.getPosition().setX(600);
                c1.getPosition().setY(600);
                c2.levelUp();
            }else{
                c2.getPosition().setX(600);
                c2.getPosition().setY(600);
                c1.levelUp();
            }
        }
        System.out.println("hero: " + hero.toString());
        System.out.println("hero: " + boss.toString());
    }
}