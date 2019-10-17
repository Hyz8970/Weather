package com.hyz.weather.entity.root;

import java.util.List;

public class NowRoot
{   
    private List<HeWeather6Now> HeWeather6;

    public void setHeWeather6(List<HeWeather6Now> HeWeather6){
        this.HeWeather6 = HeWeather6;
    }
    public List<HeWeather6Now> getHeWeather6(){
        return this.HeWeather6;
    }
}