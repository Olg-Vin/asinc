package UI;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    private Color color;

    public CirclePanel() {
        setPreferredSize(new Dimension(100, 100));
        color = Color.GRAY;
    }

    public void changeColor() {
        if (color == Color.GRAY) {
            color = Color.RED;
        } else {
            color = Color.GRAY;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}