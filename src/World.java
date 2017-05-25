import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chrono on 19.05.2017.
 */
public class World extends JFrame {
    private int ticks_ps = 500;
    private boolean running = true;
    private int year = 0;
    private List<Creature> all;
    private List<Food> foods;
    private List<Creature> reaper_queue = new ArrayList<>();
    private List<Creature> baby_queue = new ArrayList<>();
    private List<Food> eat_queue = new ArrayList<>();
    private List<Food> spawn_queue = new ArrayList<>();
    public static int FPS = Keys.getFPS();
    public static int ACCURACY = Keys.getACCURACY();

    private int steps = 0;
    private Field[][] fields;


    Actions actions;
    Point size;
    Paint paint;
    Creature lastDead;
    int dead_counter;

    public World(List<Creature> all, List<Food> foods, Point size) {

        paint = new Paint(this, all, foods);
        this.all = all;
        this.foods = foods;
        this.size = size;


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
        synchronized (foods) {
            for (Food food : foods) {
                food.setSize((int) Math.max(food.getSize(), scale));
            }
        }

        Field.size = (float) Math.max(Field.SIZE_START, scale);
    }

    private void nextTick() throws InterruptedException {

        synchronized (all) {
            for (Creature creature : all) {

                double moveratio = (double) (Math.random() * creature.getAge() / 100);

                if (moveratio < 5) {
                    actions.move(creature, size.x, size.y);
                } else {
                    actions.idle();
                }

                creature.setAge(creature.getAge() + 1);
                year++;
                int[][] surround = Toolkit.surroundings(creature, all, foods);
                System.out.println("");
                    for (Creature creature2 : all) {
                        if (Toolkit.isNextTo(creature, creature2)) {
                            if (creature.getSex() != creature2.getSex()) {
                                if (creature.getAge() > Keys.getMATURE() && creature2.getAge() > Keys.getMATURE()) {
                                    Creature mother = null;
                                    if (creature.getSex() == 1) {
                                        mother = creature;
                                    } else if (creature2.getSex() == 1) {
                                        mother = creature2;
                                    }

                                    if(!mother.isPregnant()) {
                                        actions.reproduce(mother, all);
                                        mother.setPregnant(true);
                                    }


                                }
                            }

                        }
                    }

                if(creature.getEnergy() < 5) {
                        Actions.die(creature);
                }
                else if((creature.getPregnancyYear() + Keys.getPregnancyInterval() <= year) && creature.isPregnant()) {
                    creature.setPregnant(false);
                }



            }
            handleQueues();
        }



    }
    public int getDead() {
        return dead_counter;
    }
    public Creature getLastDead() {
        return lastDead;
    }

    public void removeCreature(Creature creature) {
        synchronized (reaper_queue) {
            reaper_queue.add(creature);
        }
    }
    public void addCreature(Creature creature) {
        synchronized (baby_queue) {
            baby_queue.add(creature);
        }
    }
    public int getYear() {

        return year;
    }
    public void setRunning(boolean b) {
        this.running = b;
    }

    public void handleQueues() {
        synchronized (reaper_queue) {
            for (Creature creature : reaper_queue) {
                all.remove(creature);
                lastDead = creature;
                dead_counter++;
            }
            reaper_queue.clear();
        }
        synchronized (baby_queue) {
            for(Creature creature : baby_queue) {
                all.add(creature);
            }
            baby_queue.clear();
        }

    }
}

