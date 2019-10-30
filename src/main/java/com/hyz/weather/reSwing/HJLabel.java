package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJLabel extends JLabel {
    public HJLabel(){
        super();
        this.setFont(Fonts.MSYH_PLAIN_24);
    }
    public HJLabel(Icon img){
        super(img);
    }
    public HJLabel(String text){
        super(text);
        this.setFont(Fonts.MSYH_PLAIN_24);
    }
    @Override
    public void paintComponents(Graphics g) {
    }
}
