

import java.awt.*;
import java.lang.reflect.Array;
import java.security.cert.CRLException;
import java.util.*;
import java.util.List;

import javax.swing.JPanel;

public class Paint extends JPanel {

    private Field[][] fields;
    private World world;
    private double scale;
    private int maxFood = 0;
    private int[] food_positions;
    private List<Creature> all;
    private List<Food> foods;
    private long last = System.currentTimeMillis(), latest;

    int fps = 0, tps = 0;
    int i = 0;
    int lastSteps = 0, steps;

    boolean[] deads;


    public Paint(World world, java.util.List<Creature> all, java.util.List<Food> foods) {
        super();
        this.world = world;
        this.all = all;
        this.foods = foods;
        setBackground(Color.WHITE);
        deads = new boolean[all.size()];
    }
    public void resize(List<Creature> all) {
        this.all = all;
        deads = new boolean[all.size()];
    }

    @Override
    public void paintComponent(Graphics g) {
        //Toolkit.progress("Paint Component");
        super.paintComponent(g);
        if (i == World.FPS) {
            steps = world.getSteps();
            i = 0;
            latest = System.currentTimeMillis();
            fps = (int) (World.FPS * 1000 / (latest - last));
            tps = (int) ((steps - lastSteps) * 1000 / (latest - last));
            lastSteps = steps;
            last = latest;
        }
        i++;
        g.drawString("FPS: " + fps, 20, 20);
        g.drawString("Steps per sec: " + tps, 100, 20);
        g.drawString("Current year: " + world.getYear() / 100, 300, 20);

        g.drawString("Current amount of Creatures: " + all.size(), 400, 50);


        if(world.getLastDead() != null) {
            g.drawString("Creature " + world.getLastDead().getId() + " died!", 20, 50);
        }

        g.drawString("Dead Creatures: " + world.getDead(), 200, 50);
        synchronized (all) {
            for (Creature creature : all) {


                if((!Toolkit.checkCiv(all)) || all.size() <= 0) {
                    g.drawString("YOUR CIVILISATION JUST DIED", 200, 200);
                    world.setRunning(false);
                }



                Toolkit.drawCreature(g, creature, scale, creature.size, creature.getPosition()[0], creature.getPosition()[1]);
            }


        }
        synchronized (foods) {
            for(Food food : foods) {
                Toolkit.drawFood(g, food, scale, food.getPosition()[0], food.getPosition()[1]);
            }
        }

        
    }

    public void setWorld(Field[][] fields) {
        this.fields = fields;
    }

    public void setMaxFood(int maxfutterProQuelle) {
        maxFood = maxfutterProQuelle;
    }

    /*public void setAmeisen(Ameise[] ameisen) {
        this.ameisen = ameisen;
    }*/

    public void setScale(double s) {
        scale = s;

    }

}
