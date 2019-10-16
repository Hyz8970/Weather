package service;

import com.google.gson.Gson;
import entity.root.*;
import utils.NetTool;


public class HeWeatherApi {
    private NetTool netTool=new NetTool();
    private Gson gson=new Gson();
    /**
     * now
     * */
    public HeWeather6Now Now(String location){
        String now = netTool.HeWeatherRQ("now", location);
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
     * forecast
     * */
    public HeWeather6Forecast Forecast(String location){
        String forecast = netTool.HeWeatherRQ("forecast", location);
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
     * hourly
     * */
    public HeWeather6Hourly Hourly(String location){
        String hourly = netTool.HeWeatherRQ("hourly", location);
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
     * lifestyle
     * */
    public HeWeather6Lifestyle Lifestyle(String location){
        String lifestyle = netTool.HeWeatherRQ("lifestyle", location);
        if (!lifestyle.equals("")){
            LifestyleRoot root = gson.fromJson(lifestyle,LifestyleRoot.class);
            HeWeather6Lifestyle heWeather6Lifestyle = root.getHeWeather6().get(0);
            if (heWeather6Lifestyle.getStatus().equals("ok")){
                return heWeather6Lifestyle;
            }
        }
        return null;
    }
}
