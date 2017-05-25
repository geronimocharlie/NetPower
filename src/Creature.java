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

    public Creature(int id, float energy, int[] position, int sight, int sex, float SIZE_START) {
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
}
