import java.io.*;

public class Gulka implements Serializable {
    public static final long serialVersionUID = 112132444L;

    public float radius = 0.5f;
    public float x;
    public float y;
    private float dx;
    private float dy;

    public Gulka (float radius, float x, float y, float dx, float dy) {
        this.radius = radius;
        this.x = y;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void move () {
        x += dx;
        y += dy;
    }

    public boolean hit (Gulka g) {
        return (g.x-x)*(g.x-x) + (g.y-y)*(g.y-y) <= (radius+g.radius)*(radius+g.radius);
    }

    public String toString () {
        return radius+"["+x+","+y+"]";
    }
}
