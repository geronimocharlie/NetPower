import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Creature {
    private int id;
    private float energy;
    private int[] position;
    private int age = 0;
    private int sight;
    private int sex = 0;
    public static float size;
    public static float SIZE_START;
    private boolean crisis = false;
    private boolean dead = false;
    private boolean pregnant = false;
    private int pregnancy_year = 0;
    private World world;

    public Creature(World world, int id, float energy, int[] position, int sight, int sex, float SIZE_START) {
        this.world = world;
        this.id = id;
        this.energy = energy;
        this.position = position;
        this.sight = sight;
        this.sex = sex;
        this.size = SIZE_START;
    }


    public int getId() {
        return this.id;
    }

    public void setEnergy(float energy) {     //CHANGE ENERGY ONLY
        this.energy = energy;
    }

    public float getEnergy() {                //TO PRINT ENERGY AND CHANGE IT
        return this.energy;
    }

    public void setSex(int sex) {           //SET SEX
        this.sex = sex;
    }

    public int getSex() {                   //CHECK WHETHER THERE IS A MALE AND FEMALE TO REPRODUCE
        return this.sex;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }


    public void setSize(float SIZE_START) {
        this.SIZE_START = SIZE_START;
    }

    public float getSize() {
        return size;
    }

    public void setDead(boolean b) {
        dead = b;
    }

    public boolean isDead() {
        return dead;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPregnant(boolean b) {
        pregnant = b;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public int getPregnancyYear() {
        return pregnancy_year;
    }

    public void setPregnancyYear(int pregnancy_year) {
        this.pregnancy_year = pregnancy_year;
    }

    public int getSight() {
        return sight;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    public boolean isNextTo(Creature cr2) {
        boolean b;
        int[] position1 = this.getPosition();
        int[] position2 = cr2.getPosition();

        int p1x = position1[0];
        int p1y = position1[1];

        int p2x = position2[0];
        int p2y = position2[1];

        if ((p1x - p2x == 1 && p1y == p2y) || (p1x - p2x == -1 && p1y == p2y)) b = true;
        else if ((p1y - p2y == 1 && p1x == p2x) || (p1y - p2y == -1 && p1x == p2x)) {
            b = true;
        } else b = false;

        return b;
    }

    public boolean isNextTo(Food food) {
        boolean b;
        int[] position1 = this.getPosition();
        int[] position2 = food.getPosition();

        int p1x = position1[0];
        int p1y = position1[1];

        int p2x = position2[0];
        int p2y = position2[1];

        if ((p1x - p2x == 1 && p1y == p2y) || (p1x - p2x == -1 && p1y == p2y)) b = true;
        else if ((p1y - p2y == 1 && p1x == p2x) || (p1y - p2y == -1 && p1x == p2x)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public void move(List<Creature> all, int size_x, int size_y) throws InterruptedException {
        if (!isDead()) {
            ArrayList<int[]> moves = new ArrayList<>();
            moves.add(new int[]{1, 0});
            moves.add(new int[]{0, 1});
            moves.add(new int[]{-1, 0});
            moves.add(new int[]{0, -1});

            int rand = (int) (Math.random() * moves.size());
            int[] newPos = moves.get(rand);
            int[] currentPos = getPosition();
            int x = currentPos[0] + newPos[0];
            int y = currentPos[1] + newPos[1];
            if (x > size_x || y > size_y) {
                die();
            } else {
                if (isPregnant()) {
                    setEnergy(getEnergy() - world.keys.getMoveEnergy() * 2);
                } else {
                    for (Creature creature1 : all) {
                        if (!Arrays.equals(creature1.getPosition(), new int[]{x, y})) {
                            setPosition(new int[]{x, y});
                        }
                    }
                    setEnergy(getEnergy() - world.keys.getMoveEnergy());
                }
            }
        }
    }

    public void die() throws InterruptedException {
        setDead(true);
        world.removeCreature(this);
    }

    public void grab(Creature creature) {

    }

    public void eat(Food food) {
        setEnergy(getEnergy() + food.getAmount());
        world.eatFood(food);
    }

    void reproduce(List<Creature> all) {
        int id = Toolkit.getID(all);
        Creature baby = new Creature(world, id, world.keys.getENERGY(), getPosition(), world.keys.getSIGHT(), Toolkit.generateSex(), getSize());
        world.addCreature(baby);

        setPregnancyYear(world.getYear());
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}

