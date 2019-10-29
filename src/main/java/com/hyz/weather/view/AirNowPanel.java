package com.hyz.weather.view;

import com.hyz.weather.entity.Air_now_city;
import com.hyz.weather.entity.root.HeWeather6AirNow;

import javax.swing.*;
import java.awt.*;

/**
 * 空气质量面板
 * */
public class AirNowPanel extends JPanel {
    private JLabel qlty,pm25;
    public AirNowPanel(){
        super();
        qlty=new JLabel();
        pm25=new JLabel();
        this.setLayout(new BorderLayout());
        this.add(qlty,BorderLayout.WEST);
        this.add(pm25,BorderLayout.EAST);
    }
    public boolean setData(HeWeather6AirNow heWeather6AirNow){
        Air_now_city airNowCity = heWeather6AirNow.getAir_now_city();
        qlty.setText("空气质量："+airNowCity.getQlty());
        pm25.setText("PM2.5："+airNowCity.getPm25());
        return false;
    }
}
