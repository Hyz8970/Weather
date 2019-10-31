package com.hyz.weather.view;

import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;

public class HourlyNodePanel extends HJPanel {
    private HJLabel time, tmp, cond;

    public HourlyNodePanel() {
        super();
        time = new HJLabel("",JLabel.CENTER);
        tmp = new HJLabel("",JLabel.CENTER);
        cond = new HJLabel("",JLabel.CENTER);
        time.setFont(Fonts.MSYH_PLAIN_10);
        tmp.setFont(Fonts.MSYH_PLAIN_14);
        cond.setFont(Fonts.MSYH_PLAIN_14);
        this.setLayout(new BorderLayout());
        this.add(time, BorderLayout.NORTH);
        this.add(tmp, BorderLayout.CENTER);
        this.add(cond, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createLineBorder(MDColor.BLUE_DARK));
    }

    public HJLabel getTime() {
        return time;
    }

    public HJLabel getTmp() {
        return tmp;
    }

    public HJLabel getCond() {
        return cond;
    }
}