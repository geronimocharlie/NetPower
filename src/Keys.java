import java.awt.*;

/**
 * Created by Chrono on 25.05.2017.
 */
public class Keys {
    private static int FPS = 60;
    private static int ACCURACY = 16;
    private static int AMOUNT_CREATURES = 100;
    private static int AMOUNT_FOOD = 10;
    private static int ENERGY_PER_FOOD = 100;
    private static int SIGHT = 2;
    private static int ENERGY = 1000;
    private static int CREATURE_SIZE = 10;


    private static int MATURE = 1000; //TIME TO BECOME MATURE AND ABLE TO REPRODUCE //BETWEEN 730 and 735 is decided whether it will die or overflow
    private static int PREGNANCY_INTERVAL = 1000; //IN WHICH INTERVAL WOMEN CAN GET PREGNANT

    private static int DEAD_ENERGY = 200; //ENERGY NEEDED TO DIE

    private static int MOVE_ENERGY = 1; //STAY AT 1, EVERYTHING ABOVE KILLS

    private static Color[] CREATURE_COLORS= new Color[]{dc("#0066ff"), dc("#cc0066")};

    private static Color[] FOOD_COLORS = new Color[]{dc("#009933"), dc("#ff9900") };


    public static int getAmountCreatures() {
        return AMOUNT_CREATURES;
    }

    public static void setAmountCreatures(int amountCreatures) {
        AMOUNT_CREATURES = amountCreatures;
    }

    public static int getAmountFood() {
        return AMOUNT_FOOD;
    }

    public static void setAmountFood(int amountFood) {
        AMOUNT_FOOD = amountFood;
    }

    public static int getSIGHT() {
        return SIGHT;
    }

    public static void setSIGHT(int SIGHT) {
        Keys.SIGHT = SIGHT;
    }

    public static int getENERGY() {
        return ENERGY;
    }

    public static void setENERGY(int ENERGY) {
        Keys.ENERGY = ENERGY;
    }

    public static int getCreatureSize() {
        return CREATURE_SIZE;
    }

    public static void setCreatureSize(int creatureSize) {
        Keys.CREATURE_SIZE = creatureSize;
    }

    public static int getMATURE() {
        return MATURE;
    }

    public static void setMATURE(int MATURE) {
        Keys.MATURE = MATURE;
    }

    public static int getPregnancyInterval() {
        return PREGNANCY_INTERVAL;
    }

    public static void setPregnancyInterval(int pregnancyInterval) {
        PREGNANCY_INTERVAL = pregnancyInterval;
    }

    public static int getDeadEnergy() {
        return DEAD_ENERGY;
    }

    public static void setDeadEnergy(int deadEnergy) {
        DEAD_ENERGY = deadEnergy;
    }

    public static int getMoveEnergy() {
        return MOVE_ENERGY;
    }

    public static void setMoveEnergy(int moveEnergy) {
        MOVE_ENERGY = moveEnergy;
    }

    public static int getEnergyPerFood() {
        return ENERGY_PER_FOOD;
    }

    public static void setEnergyPerFood(int energyPerFood) {
        ENERGY_PER_FOOD = energyPerFood;
    }

    public static Color[] getCreatureColors() {
        return CREATURE_COLORS;
    }

    public static void setCreatureColors(Color[] creatureColors) {
        CREATURE_COLORS = creatureColors;
    }

    public static Color[] getFoodColors() {
        return FOOD_COLORS;
    }

    public static void setFoodColors(Color[] foodColors) {
        FOOD_COLORS = foodColors;
    }

    public static Color dc(String string) {
        return Color.decode(string);
    }

    public static int getFPS() {
        return FPS;
    }

    public static void setFPS(int FPS) {
        Keys.FPS = FPS;
    }

    public static int getACCURACY() {
        return ACCURACY;
    }

    public static void setACCURACY(int ACCURACY) {
        Keys.ACCURACY = ACCURACY;
    }
}
