package screen;

import java.awt.*;
import engine.Frame;

public class Screen {
    protected int width;
    protected int height;
    protected boolean isRunning;
    protected Frame frame;
    protected Container container;

    public Screen(int number, int w, int h, boolean isRun) {
        frame = new Frame(number,w,h);
        container = frame.getContain();
        container.setSize(w, h);
        frame.setResizable(false);
        width=frame.getWidth();
        height= frame.getHeight();
        isRunning=isRun;
    }

    public void ViewScreen(){ frame.setVisible(isRunning); }

    public void SetIsRunning(boolean isRun){this.isRunning = isRun;}
    public boolean getIsRunning(){ return isRunning; }
}