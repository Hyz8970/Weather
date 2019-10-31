package com.hyz.weather.view;

import com.hyz.weather.entity.Daily_forecast;
import com.hyz.weather.entity.root.HeWeather6Forecast;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 七天预报面板
 * */
public class ForecastPanel extends HJPanel {
    private ForecastNodePanel[] node;
    public ForecastPanel(){
        super();
        TitledBorder tb = BorderFactory.createTitledBorder("七天预报");
        tb.setTitleFont(Fonts.MSYH_PLAIN_18);
        tb.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(tb);
        this.setLayout(new FlowLayout());
        node=new ForecastNodePanel[]{
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel(),
                new ForecastNodePanel()
        };
        for (int i=0;i<7;i++){
            this.add(node[i]);
        }
    }
    public boolean setData(HeWeather6Forecast heWeather6Forecast){
        return setData(heWeather6Forecast.getDaily_forecast());
    }

    public boolean setData(List<Daily_forecast> daily_forecast_list) {
        SimpleDateFormat after = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat before = new SimpleDateFormat("dd");
        for(int i=0;i<7;i++){
            Daily_forecast daily_forecast = daily_forecast_list.get(i);
            node[i].getTmp().setText(daily_forecast.getTmp_max()+"~"+daily_forecast.getTmp_min()+"℃");
            node[i].getCond().setText(daily_forecast.getCond_txt_d());
            try {
                node[i].getDate().setText(before.format(after.parse(daily_forecast.getDate()))+"日");
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
