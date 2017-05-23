

import java.awt.*;
import java.lang.reflect.Array;
import java.security.cert.CRLException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Paint extends JPanel {

    private Field[][] fields;
    private World world;
    private double scale;
    private int maxFood = 0;
    private int[] food_positions;
    private ArrayList<Creature> all;
    private long last = System.currentTimeMillis(), latest;
    private int lastdead = -1;
    int fps = 0, tps = 0;
    int i = 0;
    int lastSteps = 0, steps;

    boolean[] deads;


    public Paint(World world, ArrayList<Creature> all) {
        super();
        this.world = world;
        this.all = all;
        setBackground(Color.WHITE);
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
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                  if (fields[x][y] != null) {
                      fields[x][y].draw(g, scale, maxFood);
                      Toolkit.drawFields(g, scale, maxFood, world.AMOUNT_FOOD, x, y);
                  }
            }
        }
        for(int i = 0; i < all.size(); i++) {
            if(deads[i] != all.get(i).isDead()) {
                lastdead = all.get(i).getId();

            }
            deads[i] = all.get(i).isDead();
            System.out.print("");

        }
        if(lastdead != -1) {
            g.drawString("Creature " + lastdead + " died!", 20, 50);
        }

        g.drawString("Dead Creatures: " + world.getDead(), 200, 50);
        for (Creature creature : all) {
            Toolkit.drawCreature(g, creature, scale, creature.size, creature.getPosition()[0], creature.getPosition()[1]);
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
