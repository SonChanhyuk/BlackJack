package engine;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Container container;
    private final int HEIGHT;
    private final int WIDTH;
    int number;

    public Frame(int number, int w, int h) {
        this.number = number;
        this.HEIGHT = h;
        this.WIDTH = w;
        setSize(w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.container = getContentPane();
    }

    /**
    public void paint(Graphics g) {
        if (number == 1) {
            Image background = new ImageIcon("res/wood_background.jpg").getImage();
            g.drawImage(background, 0, 0, null);
        }
    }
    */

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public Container getContain(){ return this.container; }
}
