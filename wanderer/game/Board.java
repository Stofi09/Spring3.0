package game;

import game.builder.MapBuilder;
import game.builder.ScoreBoard;
import game.builder.WallBuilder;
import game.model.Movement;
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
    static List<PositionedImage> wall;
    private static int numOfDead = 0;
    private static int stepCounter = 0;
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

       // graphics.drawString("Proba", 600, 600);
        ScoreBoard.showResult(graphics,hero);
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
            Movement.moveUp(hero,wall);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            Movement.moveDown(hero,wall);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Movement.moveLeft(hero,wall);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Movement.moveRight(hero,wall);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && CheckSpace.isSamePosForFight(hero,boss)){
            fight(hero,boss);
        }
        if(stepCounter % 2 == 0){
            System.out.println(stepCounter);
            int chance = (int) (Math.random() * ( 5 - 1 ))+1;
            NPCStep(chance,hero.getLevel(),boss);
        }
        stepCounter++;
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
        c1.levelUp();
        numOfDead++;
    }

    private void NPCStep(int num, int level, Character character){
        for(int i = 0; i <= level; i++){
            switch (num) {
                case 1 -> Movement.moveUp(character, wall);
                case 2 -> Movement.moveDown(character, wall);
                case 3 -> Movement.moveRight(character, wall);
                case 4 -> Movement.moveLeft(character, wall);
            }
    }
    }
}