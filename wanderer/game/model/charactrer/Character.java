package game.model.charactrer;

import game.model.Position;

public abstract class Character {
   protected String url;
    protected Position position;
    protected  int HP;
    protected  int DP;
    protected int SP;
    protected  int level;
    protected boolean isDead = false;
    public void proba(){
        System.out.println(getHP());
    }

    public void getHit(int hit){
        this.HP = this.HP - hit;
        if(this.HP <= 0) this.isDead = true;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDP() {
        return DP;
    }

    public void setDP(int DP) {
        this.DP = DP;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
    public void levelUp(){
        int hpD6 = (int) (Math.random() * ( 6 -1))+1 ;
        this.HP = this.HP + hpD6;
        int dpD6 = (int) (Math.random() * ( 6 -1))+1 ;
        this.DP = this.DP + dpD6;
        int spD6 = (int) (Math.random() * ( 6 -1))+1 ;
        this.SP = this.SP + spD6;
    }
}
