package game.rule;

import game.model.charactrer.Character;

public class Battle {


    public static boolean battle(Character char1, Character char2){
        int firstStrike = strike(char1.getSP(), char2.getDP());
        if(firstStrike > 0) char2.getHit(firstStrike);
        System.out.println(firstStrike);
        if(char2.isDead()) return true;
        int secondStrike = strike(char2.getSP(), char1.getDP());
        if(secondStrike > 0) char1.getHit(secondStrike);
        System.out.println(secondStrike);
        return char1.isDead();
    }
    private static  int strike(int sp, int dp){
        int chance = (int) (Math.random() * ( 6 -1))+1 ;
        int sv = 2 * chance + sp;
        return sv - dp;
    }
}
