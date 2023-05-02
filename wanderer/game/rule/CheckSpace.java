package game.rule;

import game.PositionedImage;
import game.model.Position;

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
    public static boolean checkNextStep(int num, Position position , List<PositionedImage> wall){
        return isOutside(num) || isSamePos(position,wall);
    }
}
