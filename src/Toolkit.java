import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        float sizeReal = 4;
        //if (isNest) {
        //g.setColor(Color.BLUE);
        //   g.fillRect((int) Math.round(x * scale - sizeReal / 2), (int) Math.round(y * scale - sizeReal / 2), (int) Math.round(sizeReal * 2), (int) Math.round(sizeReal * 2));
        //}

            //g.setColor(Color.getHSBColor(0.3f, 1f * amountFood / maxFood, 0.8f));// je mehr Futter, desto intensiver der Farbton
            g.setColor(Color.GREEN);
            g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(sizeReal), (int) Math.round(sizeReal));

    }
    public static void drawCreature(Graphics g, Creature creature, double scale, float sizeReal, int x, int y) {
        float creature_size = creature.getSize();
        g.setColor(Color.BLACK);
        if(creature.isDead()) {
            g.setColor(Color.RED);
        }
        else {
            if(creature.getSex() == 0) {
                g.setColor(Color.BLUE);
            }
            else if (creature.getSex() == 1) {
                g.setColor(Color.MAGENTA);
            }

        }


        g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(creature_size), (int) Math.round(creature_size));

    }

    public static List<Creature> generate(int AMOUNT_CREATURES, int ENERGY, int SIGHT, int size_x, int size_y) {
        progress("Generate Creatures");
        List<Creature> all = Collections.synchronizedList(new ArrayList<Creature>());


        for(int i = 0; i < AMOUNT_CREATURES; i++) {
            int ID = i;
            int[] position = new int[]{randomPos(size_x),randomPos(size_y)};
            int sex = generateSex();
            float size = (float) (Math.random() * 9) + 7;
            Creature creature = new Creature(ID, ENERGY, position, SIGHT, sex, size);
            System.out.println("\t" + creature.getId() + ". " + "ENERGY: " + creature.getEnergy() + " - SIGHT: " + SIGHT + " - SEX: " + creature.getSex() + " - SIZE: " + creature.size);
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

        int x;
        do { // garantiert, dass keine Futterquelle auf dem Feld des Nestes ist
            x = (int) (Math.random() * max);
        } while (x == 2);
        return x;
    }

    public static boolean isNextTo(Creature cr1, Creature cr2) {
        boolean b;
        int[] position1 = cr1.getPosition();
        int[] position2 = cr2.getPosition();

        int p1x = position1[0]; int p1y = position1[1];

        int p2x = position2[0]; int p2y = position2[1];

        if((p1x - p2x == 1 && p1y == p2y)||( p1x - p2x == -1 && p1y == p2y)) b = true;
        else if ((p1y - p2y == 1 && p1x == p2x)||( p1y - p2y == -1 && p1x == p2x)) {
            b = true;
        }
        else b = false;

        return b;
    }

    // return a random m-by-n matrix with values between 0 and 1
    // use to fill matrix with random weights
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                a[i][j] = Math.random();
            }
        return a;
    }

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return B = A^T
    public static double[][] transpose(double[][] x) {
        int m = x.length;
        int n = x[0].length;
        double[][] a = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[j][i] = x[i][j];
        return a;
    }

    //activation function
    public static double[][] sigmoid(double[][] x) {
        int m = x.length;
        int n = x[0].length;
        double[][] o = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                o[i][j] = 1 / (1 + Math.exp(-(x[i][j])));
        return o;
    }

    public static int getID(List<Creature> all) {
        int id = 0;
        synchronized (all) {
            for (Creature creature : all) {
                if (creature.getId() > id) {
                    id = creature.getId();
                }
            }

        }
        return id;
    }

    public static int generateSex() {
        int sex = (int)(Math.random() * 2);
        return sex;
    }

}
