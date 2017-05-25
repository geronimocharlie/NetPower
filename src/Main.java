import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chrono on 19.05.2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        Start start = new Start();
        //debug(100, 100);

    }

    public static void generate() {

    }
    public static void debug(int size_x, int size_y) {
        Point size = new Point(size_x, size_y);
        World world = new World(Toolkit.generate(size_x, size_y), Toolkit.generateFood(size_x, size_y), size);

    }

}
