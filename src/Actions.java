import java.awt.*;
import java.util.*;
import java.util.List;

public class Actions {

    private static int size_x, size_y;
    private static Graphics g;
    private static Paint paint;
    private static World world;
    public Actions(int size_x, int size_y, Graphics g) {
        Actions.size_x = size_x;
        Actions.size_y = size_y;
        Actions.g = g;
    }
    Actions(Paint paint, World world) {
        Actions.paint = paint;
        Actions.world = world;
    }
    static void move(Creature creature, int size_x, int size_y) throws InterruptedException {

        if(!creature.isDead()) {
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
                die(creature);
            } else {
                if(creature.isPregnant()) {
                    creature.setEnergy(creature.getEnergy() - Keys.getMoveEnergy() * 2);
                }
                else {
                    creature.setPosition(new int[]{x, y});
                    creature.setEnergy(creature.getEnergy() - Keys.getMoveEnergy());
                }

            }
        }


    }
    public static void grab(Creature creature) {

    }
    public static void eat(Creature creature, Food food) {
        creature.setEnergy(creature.getEnergy() + food.getAmount());
        world.eatFood(food);
    }
    static void reproduce(Creature mother, java.util.List<Creature> all) {
        int id = Toolkit.getID(all);
        Creature baby = new Creature(id, Keys.getENERGY(), mother.getPosition(), Keys.getSIGHT(), Toolkit.generateSex(), mother.getSize());
        world.addCreature(baby);


        mother.setPregnancyYear(world.getYear());



    }

    static void die(Creature creature) throws InterruptedException {
        creature.setDead(true);
        world.removeCreature(creature);
    }

    static void idle() {

    }
}
