package com.hyz.weather.service;

import com.hyz.weather.utils.NetTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BingWallpaper {
    private NetTool netTool=new NetTool();
    /**
     * DOM页面数据分解出图片URI
     * */
    public String todayWallpaper(){
        String url="http://cn.bing.com";
        String content=netTool.bingWallpaper(url);
        try {
            Document bing=Jsoup.parse(content);
            Elements bgLink = bing.select("#bgLink");
            Element first = bgLink.first();
            return url + first.attr("href");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
