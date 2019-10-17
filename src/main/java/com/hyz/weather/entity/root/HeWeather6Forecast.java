package com.hyz.weather.entity.root;

import com.hyz.weather.entity.Daily_forecast;
import com.hyz.weather.entity.HeWeather6;

import java.util.List;

public class HeWeather6Forecast extends HeWeather6 {
    private List<Daily_forecast> daily_forecast;

    public List<Daily_forecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<Daily_forecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }
}
