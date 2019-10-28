package com.hyz.weather.action;

import com.hyz.weather.entity.root.*;
import com.hyz.weather.service.HeWeatherApi;

public class Weather {
    private HeWeatherApi heWeatherApi = new HeWeatherApi();

    public HeWeather6Now now(String location){
        return heWeatherApi.now(location);
    }
    public HeWeather6AirNow airNow(String location){
        return heWeatherApi.airNow(location);
    }
    public HeWeather6Hourly hourly(String location){
        return heWeatherApi.hourly(location);
    }
    public HeWeather6Forecast forecast(String location){
        return heWeatherApi.forecast(location);
    }
    public HeWeather6Lifestyle lifestyle(String location){
        return heWeatherApi.lifestyle(location);
    }
}
