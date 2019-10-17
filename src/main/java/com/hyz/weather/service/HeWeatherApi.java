package com.hyz.weather.service;

import com.google.gson.Gson;
import com.hyz.weather.entity.root.*;
import com.hyz.weather.utils.NetTool;


public class HeWeatherApi {
    private NetTool netTool=new NetTool();
    private Gson gson=new Gson();
    /**
     * 现在天气预报
     * */
    public HeWeather6Now now(String location){
        String now = netTool.heWeatherRQ("now", location);
        if (!now.equals("")){
            NowRoot root = gson.fromJson(now,NowRoot.class);
            HeWeather6Now heWeather6Now = root.getHeWeather6().get(0);
            if (heWeather6Now.getStatus().equals("ok")){
                return heWeather6Now;
            }
        }
        return null;
    }
    /**
     * 七天预报
     * */
    public HeWeather6Forecast forecast(String location){
        String forecast = netTool.heWeatherRQ("forecast", location);
        if (!forecast.equals("")){
            ForecastRoot root = gson.fromJson(forecast,ForecastRoot.class);
            HeWeather6Forecast heWeather6Forecast = root.getHeWeather6().get(0);
            if (heWeather6Forecast.getStatus().equals("ok")){
                return heWeather6Forecast;
            }
        }
        return null;
    }
    /**
     * 小时预报
     * */
    public HeWeather6Hourly hourly(String location){
        String hourly = netTool.heWeatherRQ("hourly", location);
        if (!hourly.equals("")){
            HourlyRoot root = gson.fromJson(hourly,HourlyRoot.class);
            HeWeather6Hourly heWeather6Hourly = root.getHeWeather6().get(0);
            if (heWeather6Hourly.getStatus().equals("ok")){
                return heWeather6Hourly;
            }
        }
        return null;
    }
    /**
     * 生活指数
     * */
    public HeWeather6Lifestyle lifestyle(String location){
        String lifestyle = netTool.heWeatherRQ("lifestyle", location);
        if (!lifestyle.equals("")){
            LifestyleRoot root = gson.fromJson(lifestyle,LifestyleRoot.class);
            HeWeather6Lifestyle heWeather6Lifestyle = root.getHeWeather6().get(0);
            if (heWeather6Lifestyle.getStatus().equals("ok")){
                return heWeather6Lifestyle;
            }
        }
        return null;
    }
    /**
     * 当前空气质量
     * */
    public HeWeather6AirNow airNow(String location){
        String now = netTool.heWeatherRQAir("now", location);
        if (!now.equals("")){
            AirNowRoot root = gson.fromJson(now,AirNowRoot.class);
            HeWeather6AirNow heWeather6AirNow = root.getHeWeather6().get(0);
            if (heWeather6AirNow.getStatus().equals("ok")){
                return heWeather6AirNow;
            }
        }
        return null;
    }
}
