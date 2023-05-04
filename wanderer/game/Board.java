package game;

import game.builder.CharLoader;
import game.builder.MapBuilder;
import game.builder.ScoreBoard;
import game.model.Movement;
import game.model.Position;
import game.model.charactrer.Boss;
import game.model.charactrer.Character;
import game.model.charactrer.Hero;
import game.model.charactrer.Skeleton;
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
    static List<PositionedImage> wall;

    private static int numOfDead = 0;
    private static int stepCounter = 0;

    private static int mapLevel = 0;

    private static List<Skeleton> horde;
    public Board() {
        Position pos = new Position(0,0);
        hero = new Hero(pos,"wanderer-java/img/hero-down.png");
        Position pos2= new Position(432+(mapLevel * 72),432+(mapLevel * 72));
        boss = new Boss(pos2,"wanderer-java/img/boss.png",2);
        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
        map = new ArrayList<>();
        wall =  new ArrayList<>();
        horde = new ArrayList<>();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        MapBuilder.buildMap(map,wall,graphics,mapLevel);
        CharLoader.charLoader(hero,boss,graphics,horde,mapLevel);
        ScoreBoard.showResult(graphics,hero,mapLevel);
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
            Movement.moveUp(hero,wall,mapLevel);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            Movement.moveDown(hero,wall,mapLevel);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Movement.moveLeft(hero,wall,mapLevel);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Movement.moveRight(hero,wall,mapLevel);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(CheckSpace.isSamePosForFight(hero,boss)){
                fight(hero,boss);
            }else{
                for (Skeleton skeleton : horde) {
                    if (CheckSpace.isSamePosForFight(hero, skeleton)) {
                        fight(hero, skeleton);
                    }
                }
            }

        }
        if(stepCounter % 2 == 0){
            NPCStep(mapLevel,boss);
            for(Skeleton s : horde){
                NPCStep(mapLevel,s);
            }
        }
        stepCounter++;
        System.out.println(horde.size());
        // and redraw to have a new picture with the new coordinates
        repaint();

    }
    private static void fight(Character c1, Character c2){
        boolean result = Battle.battle(c1,c2);
        if(result){
            if(c1.isDead())died(c2,c1);
            else died(c1,c2);
        }
    }

    private static void died(Character c1, Character c2){
        c2.getPosition().setX(600 + numOfDead * 10);
        c2.getPosition().setY(600 + numOfDead * 10);
        if(c1 instanceof Hero){
            c1.levelUp();
            if(c2 instanceof Boss){
                mapLevel = mapLevel + 1;
                CharLoader.nextMapCharLoader(hero, boss,horde, mapLevel);
            }
        }else{
            // End the game
           System.exit(0);
        }
        numOfDead++;
    }

    private void NPCStep(int level, Character character){
        if(!CheckSpace.isSamePosForFight(hero,character)){
            int chance = (int) (Math.random() * ( 5 - 1 ))+1;
            System.out.println("Level: "+ level);
            for(int i = 0; i <= level; i++){
                switch (chance) {
                    case 1 -> Movement.moveUp(character, wall,mapLevel);
                    case 2 -> Movement.moveDown(character, wall,mapLevel);
                    case 3 -> Movement.moveRight(character,wall ,mapLevel);
                    case 4 -> Movement.moveLeft(character, wall,mapLevel);
                }
            }
        }
    }
}