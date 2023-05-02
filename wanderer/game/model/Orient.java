package game.model;

public enum Orient {
    UP("wanderer-java/img/hero-up.png"),DOWN("wanderer-java/img/hero-down.png"),RIGHT("wanderer-java/img/hero-right.png"),LEFT("wanderer-java/img/hero-left.png");
    private String url;
    Orient(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
