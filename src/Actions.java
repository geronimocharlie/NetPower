import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Actions {

    static int size_x, size_y;
    static Graphics g;
    static Paint paint;
    static World world;
    public Actions(int size_x, int size_y, Graphics g) {
        this.size_x = size_x;
        this.size_y = size_y;
        this.g = g;
    }
    public Actions(Paint paint, World world) {
        this.paint = paint;
        this.world = world;
    }
    public static void move(List<Creature> all, Creature creature, int size_x, int size_y ) throws InterruptedException {
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
    public static void reproduce(Creature creature, java.util.List<Creature> all) {
        int id = Toolkit.getID(all);
        Creature baby = new Creature(id, 50, creature.getPosition(), 2, Toolkit.generateSex(), creature.getSize());
        all.add(baby);



    }
    /*public static void die(Creature creature, ArrayList<Creature> all) {
        for(Creature cr : all) {
            if(creature.getId() == cr.getId()) {
                all.remove(cr);
            }
        }
    }*/
    public static void die(List<Creature> all, Creature creature) throws InterruptedException {
        creature.setDead(true);
        world.killLater(creature);
    }

    public static void idle() {

    }
}
