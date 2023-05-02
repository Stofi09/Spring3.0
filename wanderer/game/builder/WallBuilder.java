package game.builder;

import game.PositionedImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WallBuilder {

    public static final int pixelSize = 72;

    private static void horizontal3(List<PositionedImage> walls, Graphics graphics,  int times){
        int tempPosX =  times * pixelSize;
       // int chance = (int) (Math.random() * (6 - 1 ));
        for(int i = 0; i < 2; i++){
            PositionedImage positionedImage = new PositionedImage("wanderer-java/img/wall.png", tempPosX, 2 * pixelSize); // where do I want
            walls.add(positionedImage);
            positionedImage.draw(graphics);
            tempPosX = tempPosX + pixelSize;
        }
    }
    private static void vertical3(List<PositionedImage> walls, Graphics graphics, int times){
        int tempPosY = times * pixelSize;
      //  int chance = (int) (Math.random() * (6 - 1 ));
        for(int i = 0; i < 2; i++){
            PositionedImage positionedImage = new PositionedImage("wanderer-java/img/wall.png", 2 * pixelSize, tempPosY); // where do I want
            walls.add(positionedImage);
            positionedImage.draw(graphics);
            tempPosY = tempPosY + pixelSize;
        }
    }
    public static void buildWall(List<PositionedImage> walls, Graphics graphics){

        for (int i = 1; i< 3; i++){
            horizontal3(walls,graphics, i);
            vertical3(walls,graphics, i);
        }
    }
}
