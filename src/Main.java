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
        //debug(50,50);
        generate();

    }

    public static void generate() {

    }
    public static void debug(int size_x, int size_y) {
        Point size = new Point(size_x, size_y);
        int[] foodperq = new int[]{1};
        //World world = new World(Toolkit.generate(10, 50, 2), Toolkit.generateFood(size, "1"), foodperq, size, 60, 16);
        //World world = new World();
    }

}
