import java.awt.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Scene2D extends Camera2D {
    Model2D model2D;

    public Scene2D() throws URISyntaxException {
        model2D = new Model2D(Paths.get(getClass().getResource("vertices").toURI()),
                              Paths.get(getClass().getResource("edges").toURI()));
    }

    public void plot(Graphics g) {
        drawAxes(g);
        model2D.render(this, g);
    }
}