package game.rule;

public class CheckSpace {

    public static boolean isOutside(int num){
        return !(num >= 0 && num <= 432);
    }
}
