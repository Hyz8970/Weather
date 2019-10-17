package com.hyz.weather.entity.root;

import com.hyz.weather.entity.HeWeather6;
import com.hyz.weather.entity.Lifestyle;

import java.util.List;

public class HeWeather6Lifestyle extends HeWeather6 {
    private List<Lifestyle> lifestyle;

    public List<Lifestyle> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<Lifestyle> lifestyle) {
        this.lifestyle = lifestyle;
    }
}