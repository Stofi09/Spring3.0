package game;

import game.builder.MapBuilder;
import game.builder.WallBuilder;
import game.model.Orient;
import game.rule.CheckSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;

    List<PositionedImage> map;
    List<PositionedImage> walls;

    private String playerUrl = "wanderer-java/img/hero-down.png";

    public Board() {
        testBoxX = 0;
        testBoxY = 0;

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
        map = new ArrayList<>();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        //     graphics.fillRect(testBoxX, testBoxY, 100, 100); // that is the size
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
        //       PositionedImage image = new PositionedImage("wanderer-java/img/wall.png", 300, 300); // where do I want to display this

        MapBuilder.buildMap(map,graphics);
        WallBuilder.buildWall(walls,graphics);
        PositionedImage heroImage = new PositionedImage(playerUrl, testBoxX, testBoxY); // where do I want to display this
        heroImage.draw(graphics);

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
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if(CheckSpace.isOutside(testBoxY - MapBuilder.pixelSize)){
                playerUrl = Orient.DOWN.getUrl();
            }else {
                testBoxY -= MapBuilder.pixelSize;
                playerUrl = Orient.UP.getUrl();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(CheckSpace.isOutside(testBoxY + MapBuilder.pixelSize)){
                playerUrl = Orient.UP.getUrl();
            }else {
                testBoxY += MapBuilder.pixelSize;
                playerUrl = Orient.DOWN.getUrl();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(CheckSpace.isOutside(testBoxX - MapBuilder.pixelSize)){
                playerUrl = Orient.RIGHT.getUrl();
            }else {
                testBoxX -= MapBuilder.pixelSize;
                playerUrl = Orient.LEFT.getUrl();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(CheckSpace.isOutside(testBoxX + MapBuilder.pixelSize)){
                playerUrl = Orient.LEFT.getUrl();
            }else {
                testBoxX += MapBuilder.pixelSize;
                playerUrl = Orient.RIGHT.getUrl();
            }
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

}