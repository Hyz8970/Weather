package com.hyz.weather.entity.root;

import java.util.List;

public class AirNowRoot
{   
    private List<HeWeather6AirNow> HeWeather6;

    public void setHeWeather6(List<HeWeather6AirNow> HeWeather6){
        this.HeWeather6 = HeWeather6;
    }
    public List<HeWeather6AirNow> getHeWeather6(){
        return this.HeWeather6;
    }
}