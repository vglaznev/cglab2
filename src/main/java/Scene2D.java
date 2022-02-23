import java.awt.*;

public class Scene2D extends Camera2D {

    public Scene2D() {
    }

    public void plot(Graphics g) {
        drawAxes(g);
        lineTo(g, 10, 10);
    }
}