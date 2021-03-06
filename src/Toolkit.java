import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Toolkit {
    public void progress(String text) {
        System.out.println("--- " + text + " ---");
    }
    public void step(String text) {
        System.out.println("\t> " + text);
    }

    public Toolkit() {

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
    public void drawCreature(Graphics g, Creature creature, double scale, float sizeReal, int x, int y) {
        float creature_size = creature.getSize();
        g.setColor(Color.BLACK);
        if(creature.isDead()) {
            g.setColor(Color.RED);
        }
        else {
            if(creature.getSex() == 0) {
                g.setColor(creature.getWorld().keys.getCreatureColors()[0]);
            }
            else if (creature.getSex() == 1) {
                if(creature.isPregnant()) {
                    g.setColor(Color.magenta);
                }
                else {
                    g.setColor(creature.getWorld().keys.getCreatureColors()[1]);
                }
            }

        }

        g.fillOval((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(creature_size), (int) Math.round(creature_size));

    }
    public void drawFood(Graphics g, Food food, double scale, int x, int y) {
        int food_size = food.getSize();
        int dangerous = food.getDangerous();

        if(dangerous == 0) {
            g.setColor(food.getWorld().keys.getFoodColors()[0]);
        }
        /*else if(dangerous == 1) {
            g.setColor(Color.ORANGE);
        }
        else if(dangerous == 2) {
            g.setColor(Color.RED);
        }*/
        else {
            g.setColor(food.getWorld().keys.getFoodColors()[1]);
        }
        g.fillRect((int) Math.round(x * scale), (int) Math.round(y * scale), (int) Math.round(food_size), (int) Math.round(food_size));
    }




    public static int randomPos(int max) {//, int notAllowed) {

        int x;
        do { // garantiert, dass keine Futterquelle auf dem Feld des Nestes ist // NEIN! -.-
            x = (int) (Math.random() * max);
        } while (x == 2);
        return x;
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
    public int[][] surroundings(Creature creature, List<Creature> all, List<Food> foods) {
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

    public boolean checkCiv(List<Creature> all) {
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
