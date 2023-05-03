package game.model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    public void setPlusX(int x) {
        this.x = x + this.x;
    }
    public void setMinusX(int x) {
        this.x = this.x - x;
    }
    public void setPlusY(int y) {
        this.y = this.y + y;
    }
    public void setMinusY(int y) {
        this.y = this.y - y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
