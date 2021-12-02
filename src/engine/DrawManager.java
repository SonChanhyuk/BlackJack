package engine;

import com.sun.tools.javac.Main;
import entity.Entity;
import screen.Screen;

import javax.swing.*;
import java.awt.*;


public class DrawManager {
    private Font font;

    public DrawManager(){
        font = new Font("맑은 고딕", Font.BOLD, 20);
    }

    public void drawEntity(final Screen screen, final Entity entity, final int positionX, final int positionY) {
        JLabel img_label = new JLabel();
        ImageIcon icon = new ImageIcon(Main.class.getResource(entity.getRoute()));
        img_label.setIcon(icon);
        img_label.setBounds(positionX,positionY,50,50);
    }

}
