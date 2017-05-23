import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Actions {
    static int size_x, size_y;
    static Graphics g;
    public Actions(int size_x, int size_y, Graphics g) {
        this.size_x = size_x;
        this.size_y = size_y;
        this.g = g;
    }
    public static void move(ArrayList<Creature> all, Creature creature, int size_x, int size_y ) throws InterruptedException {
        if(creature.isDead() != true) {
            ArrayList<int[]> moves = new ArrayList<int[]>();
            moves.add(new int[]{1, 0});
            moves.add(new int[]{0, 1});
            moves.add(new int[]{-1, 0});
            moves.add(new int[]{0, -1});

            int rand = (int) (Math.random() * moves.size());
            int[] newPos = moves.get(rand);
            int[] currentPos = creature.getPosition();
            int x = currentPos[0] + newPos[0];
            int y = currentPos[1] + newPos[1];
            if (x > size_x || y > size_y) {
                die(all, creature);
            } else {
                creature.setPosition(new int[]{x, y});
                creature.setEnergy(creature.getEnergy() - 1);
            }
        }
        else {

        }

    }
    public static void grab(Creature creature) {

    }
    public static void eat(Creature creature) {

    }
    public static void reproduce(Creature creature, ArrayList<Creature> all) {

    }
    /*public static void die(Creature creature, ArrayList<Creature> all) {
        for(Creature cr : all) {
            if(creature.getId() == cr.getId()) {
                all.remove(cr);
            }
        }
    }*/
    public static void die(ArrayList<Creature> all, Creature creature) throws InterruptedException {
        ArrayList<Creature> alls = all;
        creature.setDead(true);
        //Thread.sleep(5000);
        /*for(Creature cr : alls) {
            if(creature.getId() == cr.getId()) {
                all.remove(cr);
            }
        }*/

    }
    public static void idle() {

    }
}
