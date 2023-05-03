package game.rule;

import game.PositionedImage;
import game.model.Position;
import game.model.charactrer.Character;

import java.util.List;

public class CheckSpace {

    private static boolean isOutside(int num){
        return !(num >= 0 && num <= 432);
    }

    private static boolean isSamePos(Position position , List<PositionedImage> wall){
        for(PositionedImage p : wall){
            if (p.getPosition().equals(position)) return true;
        }
        return false;
    }
    public static boolean isSamePosForFight(Character char1, Character char2){
        return char1.getPosition().equals(char2.getPosition());
    }
    public static boolean checkNextStep(int num, Position position , List<PositionedImage> wall){
        return isOutside(num) || isSamePos(position,wall);
    }
}
