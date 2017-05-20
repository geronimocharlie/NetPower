

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

public class Start extends JFrame {
    public static ArrayList<Creature> all = new ArrayList<>();
    public static int SIGHT = 2;
    public static int ID = 0;
    public static int ENERGY = 50;
    public static int AMOUNT_CREATURES = 10;
    public static int AMOUNT_FOOD = 10;
    public static int FIELD_SIZE_START = 8;
    public static int CREATURE_SIZE_START = 4;

    private static int FPS = 60;
    private static int ACCURACY = 16;
    public static Point size = new Point(16, 16); //nest = new Point(anzNestX, anzNestY);


    private JPanel adv_panel;

    private JLabel fakt_label;
    private JSlider fakt_slider;
    private JTextField x_textfield, y_textfield; //tf_nestX, tf_nestY;
    private JPanel adv_panel3;
    private JTextField food_textfield;

    private JTextField creatures_textfield;
    private JPanel size_panel;

    private boolean advSettingsShown = false;
    private ArrayList<FutterQPanel> list_fq = new ArrayList<>(5);

    // standard values for advanced settings:
    public static final boolean RULE_IGNORE_TRACE = true;

    public static final int FUTTER_PRO_QUELLE = 50;

    public Start() {
        super("Artificial Life Simulation");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        // Font font = new Font("calibri", Font.BOLD,20);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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
                int i = JOptionPane.showConfirmDialog(null, "Mit dem Schließen diese Fensters wird die Simulation abgebrochen.", "Achtung!", JOptionPane.OK_CANCEL_OPTION);
                if (i == JOptionPane.OK_OPTION)
                    System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }
        });

        // setSize(500, 300);
        setLocation(400, 100);
        JPanel p_frame = new JPanel();
        p_frame.setLayout(new BorderLayout());

        JPanel all_panel = new JPanel();
        all_panel.setLayout(new BorderLayout());

        JPanel p_basic = new JPanel();
        setLayout(new BorderLayout());

        GridLayout gl = new GridLayout(4, 2, 20, 20);
        p_basic.setLayout(gl);

        JLabel SIZE_LABEL = new JLabel("Größe des Feldes: ");
        JLabel AMOUNT_CREATURES_LABEL = new JLabel("Anzahl der Lebewesen: ");
        JLabel AMOUNT_FOOD_LABEL = new JLabel("Anzahl der Futterquellen: ");

        EmptyBorder border = new EmptyBorder(20, 10, 20, 20);
        SIZE_LABEL.setBorder(border);
        AMOUNT_CREATURES_LABEL.setBorder(border);
        AMOUNT_FOOD_LABEL.setBorder(border);

        size_panel = new JPanel();
        creatures_textfield = new JTextField("100");
        food_textfield = new JTextField("5");

        JLabel l_groesseP = new JLabel(" * ");
        l_groesseP.setHorizontalAlignment(JLabel.CENTER);

        x_textfield = new JTextField("500");
        y_textfield = new JTextField("500");
        x_textfield.setColumns(5);
        y_textfield.setColumns(5);

        size_panel.setLayout(new BorderLayout());
        size_panel.add(x_textfield, BorderLayout.WEST);
        size_panel.add(l_groesseP, BorderLayout.CENTER);
        size_panel.add(y_textfield, BorderLayout.EAST);

        JLabel more_label = new JLabel("Mehr Einstellungen..");
        more_label.setFont(new Font("calibri", Font.BOLD, 12));
        more_label.setBorder(border);
        more_label.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseMoved(MouseEvent arg0) {
            }
            @Override
            public void mouseDragged(MouseEvent arg0) {
            }
            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
            @Override
            public void mousePressed(MouseEvent arg0) {
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO erweiterte Einstellungen einblenden
                // System.out.println("add advanced");
                if (advSettingsShown) {
                    all_panel.remove(1);
                    all_panel.remove(adv_panel);
                    more_label.setText("Mehr Einstellungen..");
                } else {
                    all_panel.add(new JSeparator(), BorderLayout.CENTER);
                    all_panel.add(adv_panel, BorderLayout.SOUTH);
                    more_label.setText("Weniger Einstellungen..");

                }
                advSettingsShown = !advSettingsShown;
                pack();
            }
        });
        JLabel l_emty = new JLabel("");

        p_basic.add(SIZE_LABEL);
        p_basic.add(size_panel);
        p_basic.add(AMOUNT_CREATURES_LABEL);
        p_basic.add(creatures_textfield);
        p_basic.add(AMOUNT_FOOD_LABEL);
        p_basic.add(food_textfield);
        p_basic.add(more_label);
        p_basic.add(l_emty);

        // Erweiterte Einstellungen

        adv_panel = new JPanel();
        adv_panel.setLayout(new BorderLayout(20, 20));
        adv_panel.setBorder(border);

        JPanel p_adv_1 = new JPanel();
        p_adv_1.setLayout(new GridLayout(3, 3, 20, 20));











        JPanel p_adv_2 = new JPanel();
        p_adv_2.setLayout(new GridLayout(0, 1, 20, 20));

        JPanel p_customQ = new JPanel();
        p_customQ.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        JLabel l_customQ = new JLabel("Feste Nahrung hinzufügen: ");
        JLabel l_customInfo = new JLabel("Alle Nahrungsquellen die nicht näher spezifiziert werden werden zufällig verteilt");
        l_customInfo.setFont(new Font("arial", Font.ITALIC, 10));

        adv_panel3 = new JPanel();// alle spezifischen Futterquellen
        adv_panel3.setLayout(new GridLayout(0, 1, 20, 10));

        JButton btn_customAdd = new JButton("Nahrungsquelle spezifizieren");
        btn_customAdd.addActionListener(event -> {
            int anzQ = Integer.MAX_VALUE;
            try {
                anzQ = Integer.parseInt(food_textfield.getText());
            } catch (NumberFormatException e) {

            }
            if (list_fq.size() < anzQ) {
                FutterQPanel q = new FutterQPanel(list_fq.size());
                list_fq.add(q);
                adv_panel3.add(q);
                adv_panel3.revalidate();
                adv_panel3.repaint();
            } else {
                JOptionPane.showMessageDialog(Start.this, "<html>Es sind schon alle " + anzQ + " Nahrungsquellen definiert.<br /> Ändere oben die Anzahl der Nahrungsquellen.</html>",
                        "Zu viele Quellen", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btn_fertig = new JButton("Simualtion starten");
        btn_fertig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Pushed");
                int size_x, size_y, amount_creatures, amount_food;
                try {
                    size_x = Integer.parseInt(x_textfield.getText());
                    size_y = Integer.parseInt(y_textfield.getText());
                    amount_creatures = Integer.parseInt(creatures_textfield.getText());
                    amount_food = Integer.parseInt(food_textfield.getText());

                    if (size_x <= 0 || size_y <= 0 || amount_creatures <= 0 || amount_food < 0 )
                        JOptionPane.showMessageDialog(Start.this, "Bitte Zahlen > 0 eingeben.", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
                    else {
                        System.out.println("okay");
                        Point size = new Point(size_x, size_y); //nest = new Point(anzNestX, anzNestY);
                        //new World(all, FPS, ACCURACY, AMOUNT_FOOD, FIELD_SIZE_START, CREATURE_SIZE_START, size);
                        World world = new World(Toolkit.generate(AMOUNT_CREATURES, ENERGY, SIGHT), getFoodPositions(size), getFutterProQ(), size, FPS, ACCURACY);
                        world.generateFrame();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(Start.this, "Bitte nur Ganzzahlen eingeben.", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Start.this, "Bitte erneut vesuchen.", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        p_customQ.add(l_customQ);
        p_customQ.add(btn_customAdd);
        p_customQ.add(l_customInfo);


        p_adv_2.add(p_customQ);
        // p_adv_2.add(new JSeparator());

        adv_panel.add(p_adv_1, BorderLayout.NORTH);
        adv_panel.add(p_adv_2, BorderLayout.CENTER);
        adv_panel.add(adv_panel3, BorderLayout.SOUTH);

        // FutterQ: x,y,anzFutter

        all_panel.add(p_basic, BorderLayout.NORTH);

        p_frame.add(all_panel, BorderLayout.CENTER);
        p_frame.add(btn_fertig, BorderLayout.SOUTH);

        JScrollPane scrollP = new JScrollPane(p_frame, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollP.getVerticalScrollBar().setUnitIncrement(10);

        add(scrollP);
        add(btn_fertig, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        new Start();
    }

    private Point[] getFoodPositions(Point size) throws NumberFormatException {
        int anzQ = Integer.parseInt(food_textfield.getText());
        Point[] p = new Point[anzQ];
        for (int i = 0; i < anzQ; i++) {
            if (i < list_fq.size())
                p[i] = list_fq.get(i).getPos();
            else
                p[i] = new Point(randomPos(size.x), randomPos(size.y));
        }
        return p;
    }

    private int[] getFutterProQ() throws NumberFormatException {
        int anzQ = Integer.parseInt(food_textfield.getText());
        int[] p = new int[anzQ];
        for (int i = 0; i < anzQ; i++) {
            if (i < list_fq.size())
                p[i] = list_fq.get(i).getFutter();
            else
                p[i] = FUTTER_PRO_QUELLE;
        }
        return p;
    }



    public static float getSliderValueLin(int value, int stellen) {

        return toNiceFloat(value / 1000f + 0.001f, stellen);
    }

    public static float getSliderValueExp(int value, int stellen) {
        float f = (float) (value / 2000f + 0.95);
        return toNiceFloat(f, stellen);

    }

    public static int getValueForSlider(float value, boolean isExp) {
        if (isExp)
            return (int) ((value - 0.95f) * 2000);
        else
            return (int) ((value - 0.001f) * 1000);
    }

    public static float toNiceFloat(float f, int stellen) {
        int i = (int) Math.round(f * Math.pow(10, stellen));

        return (float) (i / Math.pow(10, stellen));
    }

    public class FutterQPanel extends JPanel {

        private JTextField tf_posX, tf_posY, tf_anzFutter;

        public FutterQPanel(int index) {
            super();

            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

            JLabel l_name = new JLabel("Futterquelle [Position x] [Position y] [Futtereinheiten]");
            tf_posX = new JTextField(newRandomPos(true), 5);
            tf_posY = new JTextField(newRandomPos(false), 5);
            tf_anzFutter = new JTextField("" + FUTTER_PRO_QUELLE, 5);
            JButton btn_delete = new JButton("Delete");
            btn_delete.addActionListener(e -> {
                list_fq.remove(this);
                adv_panel3.remove(this);
                adv_panel3.revalidate();
                adv_panel3.repaint();
            });

            add(l_name);
            add(tf_posX);
            add(tf_posY);
            add(tf_anzFutter);
            add(btn_delete);

        }

        public Point getPos() throws NumberFormatException {

            int x = Integer.parseInt(tf_posX.getText());
            int y = Integer.parseInt(tf_posY.getText());
            return new Point(x, y);

        }

        public int getFutter() throws NumberFormatException {
            return Integer.parseInt(tf_anzFutter.getText());
        }

        private String newRandomPos(boolean isX) {
            int world, nest;
            try {
                world = Integer.parseInt((isX ? x_textfield.getText() : y_textfield.getText()));
            } catch (NumberFormatException e) {
                world = 500;
            }
            try {
                //nest = Integer.parseInt((isX ? tf_nestX.getText() : tf_nestY.getText()));
            } catch (NumberFormatException e) {
                nest = world / 2;
            }
            return "" + randomPos(world);//, nest);
        }

    }

    public static int randomPos(int max) {//, int notAllowed) {
        int x;
        do { // garantiert, dass keine Futterquelle auf dem Feld des Nestes ist
            x = (int) (Math.random() * max);
        } while (x == x);
        return x;
    }

}
