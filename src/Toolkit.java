import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                g.setColor(Keys.getCreatureColors()[0]);
            }
            else if (creature.getSex() == 1) {
                g.setColor(Keys.getCreatureColors()[1]);
            }

        }

        g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(creature_size), (int) Math.round(creature_size));

    }
    public static void drawFood(Graphics g, Food food, double scale, int x, int y) {
        int food_size = food.getSize();
        int dangerous = food.getDangerous();

        if(dangerous == 0) {
            g.setColor(Keys.getFoodColors()[0]);
        }
        /*else if(dangerous == 1) {
            g.setColor(Color.ORANGE);
        }
        else if(dangerous == 2) {
            g.setColor(Color.RED);
        }*/
        else {
            g.setColor(Keys.getFoodColors()[1]);
        }
        g.fillRect((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(food_size), (int) Math.round(food_size));
    }

    public static List<Creature> generate(int size_x, int size_y) {
        progress("Generate Creatures");
        List<Creature> all = Collections.synchronizedList(new ArrayList<Creature>());


        for(int i = 0; i < Keys.getAmountCreatures(); i++) {
            int ID = i;
            int[] position = new int[]{randomPos(size_x),randomPos(size_y)};
            int sex = generateSex();

            Creature creature = new Creature(ID, Keys.getENERGY(), position, Keys.getSIGHT(), sex, Keys.getCreatureSize());
            System.out.println("\t" + creature.getId() + ". " + "ENERGY: " + creature.getEnergy() + " - SIGHT: " + Keys.getSIGHT() + " - SEX: " + creature.getSex() + " - SIZE: " + creature.size);
            all.add(creature);
        }
        progress("Finished");
        return all;
    }

    public static List<Food> generateFood(int size_x, int size_y) throws NumberFormatException {
        progress("Generate Food");
        List<Food> foods = Collections.synchronizedList(new ArrayList<Food>());
        for(int i = 0; i < Keys.getAmountFood(); i++) {
            int f_id = i;
            int[] position = new int[]{randomPos(size_x),randomPos(size_y)};
            //int dangerous = generateSex();

            Food food = new Food(f_id, Keys.getEnergyPerFood(), generateDanger(),  position);

            foods.add(food);
        }

        progress("Finished");
        return foods;
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
    public static boolean isNextTo(Creature creature, Food food) {
        boolean b;
        int[] position1 = creature.getPosition();
        int[] position2 = food.getPosition();

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
    public static int[][] surroundings(Creature creature, List<Creature> all, List<Food> foods) {
        int size = creature.getSight() * 2 + 1;
        int[][] surround = new int[size][size];
        int[] position = creature.getPosition();
        int[] currentPos = new int[2];
        int tempx, tempy;
        for(int i = 0; i < size; i++) {
            for(int u = 0; u < size; u++) {
                currentPos[0] = position[0] + (u - creature.getSight());
                currentPos[1] = position[1] + (i - creature.getSight());


                    for (Creature creature1 : all) {
                        int[] position2 = creature1.getPosition();
                        if (Arrays.equals(creature1.getPosition(), currentPos)) {
                            if(creature1.getId() == creature.getId()) {
                                surround[u][i] = -1;
                            }
                            else {
                                surround[u][i] = 1;
                            }

                        }
                    }

                    for (Food food : foods) {
                        if (Arrays.equals(food.getPosition(), currentPos)) {
                            surround[u][i] = 2;
                        }
                    }

                System.out.println("");
            }
        }

        return surround;
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
    public static int generateDanger() {
        int danger = (int) (Math.random() * 2);
        return danger;
    }

    public static boolean checkCiv(List<Creature> all) {
        boolean male = false;
        boolean female = false;
        synchronized (all) {
            for (Creature creature : all) {
                if(creature.getSex() == 0) {
                    male = true;
                }
                else {
                    female = true;
                }
            }
            if(!(male && female))  {
                return false;
            }
            else {
                return true;
            }

        }
    }



}
