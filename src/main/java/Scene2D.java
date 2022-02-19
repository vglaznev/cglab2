import java.awt.*;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

public class Scene2D extends Camera2D {

    public Scene2D() {
    }


    public void plot(Graphics g) {
        drawAxes(g);
        lineTo(g, 10, 10);
    }
}