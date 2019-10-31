package com.hyz.weather.view;

import com.hyz.weather.entity.Now;
import com.hyz.weather.entity.root.HeWeather6Now;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;

/**
 * 现在的天气面板
 * */
public class NowPanel extends HJPanel {
    private HJLabel tmp,icon,cond,windDir,windSpd;

    public NowPanel(){
        super();
        tmp=new HJLabel();
        icon=new HJLabel();
        cond=new HJLabel();
        windDir=new HJLabel();
        windSpd=new HJLabel();
        tmp.setFont(Fonts.MSYH_BOLD_72);
        cond.setFont(Fonts.MSYH_PLAIN_32);
        windDir.setFont(Fonts.MSYH_PLAIN_18);
        windSpd.setFont(Fonts.MSYH_PLAIN_18);
        this.setLayout(new BorderLayout());
        HJPanel topPanel=new HJPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tmp,BorderLayout.WEST);
        topPanel.add(new HJLabel("℃"),BorderLayout.CENTER);
        topPanel.add(icon,BorderLayout.EAST);
        HJPanel bottomPanel=new HJPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(windDir,BorderLayout.WEST);
        bottomPanel.add(windSpd,BorderLayout.EAST);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(cond,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createMatteBorder(0,0,2,0, MDColor.BLUE_DARK));
    }
    /**
     * 设置天气数据
     * */
    public boolean setData(HeWeather6Now heWeather6Now){
        return setData(heWeather6Now.getNow());
    }
    public boolean setData(Now now){
        tmp.setText(now.getTmp());
        cond.setText(now.getCond_txt());
        windDir.setText("风向："+now.getWind_dir());
        windSpd.setText("风速："+now.getWind_spd()+"m/s");
        icon.setIcon(new ImageIcon(
                NowPanel.class.getClassLoader()
                        .getResource("./img/icon/icon_"+now.getCond_code()+"d.png"))
        );
        return true;
    }
}
