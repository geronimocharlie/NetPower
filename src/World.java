import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.List;

/**
 * Created by Chrono on 19.05.2017.
 */
public class World extends JFrame {
    private int ticks_ps = 500;
    private boolean running = true;
    private List<Creature> all;
    public static int FPS;
    public static int ACCURACY;
    public int AMOUNT_FOOD;
    private int steps = 0;
    private Field[][] fields;
    public static Point[] food_positions;
    public static int[] foodperq;
    public static float FIELD_SIZE_START;
    public static float CREATURE_SIZE_START;
    Actions actions;
    Point size;
    Paint paint;

    public World(List<Creature> all, Point[] food_positions, int[] foodperq, Point size, int FPS, int ACCURACY) {

        paint = new Paint(this, all);
        this.all = all;
        this.FPS = FPS;
        this.ACCURACY = ACCURACY;
        this.size = size;
        this.food_positions = food_positions;
        this.foodperq = foodperq;

        generateFrame();

    }
    public World() {
        System.out.println("World EMPTY!");
    }



    public void generateFrame() {
        Toolkit.progress("Generate World");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(700, 700);
        Toolkit.step("Size set");

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }
        });

        addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                handleResize(size);

            }

            @Override
            public void componentHidden(ComponentEvent arg0) {

            }

            @Override
            public void componentMoved(ComponentEvent arg0) {

            }

            @Override
            public void componentShown(ComponentEvent arg0) {

            }
        });
        setLocation(0, 0);
        Toolkit.step("Location set");
        setLayout(new BorderLayout()); Toolkit.step("Location set");
        setVisible(true); Toolkit.step("Visibility set");
        JPanel topMenu = new JPanel(new BorderLayout(20, 20)); Toolkit.step("Top Menu set");
        topMenu.setBorder(new EmptyBorder(20, 20, 20, 20)); Toolkit.step("Top Menu Border set");

        JSlider slider = new JSlider(0, 35);
        slider.setValue(5);
        ticks_ps = (int) Math.round(Math.pow(10, slider.getValue() / 10f));
        JLabel l_tps = new JLabel("SPS: " + ticks_ps);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                synchronized (World.this) {
                    ticks_ps = (int) Math.round(Math.pow(10, slider.getValue() / 10f));
                }
                l_tps.setText("SPS: " + ticks_ps);

            }
        });
        topMenu.add(l_tps, BorderLayout.LINE_START);
        topMenu.add(slider, BorderLayout.LINE_END);

        handleResize(size); Toolkit.step("Resize handled");
        add(topMenu, BorderLayout.PAGE_START);
        add(paint, BorderLayout.CENTER); Toolkit.step("Paint added to Layout");
        setVisible(true);


        fields = new Field[size.x][size.y];// leeres Feld == null
        paint.setWorld(fields);// referenz der Welt zum zeichenen
        int maxfoodperq = 0;
        /*for (int ifq = 0; ifq < food_positions.length; ifq++) {// ifq -> idexFutterQuelle
            if (foodperq[ifq] > maxfoodperq)
                maxfoodperq = foodperq[ifq];
            fields[food_positions[ifq].x][food_positions[ifq].y] = new Field(food_positions[ifq].x, food_positions[ifq].y, foodperq[ifq]);
        }
        paint.setMaxFood(maxfoodperq);*/


        startAnimation();
    }

    private void startAnimation() {
        Toolkit.progress("Start Animation");
        startDrawThread();
        new Thread() {
            public void run() {
                Toolkit.step("Animation Thread started");
                try {
                    int warten;
                    long last = System.currentTimeMillis(), latest;
                    int ticks_ps = World.this.ticks_ps;
                    Toolkit.step("Ticks PS : " + ticks_ps);
                    float millsStepInterval = 1000f / ticks_ps;
                    int i = 1;

                    actions = new Actions(paint, World.this);

                    for (steps = 0; steps < Integer.MAX_VALUE - ticks_ps && running; steps++) {

                        nextTick();

                        latest = System.currentTimeMillis();
                        warten = Math.max(Math.round(millsStepInterval * i + (last - latest)), 0);

                        if (i == ACCURACY) {
                            i = 1;
                            last = latest + warten;
                        } else
                            i++;
                        Thread.sleep(warten);

                        synchronized (World.this) {
                            if (ticks_ps != World.this.ticks_ps) {
                                ticks_ps = World.this.ticks_ps;
                                millsStepInterval = 1000f / ticks_ps;
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }
                Toolkit.step("Animation Thread finished");
            }
        }.start();

    }

    private void startDrawThread() {

        new Thread() {
            public void run() {
                float millsFrameInterval = 1000f / FPS;// e.g. render 1 frame in 1000/60 millisec
                long warten;
                long last = System.currentTimeMillis(), latest;
                int i = 1;
                try {
                    for (int frames = 0; frames < Integer.MAX_VALUE - FPS && running; frames++) {

                        paint.repaint();

                        latest = System.currentTimeMillis();
                        warten = Math.max(Math.round(millsFrameInterval * i + (last - latest)), 0);

                        if (i == ACCURACY) {
                            i = 1;
                            last = latest + warten;
                        } else
                            i++;
                        Thread.sleep(warten);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }

            }
        }.start();
        Toolkit.step("Draw Thread finished");
    }

    public int getSteps() {
        return steps;
    }

    private void handleResize(Point size) {// scale so berechnet, dass alles auf das Fenster passt
        double scale = Math.min(1d * getWidth() / size.x, (1d * getHeight() - 100) / size.y);
        paint.setScale(scale);

        synchronized (all) {
            for (Creature creature : all) {
                creature.size = (float) Math.max(creature.size, scale);
            }
        }

        Field.size = (float) Math.max(Field.SIZE_START, scale);
    }

    private void nextTick() throws InterruptedException {
        // Ameisen bewegen
        for (Creature creature : all) {
            //Random random = new Random();
            //boolean move = random.nextBoolean();
            double moveratio = (double) (Math.random() * creature.getAge() / 100);

            //if(creature.getEnergy() > 10) {
                if(moveratio < 5) {
                    actions.move(all, creature, size.x, size.y);
                }
                else {
                    actions.idle();
                }
            //}
            //else {
            //    actions.die(creature);
            //}

            creature.setAge(creature.getAge() + 1);

            synchronized (all) {
                for (Creature creature1 : all) {
                    for (Creature creature2 : all) {
                        if (Toolkit.isNextTo(creature1, creature2)) {
                            if (creature1.getSex() != creature2.getSex()) {
                                if (creature1.getAge() > 1000 && creature2.getAge() > 1000) {
                                    Creature mother = null;
                                    if (creature1.getSex() == 1) {
                                        mother = creature1;
                                    } else if (creature2.getSex() == 1) {
                                        mother = creature2;
                                    }
                                    actions.reproduce(mother, all);
                                }
                            }

                        }
                    }
                }
            }


        }



    }
    public int getDead() {
        int i = 0;
        synchronized (all) {
            for(Creature creature : all) {
                if(creature.isDead()) i++;
            }
        }
        return i;
    }

    public void setAll(ArrayList<Creature> all) {
        this.all = all;
    }

}

