import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Panel extends JPanel {
    public static final int DEFAULT_HEIGHT = 600;
    public static final int DEFAULT_WIDTH = 600;
    public static final double SCALE_COEFFICIENT = 0.5;

    private Scene2D scene2D;

    public Panel() {
        scene2D = new Scene2D();
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(Color.WHITE);
        addComponentListener(new ComponentControl());
        addMouseListener(new MouseControl());
        addMouseWheelListener(new MouseControl());
        addMouseMotionListener(new MouseControl());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        scene2D.plot(g);
    }

    private class ComponentControl implements ComponentListener {
        @Override
        public void componentResized(ComponentEvent e) {
            scene2D.setResolution(e.getComponent().getSize());
            e.getComponent().repaint();
        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }

    private class MouseControl implements MouseMotionListener, MouseListener, MouseWheelListener {
        private int posX;
        private int posY;

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.printf("Mouse clicked. X: %s, Y: %s \n", e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.printf("Mouse pressed. X: %s, Y: %s, Button: %s \n", e.getX(), e.getY(), e.getButton());
            if(e.getButton() == 3){
                scene2D.setOrigin(e.getX(), e.getY());
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.printf("Mouse dragged. X: %s, Y: %s \n", e.getX(), e.getY());
            scene2D.moveOrigin(e.getX() - posX, e.getY() - posY);
            posX = e.getX();
            posY = e.getY();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.printf("Mouse moved. X: %s, Y: %s \n", e.getX(), e.getY());
            posX = e.getX();
            posY = e.getY();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double scale = (double)(-1 * Math.signum(e.getWheelRotation()) + 2) * SCALE_COEFFICIENT;

            System.out.printf("Mouse wheel moved. Rotation: %s \n", scale);
            scene2D.scaling(scale, scene2D.screenToWorldX(e.getX()), scene2D.screenToWorldY(e.getY()));
            repaint();
        }
    }
}