import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Toolkit {
    public static void progress(String text) {
        System.out.println("--- " + text + " ---");
    }
    public static void step(String text) {
        System.out.println("\t> " + text);
    }


    public static void drawFields(Graphics g, double scale, int maxFood, int amountFood, int x, int y) {
        float sizeReal = 1;
        //if (isNest) {
        //g.setColor(Color.BLUE);
        //   g.fillRect((int) Math.round(x * scale - sizeReal / 2), (int) Math.round(y * scale - sizeReal / 2), (int) Math.round(sizeReal * 2), (int) Math.round(sizeReal * 2));
        //}
        if (amountFood > 0) {
            //g.setColor(Color.getHSBColor(0.3f, 1f * amountFood / maxFood, 0.8f));// je mehr Futter, desto intensiver der Farbton
            g.setColor(Color.GREEN);
            g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(sizeReal), (int) Math.round(sizeReal));
        }
    }
    public static void drawCreature(Graphics g, double scale, float sizeReal, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(sizeReal), (int) Math.round(sizeReal));

    }

    public static ArrayList<Creature> generate(int AMOUNT_CREATURES, int ENERGY, int SIGHT) {
        progress("Generate Creatures");
        ArrayList<Creature> all = new ArrayList<>();

        int[] position = new int[]{0,0};
        for(int i = 0; i < AMOUNT_CREATURES; i++) {
            int ID = i;
            int sex = (int)(Math.random() * 2) ;
            Creature creature = new Creature(ID, ENERGY, position, SIGHT, sex);
            System.out.println("\t" + creature.getId() + ". " + creature.getEnergy() + " - " + SIGHT + " - " + creature.getSex());
            all.add(creature);
        }
        progress("Finished");
        return all;
    }

    public static Point[] generateFood(Point size, String str_amount) throws NumberFormatException {
        progress("Generate Food");
        int amount = Integer.parseInt(str_amount);
        Point[] p = new Point[amount];
        for (int i = 0; i < amount; i++) {
            p[i] = new Point(randomPos(size.x), randomPos(size.y));
        }

        progress("Finished");
        return p;
    }
    public static int[] foodPerSource(int amount, int FOOD_PER_SOURCE) {

        int[] p = new int[amount];
        for (int i = 0; i < amount; i++) {
            p[i] = FOOD_PER_SOURCE;
        }
        return p;
    }

    public static int randomPos(int max) {//, int notAllowed) {
        progress("Create Random Position");
        int x;
        do { // garantiert, dass keine Futterquelle auf dem Feld des Nestes ist
            x = (int) (Math.random() * max);
        } while (x == 2);
        return x;
    }
}
