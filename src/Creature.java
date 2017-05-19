/**
 * Created by Chrono on 19.05.2017.
 */
public class Creature {
    private int id;
    private int energy;
    private int[] position;
    private int age = 0;
    private int sight;
    private int sex = 0;
    public static float size;
    public static float SIZE_START;
    private boolean crisis = false;

    public Creature(int id, int energy, int[] position, int sight, int sex) {
        this.id = id;
        this.energy = energy;
        this.position = position;
        this.sight = sight;
        this.sex = sex;
    }


    public int getId() {
        return this.id;
    }
    public void setEnergy(int energy) {     //CHANGE ENERGY ONLY
        this.energy = energy;
    }
    public int getEnergy() {                //TO PRINT ENERGY AND CHANGE IT
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

}
