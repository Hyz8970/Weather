package utils;

import okhttp3.*;

import java.io.*;
import java.util.Properties;

public class NetTool {
    private OkHttpClient client;
    private Properties config;
    private String heWeatherKey = "";
    private String aMapKey = "";

    public NetTool(){
        config = new Properties();
        try{
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream(NetTool.class.getResource("").getPath() +
                            "/../config.properties"));
            config.load(inputStream);
            heWeatherKey = config.getProperty("heWeatherKey");
            aMapKey = config.getProperty("aMapKey");
        }catch (IOException e){
            e.printStackTrace();
        }
        client = new OkHttpClient();
    }

    /**
     * 请求HeWeather
     * */
    public String HeWeatherRQ(String api,String location) {
        String url = "https://free-api.heweather.net/s6/weather/"+ api
                +"?location="+ location +"&key=" + heWeatherKey;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 高德IP定位API(本机)
     * */
    public String AMapIpLocation(){
        String url = "https://restapi.amap.com/v3/ip?key="+aMapKey;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
