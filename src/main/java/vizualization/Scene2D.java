package vizualization;

import model.AffineTransform;
import model.Model2D;

import java.awt.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Scene2D extends Camera2D {
    Model2D model2D;

    public Scene2D(int width, int height) throws URISyntaxException {
        super(width, height);
        model2D = new Model2D(Paths.get(getClass().getResource("/vertices").toURI()),
                Paths.get(getClass().getResource("/edges").toURI()));
    }

    private void leftMove() {
        model2D.applyAffineTransform(AffineTransform.TranslationMatrix(-1, 0));
    }

    private void rightMove() {
        model2D.applyAffineTransform(AffineTransform.TranslationMatrix(1, 0));
    }

    private void upMove() {
        model2D.applyAffineTransform(AffineTransform.TranslationMatrix(0, 1));
    }

    private void downMove() {
        model2D.applyAffineTransform(AffineTransform.TranslationMatrix(0, -1));
    }

    private void scalingUp() {
        model2D.applyAffineTransform(AffineTransform.ScalingMatrix(2, 2));
    }

    private void scalingDown() {
        model2D.applyAffineTransform(AffineTransform.ScalingMatrix(0.5, 0.5));
    }

    private void rotateLeft() {
        model2D.applyAffineTransform(AffineTransform.RotationMatrix(-10, true));
    }

    private void rotateRight() {
        model2D.applyAffineTransform(AffineTransform.RotationMatrix(10, true));
    }

    public void process(char key) {
        switch (key) {
            case 'a':
                leftMove();
                break;
            case 'w':
                upMove();
                break;
            case 's':
                downMove();
                break;
            case 'd':
                rightMove();
                break;
            case 'r':
                rotateRight();
                break;
            case 'l':
                rotateLeft();
                break;
            case '+':
                scalingUp();
                break;
            case '-':
                scalingDown();
                break;
        }
    }

    public void plot(Graphics g) {
        drawAxes(g);
        model2D.render(this, g);
    }
}