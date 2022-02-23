import javax.swing.*;
import java.net.URISyntaxException;

public class Application {
    public static void setupAndRunApplication() throws URISyntaxException {
        JFrame frame = new JFrame();
        Panel panel = new Panel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
    }

    public static void main(String[] args) throws URISyntaxException {
        setupAndRunApplication();
    }
}
