package com.hyz.weather.action;

import com.hyz.weather.service.BingWallpaper;

/**
 * 壁纸
 * */
public class Wallpaper {

    /**
     * bing壁纸
     * */
    public String bingWallpaper(){
        BingWallpaper bingWallpaper=new BingWallpaper();
        return bingWallpaper.todayWallpaper();
    }
}
