package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(MDColor.blueLight);
//        g.fillRect(20,30,100,100);
        g.drawRoundRect(10,10,20,50,10,10);
    }
}
