import java.awt.*;

/**
 * Created by Chrono on 25.05.2017.
 */
public class Keys {
    private static int FPS = 60;
    private static int ACCURACY = 16;
    private static int size_x = 100;
    private static int size_y = 100;
    private static int AMOUNT_CREATURES = 100;
    private static int AMOUNT_FOOD = 10;
    private static int ENERGY_PER_FOOD = 100;
    private static int SIGHT = 2;
    private static int ENERGY = 1000;
    private static int CREATURE_SIZE = 10;


    private static int MATURE = 700; //TIME TO BECOME MATURE AND ABLE TO REPRODUCE //BETWEEN 730 and 735 is decided whether it will die or overflow
    private static int PREGNANCY_INTERVAL = 1000; //IN WHICH INTERVAL WOMEN CAN GET PREGNANT

    private static int DEAD_ENERGY = 100; //ENERGY NEEDED TO DIE

    private static int MOVE_ENERGY = 1; //STAY AT 1, EVERYTHING ABOVE KILLS

    private static Color[] CREATURE_COLORS= new Color[]{dc("#0066ff"), dc("#cc0066")};

    private static Color[] FOOD_COLORS = new Color[]{dc("#009933"), dc("#ff9900") };

    private static int MAX_DANGER = 5;


    public int getAmountCreatures() {
        return AMOUNT_CREATURES;
    }

    public void setAmountCreatures(int amountCreatures) {
        AMOUNT_CREATURES = amountCreatures;
    }

    public int getAmountFood() {
        return AMOUNT_FOOD;
    }

    public void setAmountFood(int amountFood) {AMOUNT_FOOD = amountFood;}

    public int getSIGHT() {
        return SIGHT;
    }

    public void setSIGHT(int SIGHT) {
        Keys.SIGHT = SIGHT;
    }

    public int getENERGY() {
        return ENERGY;
    }

    public void setENERGY(int ENERGY) {
        Keys.ENERGY = ENERGY;
    }

    public int getCreatureSize() {
        return CREATURE_SIZE;
    }

    public void setCreatureSize(int creatureSize) {
        Keys.CREATURE_SIZE = creatureSize;
    }

    public int getMATURE() {
        return MATURE;
    }

    public void setMATURE(int MATURE) {
        Keys.MATURE = MATURE;
    }

    public int getPregnancyInterval() {
        return PREGNANCY_INTERVAL;
    }

    public void setPregnancyInterval(int pregnancyInterval) {
        PREGNANCY_INTERVAL = pregnancyInterval;
    }

    public int getDeadEnergy() {
        return DEAD_ENERGY;
    }

    public void setDeadEnergy(int deadEnergy) {
        DEAD_ENERGY = deadEnergy;
    }

    public int getMoveEnergy() {
        return MOVE_ENERGY;
    }

    public void setMoveEnergy(int moveEnergy) {
        MOVE_ENERGY = moveEnergy;
    }

    public int getEnergyPerFood() {
        return ENERGY_PER_FOOD;
    }

    public void setEnergyPerFood(int energyPerFood) {
        ENERGY_PER_FOOD = energyPerFood;
    }

    public Color[] getCreatureColors() {
        return CREATURE_COLORS;
    }

    public void setCreatureColors(Color[] creatureColors) {
        CREATURE_COLORS = creatureColors;
    }

    public Color[] getFoodColors() {
        return FOOD_COLORS;
    }

    public void setFoodColors(Color[] foodColors) {
        FOOD_COLORS = foodColors;
    }

    public static Color dc(String string) {
        return Color.decode(string);
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        Keys.FPS = FPS;
    }

    public int getACCURACY() {
        return ACCURACY;
    }

    public void setACCURACY(int ACCURACY) {
        Keys.ACCURACY = ACCURACY;
    }

    public int getMaxDanger() {
        return MAX_DANGER;
    }

    public void setMaxDanger(int maxDanger) {
        MAX_DANGER = maxDanger;
    }

    public int getSize_x() {
        return size_x;
    }

    public void setSize_x(int size_x) {
        Keys.size_x = size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public void setSize_y(int size_y) {
        Keys.size_y = size_y;
    }
}
