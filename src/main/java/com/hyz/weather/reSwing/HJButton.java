package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJButton extends JButton {
    public HJButton(Icon img){
        super(img);
        setMargin(new Insets(0,0,0,0));
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
    public HJButton(String tag){
        super(tag);
        setMargin(new Insets(0,0,0,0));
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
    public HJButton(Icon icon,String text){
        super(text,icon);
        setMargin(new Insets(0,0,0,0));
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
