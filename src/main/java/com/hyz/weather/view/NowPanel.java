package com.hyz.weather.view;

import com.hyz.weather.entity.Now;
import com.hyz.weather.entity.root.HeWeather6Now;

import javax.swing.*;
import java.awt.*;

/**
 * 现在的天气面板
 * */
public class NowPanel extends JPanel {
    private JLabel tmp,icon,cond,windDir,windSpd;

    public NowPanel(){
        super();
        tmp=new JLabel("10");
        icon=new JLabel(new ImageIcon(
                NowPanel.class.getClassLoader().getResource("./img/icon/icon_100d.png")));
        cond=new JLabel("晴");
        windDir=new JLabel("东风");
        windSpd=new JLabel("11m/s");
        tmp.setFont(new Font("微软雅黑",Font.BOLD,72));
        this.setLayout(new BorderLayout());
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tmp,BorderLayout.WEST);
        topPanel.add(new JLabel("℃"),BorderLayout.CENTER);
        topPanel.add(icon,BorderLayout.EAST);
        JPanel bottomPanel=new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(windDir,BorderLayout.WEST);
        bottomPanel.add(windSpd,BorderLayout.EAST);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(cond,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
    }
    /**
     * 设置天气数据
     * */
    public boolean setData(HeWeather6Now heWeather6Now){
        Now now = heWeather6Now.getNow();
        tmp.setText(now.getTmp());
        cond.setText(now.getCond_txt());
        windDir.setText("风向："+now.getWind_dir());
        windSpd.setText("风速："+now.getWind_spd()+"m/s");
        icon.setIcon(new ImageIcon(
                NowPanel.class.getClassLoader()
                        .getResource("./img/icon/icon_"+now.getCond_code()+"d.png"))
        );
        return false;
    }
}
