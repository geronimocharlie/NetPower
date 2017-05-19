import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Main {
    public static ArrayList<Creature> all = new ArrayList<>();
    public static int SIGHT = 2;
    public static int ID = 0;
    public static int ENERGY = 50;
    public static int AMOUNT_CREATURES = 10;
    public static int AMOUNT_FOOD = 10;
    public static int FIELD_SIZE_START = 8;
    public static int CREATURE_SIZE_START = 4;

    private static int FPS = 60;
    private static int ACCURACY = 16;
    public static Point size = new Point(16, 16); //nest = new Point(anzNestX, anzNestY);
    public static void main(String[] args) {
        System.out.println("Start");

        generate();

    }

    public static void generate() {

        int sex = (int)(Math.random() * 1);
        int[] position = new int[]{0,0};
        for(int i = 0; i < AMOUNT_CREATURES; i++) {
            ID = i;
            Creature creature = new Creature(ID, ENERGY, position, SIGHT, sex);
            all.add(creature);
        }

        new World(all, FPS, ACCURACY, AMOUNT_FOOD, FIELD_SIZE_START, CREATURE_SIZE_START, size);
    }
}
