package com.hyz.weather.action;

import com.hyz.weather.entity.*;
import com.hyz.weather.entity.root.*;
import com.hyz.weather.service.HistoryService;

import java.util.List;

public class HistoryAction {
    private HistoryService historyService=new HistoryService();
    /**
     * 获取记录
     * */
    public List<History> getHistoryList(){
        return historyService.getHistoryList();
    }

    /**
     * 获取上一次的地区记录
     * */
    public History getHistory(){
        return historyService.getHistory();
    }

    /**
     * 更新当前地区的数据
     * */
    public boolean updateCurrent(HeWeather6Now now, Air_now_city air_now_city, List<Hourly> hourly,
                                 List<Daily_forecast> daily_forecast, List<Lifestyle> lifestyle){
        return historyService.updateCurrent(now,air_now_city,hourly, daily_forecast, lifestyle);
    }

    public boolean delHistory(String cid) {
        return historyService.delHistory(cid);
    }
}
