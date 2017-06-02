import java.awt.*;
import java.util.*;
import java.util.List;

@Deprecated
public class Actions {

    private static int size_x, size_y;
    private static Graphics g;
    private static Paint paint;
    private static World world;
    public Actions(int size_x, int size_y, Graphics g) {
        Actions.size_x = size_x;
        Actions.size_y = size_y;
        Actions.g = g;
    }
    Actions(Paint paint, World world) {
        Actions.paint = paint;
        Actions.world = world;
    }


    static void idle() {

    }


}
