package com.hyz.weather.view;

import com.hyz.weather.entity.root.HeWeather6Hourly;

import javax.swing.*;
import java.awt.*;

/**
 * 逐小时天气预报
 * */
public class HourlyPanel extends JPanel {
    private JLabel title;
    public HourlyPanel(){
        super();
        title=new JLabel("八时更新");
        this.setLayout(new BorderLayout());
        JPanel sevenPanel=new JPanel();
        sevenPanel.setLayout(new GridLayout(7,2));
        for (int i=7;i<8;i++){
            sevenPanel.add(new JLabel(i*10+"℃"));
        }
        for (int i=7;i<8;i++){
            sevenPanel.add(new JLabel(i+"风"));
        }
        this.add(title,BorderLayout.NORTH);
        this.add(sevenPanel,BorderLayout.SOUTH);
    }
    public boolean setData(HeWeather6Hourly heWeather6Hourly){
        return false;
    }
}
