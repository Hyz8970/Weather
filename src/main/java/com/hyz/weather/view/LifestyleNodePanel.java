package com.hyz.weather.view;

import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;

public class LifestyleNodePanel extends JPanel {
    private HJLabel type, brf, txt;

    public LifestyleNodePanel() {
        super();
        type = new HJLabel();
        brf = new HJLabel();
        txt = new HJLabel();
        type.setFont(Fonts.MSYH_PLAIN_18);
        brf.setFont(Fonts.MSYH_PLAIN_18);
        txt.setFont(Fonts.MSYH_PLAIN_14);
        this.setLayout(new BorderLayout());
        this.add(type, BorderLayout.WEST);
        this.add(brf, BorderLayout.CENTER);
        this.add(txt, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createMatteBorder(0,0,2,0, MDColor.BLUE_DARK));
    }

    public HJLabel getType() {
        return type;
    }

    public HJLabel getBrf() {
        return brf;
    }

    public HJLabel getTxt() {
        return txt;
    }
}
