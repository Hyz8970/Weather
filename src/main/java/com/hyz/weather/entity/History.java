package com.hyz.weather.entity;

public class History {
    private int id;
    private int cid;
    private int isUser;
    private String name;
    private String now;
    private String airNow;
    private String hourly;
    private String forecast;
    private String lifestyle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getAirNow() {
        return airNow;
    }

    public void setAirNow(String airNow) {
        this.airNow = airNow;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }
}
