import javax.swing.*;
import java.awt.*;

public class test {
    private static JPanel panel;
    private static Container container;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("test");
        container = frame.getContentPane();
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(1280,720);
        frame.setVisible(true);
    }
}
