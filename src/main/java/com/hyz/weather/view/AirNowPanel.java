package com.hyz.weather.view;

import com.hyz.weather.entity.Air_now_city;
import com.hyz.weather.entity.root.HeWeather6AirNow;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;

/**
 * 空气质量面板
 * */
public class AirNowPanel extends HJPanel {
    private HJLabel qlty,pm25;
    public AirNowPanel(){
        super();
        qlty=new HJLabel();
        pm25=new HJLabel();
        qlty.setFont(Fonts.MSYH_PLAIN_18);
        pm25.setFont(Fonts.MSYH_PLAIN_18);
        this.setLayout(new BorderLayout());
        this.add(qlty,BorderLayout.WEST);
        this.add(pm25,BorderLayout.EAST);
        this.setBorder(BorderFactory.createMatteBorder(0,0,2,0, MDColor.BLUE_DARK));
    }

    public boolean setData(HeWeather6AirNow heWeather6AirNow){
        return setData(heWeather6AirNow.getAir_now_city());
    }

    public boolean setData(Air_now_city airNowCity) {
        //出现某些null
        if (airNowCity!=null){
            qlty.setText("空气质量："+airNowCity.getQlty());
            pm25.setText("PM2.5："+airNowCity.getPm25());
            return true;
        }
        return false;
    }
}
