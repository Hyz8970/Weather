package com.hyz.weather.service;

import com.google.gson.Gson;
import com.hyz.weather.dao.HistoryDao;
import com.hyz.weather.entity.*;
import com.hyz.weather.entity.root.HeWeather6Now;

import java.util.List;

public class HistoryService {
    private HistoryDao historyDao = new HistoryDao();
    private Gson gson = new Gson();

    public List<History> getHistoryList() {
        return historyDao.getList();
    }

    public History getHistory() {
        return historyDao.getHistory();
    }

    public boolean updateCurrent(HeWeather6Now now, Air_now_city air_now_city, List<Hourly> hourly,
                                 List<Daily_forecast> daily_forecast, List<Lifestyle> lifestyle) {
        History history = new History();
        Basic basic = now.getBasic();
        history.setCid(basic.getCid());
        history.setName(basic.getLocation());
        history.setIsUse(1);
        history.setNow(gson.toJson(now.getNow()));
        history.setAirNow(gson.toJson(air_now_city));
        history.setHourly(gson.toJson(hourly));
        history.setForecast(gson.toJson(daily_forecast));
        history.setLifestyle(gson.toJson(lifestyle));
        return historyDao.updateCurrent(history);
    }

    public boolean delHistory(String cid) {
        return historyDao.delHistory(cid);
    }
}
