package com.hyz.weather.view;

import com.hyz.weather.entity.Hourly;
import com.hyz.weather.entity.root.HeWeather6Hourly;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 后八个小时天气预报
 * */
public class HourlyPanel extends HJPanel {
    private HourlyNodePanel[] node;
    public HourlyPanel(){
        super();
        TitledBorder tb = BorderFactory.createTitledBorder("八小时更新");
        tb.setTitleFont(Fonts.MSYH_PLAIN_18);
        tb.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(tb);
        this.setLayout(new FlowLayout());
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
        for (int i=0;i<8;i++){
            this.add(node[i]);
        }
    }
    public boolean setData(HeWeather6Hourly heWeather6Hourly){
        return setData(heWeather6Hourly.getHourly());
    }

    public boolean setData(List<Hourly> hourlyList) {
        SimpleDateFormat after = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat before = new SimpleDateFormat("dd.HH");
        for (int i=0;i<8;i++){
            Hourly hourly = hourlyList.get(i);
            try {
                node[i].getTime().setText(before.format(after.parse(hourly.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
            node[i].getTmp().setText(hourly.getTmp()+"℃");
            node[i].getCond().setText(hourly.getCond_txt());
        }
        return true;
    }
}
