package com.hyz.weather.action;

import com.hyz.weather.entity.root.AMapIPRoot;
import com.hyz.weather.service.AMapIPLocation;

public class IpLocation {
    AMapIPLocation aMapIPLocation = new AMapIPLocation();

    /**
     * 获取当前访问主机ip地区
     */
    public String locationByIP() {
        AMapIPRoot location = aMapIPLocation.getLocation();
        if (location != null) {
            return location.getCity();
        } else {
            return "";
        }
    }
}
