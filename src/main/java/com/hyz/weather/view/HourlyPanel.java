package com.hyz.weather.view;

import com.hyz.weather.entity.Hourly;
import com.hyz.weather.entity.root.HeWeather6Hourly;
import com.hyz.weather.reSwing.Fonts;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 后八个小时天气预报
 * */
public class HourlyPanel extends JPanel {
    private HourlyNodePanel[] node;
    public HourlyPanel(){
        super();
        TitledBorder tb = BorderFactory.createTitledBorder("八小时更新");
        tb.setTitleFont(Fonts.MSYH_PLAIN_18);
        tb.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(tb);
        this.setLayout(new BorderLayout());
        node=new HourlyNodePanel[]{
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel(),
                new HourlyNodePanel()
        };
        JPanel eightPanel=new JPanel();
        eightPanel.setLayout(new FlowLayout());
        for (int i=0;i<8;i++){
            eightPanel.add(node[i]);
        }
        this.add(eightPanel,BorderLayout.SOUTH);
    }
    public boolean setData(HeWeather6Hourly heWeather6Hourly){
        SimpleDateFormat HH = new SimpleDateFormat("HH");
        List<Hourly> hourlyList = heWeather6Hourly.getHourly();
        for (int i=0;i<8;i++){
            Hourly hourly = hourlyList.get(i);
            try {
                node[i].getTime().setText(HH.format(HH.parse(hourly.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            node[i].getTmp().setText(hourly.getTmp()+"℃");
            node[i].getCond().setText(hourly.getCond_txt());
        }
        return true;
    }
}
