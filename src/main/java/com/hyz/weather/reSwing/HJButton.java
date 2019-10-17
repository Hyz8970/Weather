package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJButton extends JButton {
    public HJButton(String tag){
        super(tag);
//        setMargin(new Insets(0,0,0,0));
//        setContentAreaFilled(false);
//        setBorderPainted(false);
//        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(MDColor.blueLight);
//        g.fillRect(20,50,100,100);
        g.drawRoundRect(0,0,70,30,10,10);
    }
}
