package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJLabel extends JLabel {
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.red);
        g.fillRect(20,50,100,100);
    }
}
