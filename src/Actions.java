import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Actions {
    public static void move(Creature creature) {

    }
    public static void grab(Creature creature) {

    }
    public static void eat(Creature creature) {

    }
    public static void reproduce(Creature creature, ArrayList<Creature> all) {

    }
    public static void die(Creature creature, ArrayList<Creature> all) {
        for(Creature cr : all) {
            if(creature.getId() == cr.getId()) {
                all.remove(cr);
            }
        }
    }
}
