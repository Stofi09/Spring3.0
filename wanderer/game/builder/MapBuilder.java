package game.builder;

import game.PositionedImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {

    public static final int pixelSize = 72;

    public static void buildMap(List<PositionedImage> map, Graphics graphics, int mapLevel){
        int tempPosX = 0;
        int tempPosY = 0;
        for(int i = 0; i < 7 + mapLevel*2; i++){
            for(int j = 0; j < 7 + mapLevel*2 ; j++){
                PositionedImage positionedImage = new PositionedImage("wanderer-java/img/floor.png", tempPosX, tempPosY); // where do I want
                map.add(positionedImage);
                positionedImage.draw(graphics);
                tempPosX = tempPosX + pixelSize;
            }
            tempPosY = tempPosY + pixelSize;
            tempPosX = 0;
        }
    }
}
