import java.awt.*;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Field {
    public static float size;
    public static float SIZE_START;
    private int x, y, foodperq;

    public Field(int x, int y, int foodperq) {
        this.x = x;
        this.y = y;
        this.foodperq = foodperq;
    }

    public void draw(Graphics g, double scale, int maxFutter) {
        g.setColor(Color.BLUE);
        g.fillRect((int) Math.round(x * scale - size / 2), (int) Math.round(y * scale - size / 2), (int) Math.round(size * 2), (int) Math.round(size * 2));
    }



}
