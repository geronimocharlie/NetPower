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

/**
 * Created by Chrono on 19.05.2017.
 */
public class World extends JFrame {
    private int ticks_ps = 500;
    private boolean running;
    private ArrayList<Creature> all;
    public static int FPS;
    public int ACCURACY;
    public int AMOUNT_FOOD;
    private int steps = 0;
    private Field[][] fields;
    public static float FIELD_SIZE_START;
    public static float CREATURE_SIZE_START;
    Point size;
    Paint paint;
    public World(ArrayList<Creature> all, int FPS, int ACCURACY, int AMOUNT_FOOD, float FIELD_SIZE_START, float CREATURE_SIZE_START, Point size) {
        this.all = all;
        this.FPS = FPS;
        this.ACCURACY = ACCURACY;
        this.AMOUNT_FOOD = AMOUNT_FOOD;
        this.FIELD_SIZE_START = FIELD_SIZE_START;
        this.CREATURE_SIZE_START = CREATURE_SIZE_START;
        this.size = size;
        generateFrame();
    }

    public void generateFrame() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
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
        setLayout(new BorderLayout());
        JPanel topMenu = new JPanel(new BorderLayout(20, 20));
        topMenu.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel l_tps = new JLabel("Schritte pro Seunde: 501");
        JSlider slider = new JSlider(0, 35);
        slider.setValue(27);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                synchronized (World.this) {
                    ticks_ps = (int) Math.round(Math.pow(10, slider.getValue() / 10f));
                }
                l_tps.setText("Schritte pro Seunde: " + ticks_ps);

            }
        });
        topMenu.add(l_tps, BorderLayout.LINE_START);
        topMenu.add(slider, BorderLayout.LINE_END);
        paint = new Paint(this, all);
        //handleResize(size);
        add(topMenu, BorderLayout.PAGE_START);
        add(paint, BorderLayout.CENTER);
        setVisible(true);

        //this.verdfkt = verdunstungsfaktor;
        //this.verdunstungExp = verdunstungExp;
        fields = new Field[16][16];// leeres Feld == null
        paint.setWorld(fields);// referenz der Welt zum zeichenen
        //world[nestPos.x][nestPos.y] = new Feld(nestPos.x, nestPos.y, 0, 0, true);
        startAnimation();
    }

    private void startAnimation() {
        startDrawThread();
        new Thread() {
            public void run() {
                try {
                    int warten;
                    long last = System.currentTimeMillis(), latest;
                    int ticks_ps = World.this.ticks_ps;
                    float millsStepInterval = 1000f / ticks_ps;
                    int i = 1;
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
    }

    public int getSteps() {
        return steps;
    }

    private void handleResize(Point size) {// scale so berechnet, dass alles auf das Fenster passt
        double scale = Math.min(1d * getWidth() / size.x, (1d * getHeight() - 50) / size.y);
        paint.setScale(scale);

        Creature.size = (float) Math.max(Creature.SIZE_START, scale);
        Field.size = (float) Math.max(Field.SIZE_START, scale);
    }

    private void nextTick() {
        // Ameisen bewegen
        for (Creature creature : all)
            Actions.move(creature);
        // Duft evtl verringern
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                Field field = fields[x][y];
                if (field != null) {

                }
                    //field.reduceDuft(verdfkt, verdunstungExp);
            }
        }

    }

}
