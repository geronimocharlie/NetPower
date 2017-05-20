import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Toolkit {
    public void print(String text) {
        System.out.println(text);
    }

    public static void drawFields(Graphics g, double scale, int maxFood, int amountFood, int x, int y) {
        float sizeReal = 1;
        //if (isNest) {
        //g.setColor(Color.BLUE);
        //   g.fillRect((int) Math.round(x * scale - sizeReal / 2), (int) Math.round(y * scale - sizeReal / 2), (int) Math.round(sizeReal * 2), (int) Math.round(sizeReal * 2));
        //}
        if (amountFood > 0) {
            g.setColor(Color.getHSBColor(0.3f, 1f * amountFood / maxFood, 0.8f));// je mehr Futter, desto intensiver der Farbton
            g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(sizeReal), (int) Math.round(sizeReal));
        }
    }
    public static void drawCreature(Graphics g, double scale, float sizeReal, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(sizeReal), (int) Math.round(sizeReal));

    }

    public static ArrayList<Creature> generate(int AMOUNT_CREATURES, int ENERGY, int SIGHT) {
        ArrayList<Creature> all = new ArrayList<>();
        int sex = (int)(Math.random() * 1);
        int[] position = new int[]{0,0};
        for(int i = 0; i < AMOUNT_CREATURES; i++) {
            int ID = i;
            Creature creature = new Creature(ID, ENERGY, position, SIGHT, sex);
            all.add(creature);
        }
        return all;
    }
}
