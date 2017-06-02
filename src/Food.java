/**
 * Created by Chrono on 22.05.2017.
 */
public class Food {
    private int f_id;
    private int amount;
    private int dangerous; //0 - not dangerous, 1 - spikes, 2 - poison, 3 - radioactive ?
    private int[] position;
    private int size;
    private World world;


    public Food(World world, int f_id, int amount, int dangerous, int[] position) {
        this.f_id = f_id;
        this.amount = amount;
        this.dangerous = dangerous;
        this.position = position;
        this.world = world;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDangerous() {
        return dangerous;
    }

    public void setDangerous(int dangerous) {
        this.dangerous = dangerous;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
