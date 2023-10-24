package UI;

import javax.swing.*;
import java.awt.*;

// Класс, создающий окно приложения с тремя кругами
public class CircleWindow extends JFrame {
    private CirclePanel[] circles;

    public CircleWindow() {
        setTitle("Поле курева");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        circles = new CirclePanel[3];
        for (int i = 0; i < 3; i++) {
            circles[i] = new CirclePanel();
            add(circles[i]);
        }

        pack();
        setVisible(true);
    }

    public CirclePanel[] getCircles() {
        return circles;
    }
}