package game.model;

import game.PositionedImage;
import game.builder.MapBuilder;
import game.model.charactrer.Character;
import game.rule.CheckSpace;
import game.model.charactrer.Hero;
import java.util.List;

public class Movement {
  private static Position newPosition;
    public static void moveUp(Character character, List<PositionedImage> wall){
        newPosition = new Position(character.getPosition().getX(),character.getPosition().getY() - MapBuilder.pixelSize);
        if(CheckSpace.checkNextStep(character.getPosition().getY() - MapBuilder.pixelSize,newPosition, wall)){
           if(character instanceof Hero) character.setUrl(Orient.DOWN.getUrl());

        }else {
            character.getPosition().setMinusY(MapBuilder.pixelSize);

            if(character instanceof Hero)  character.setUrl(Orient.UP.getUrl());
        }
    }

    public static void moveDown(Character character, List<PositionedImage> wall){
        newPosition = new Position(character.getPosition().getX(),character.getPosition().getY() + MapBuilder.pixelSize);
        if(CheckSpace.checkNextStep(character.getPosition().getY() + MapBuilder.pixelSize,newPosition, wall)){
            if(character instanceof Hero)  character.setUrl(Orient.UP.getUrl()) ;
        }else {
            character.getPosition().setPlusY(MapBuilder.pixelSize);
            if(character instanceof Hero)  character.setUrl(Orient.DOWN.getUrl());
        }
    }
    public static void moveLeft(Character character, List<PositionedImage> wall){
        newPosition = new Position(character.getPosition().getX() -  MapBuilder.pixelSize,character.getPosition().getY());
        if(CheckSpace.checkNextStep(character.getPosition().getX() - MapBuilder.pixelSize,newPosition, wall)){
            if(character instanceof Hero) character.setUrl( Orient.RIGHT.getUrl());
        }else {
            character.getPosition().setMinusX(MapBuilder.pixelSize);
            if(character instanceof Hero) character.setUrl(Orient.LEFT.getUrl());

        }
    }
    public static void moveRight(Character character, List<PositionedImage> wall){
        newPosition = new Position(character.getPosition().getX() +  MapBuilder.pixelSize,character.getPosition().getY());
        if(CheckSpace.checkNextStep(character.getPosition().getX() + MapBuilder.pixelSize,newPosition, wall)){
            if(character instanceof Hero)  character.setUrl(Orient.LEFT.getUrl());
        }else {
            character.getPosition().setPlusX(MapBuilder.pixelSize);
            if(character instanceof Hero) character.setUrl(Orient.RIGHT.getUrl());
        }
    }
}
