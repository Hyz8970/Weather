package com.hyz.weather.entity;
public class HeWeather6
{
    private Basic basic;

    private Update update;

    private String status;

    public void setBasic(Basic basic){
        this.basic = basic;
    }
    public Basic getBasic(){
        return this.basic;
    }
    public void setUpdate(Update update){
        this.update = update;
    }
    public Update getUpdate(){
        return this.update;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}