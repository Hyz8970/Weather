package com.hyz.weather.entity;
public class Air_now_city
{
    private String aqi;

    private String qlty;

    private String main;

    private String pm25;

    private String pm10;

    private String no2;

    private String so2;

    private String co;

    private String o3;

    private String pub_time;

    public void setAqi(String aqi){
        this.aqi = aqi;
    }
    public String getAqi(){
        return this.aqi;
    }
    public void setQlty(String qlty){
        this.qlty = qlty;
    }
    public String getQlty(){
        return this.qlty;
    }
    public void setMain(String main){
        this.main = main;
    }
    public String getMain(){
        return this.main;
    }
    public void setPm25(String pm25){
        this.pm25 = pm25;
    }
    public String getPm25(){
        return this.pm25;
    }
    public void setPm10(String pm10){
        this.pm10 = pm10;
    }
    public String getPm10(){
        return this.pm10;
    }
    public void setNo2(String no2){
        this.no2 = no2;
    }
    public String getNo2(){
        return this.no2;
    }
    public void setSo2(String so2){
        this.so2 = so2;
    }
    public String getSo2(){
        return this.so2;
    }
    public void setCo(String co){
        this.co = co;
    }
    public String getCo(){
        return this.co;
    }
    public void setO3(String o3){
        this.o3 = o3;
    }
    public String getO3(){
        return this.o3;
    }
    public void setPub_time(String pub_time){
        this.pub_time = pub_time;
    }
    public String getPub_time(){
        return this.pub_time;
    }
}
