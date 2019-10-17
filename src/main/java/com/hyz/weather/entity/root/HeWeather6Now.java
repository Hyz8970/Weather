package com.hyz.weather.entity.root;

import com.hyz.weather.entity.HeWeather6;
import com.hyz.weather.entity.Now;

public class HeWeather6Now extends HeWeather6 {
    private com.hyz.weather.entity.Now now;

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }
}
