package entity;

public class Entity {
    protected int positionX;
    protected int positionY;
    protected int width;
    protected int height;
    protected String route;

    public Entity(){
    }

    public String getRoute() { return this.route; }

    public void setPosition(final int positionX, final int positionY, final int width, final int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }


}
