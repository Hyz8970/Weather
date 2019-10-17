package com.hyz.weather.entity.root;

import com.hyz.weather.entity.Air_now_city;
import com.hyz.weather.entity.Air_now_station;
import com.hyz.weather.entity.HeWeather6;
import com.hyz.weather.entity.Now;

import java.util.List;

public class HeWeather6AirNow extends HeWeather6 {
    private Air_now_city air_now_city;

    private List<Air_now_station> air_now_station;

    public Air_now_city getAir_now_city() {
        return air_now_city;
    }

    public void setAir_now_city(Air_now_city air_now_city) {
        this.air_now_city = air_now_city;
    }

    public List<Air_now_station> getAir_now_station() {
        return air_now_station;
    }

    public void setAir_now_station(List<Air_now_station> air_now_station) {
        this.air_now_station = air_now_station;
    }
}
