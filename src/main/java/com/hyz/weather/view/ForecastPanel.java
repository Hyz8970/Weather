package com.hyz.weather.view;

import com.hyz.weather.entity.Daily_forecast;
import com.hyz.weather.entity.root.HeWeather6Forecast;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 七天预报面板
 * */
public class ForecastPanel extends JPanel {
    private ForecastNodePanel[] node;
    public ForecastPanel(){
        super();
        TitledBorder tb = BorderFactory.createTitledBorder("七天预报");
        tb.setTitleFont(Fonts.MSYH_PLAIN_18);
        tb.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(tb);
        node=new ForecastNodePanel[]{
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel()
        };
        JPanel eightPanel=new JPanel();
        eightPanel.setLayout(new FlowLayout());
        for (int i=0;i<7;i++){
            eightPanel.add(node[i]);
        }
        this.add(eightPanel,BorderLayout.SOUTH);
    }
    public boolean setData(HeWeather6Forecast heWeather6Forecast){
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        List<Daily_forecast> daily_forecast_list = heWeather6Forecast.getDaily_forecast();
        for(int i=0;i<7;i++){
            Daily_forecast daily_forecast = daily_forecast_list.get(i);
            node[i].getTmp().setText(daily_forecast.getTmp_max()+"~"+daily_forecast.getTmp_min()+"℃");
            node[i].getCond().setText(daily_forecast.getCond_txt_d());
            try {
                node[i].getDate().setText(dd.format(dd.parse(daily_forecast.getDate()))+"日");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
