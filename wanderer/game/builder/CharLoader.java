package game.builder;

import game.PositionedImage;
import game.model.Position;
import game.model.charactrer.Character;
import game.model.charactrer.Skeleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharLoader {


        public static void loadSkeletons(List<Skeleton> skeletons, int mapLevel, Graphics graphics){
                if (skeletons.isEmpty()) {
                        Position pos1 = new Position(360+(mapLevel*2*72),432+(mapLevel*2*72));
                        Position pos2 = new Position(432+(mapLevel*2*72),360+(mapLevel*2*72));
                        Position pos3 = new Position(360+(mapLevel*2*72),360+(mapLevel*2*72));
                        Skeleton skeleton1 = new Skeleton(pos1,"wanderer-java/img/skeleton.png",mapLevel);
                        Skeleton skeleton2 = new Skeleton(pos2,"wanderer-java/img/skeleton.png",mapLevel);
                        Skeleton skeleton3 = new Skeleton(pos3,"wanderer-java/img/skeleton.png",mapLevel);
                        skeletons.add(skeleton1);
                        skeletons.add(skeleton2);
                        skeletons.add(skeleton3);
                }
                PositionedImage skeleton1 = new PositionedImage(skeletons.get(0).getUrl(), skeletons.get(0).getPosition().getX(),skeletons.get(0).getPosition().getY());
                PositionedImage skeleton2 = new PositionedImage(skeletons.get(1).getUrl(), skeletons.get(1).getPosition().getX(),skeletons.get(1).getPosition().getY());
                PositionedImage skeleton3 = new PositionedImage(skeletons.get(2).getUrl(), skeletons.get(2).getPosition().getX(),skeletons.get(2).getPosition().getY());
                skeleton1.draw(graphics);
                skeleton2.draw(graphics);
                skeleton3.draw(graphics);

        }

}
