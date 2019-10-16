package utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.Properties;

public class NetTool {
    private OkHttpClient client = new OkHttpClient();
    private Properties config = new Properties();
    private String key="";
    /**
     * 请求HeWeather
     * */
    public String HeWeatherRQ(String api,String location) {
        if (key.equals("")){
            try{
                InputStream inputStream = new BufferedInputStream(
                        new FileInputStream(NetTool.class.getResource("").getPath() +
                                "/../config.properties"));
                config.load(inputStream);
                key = config.getProperty("key");
            }catch (IOException e){
                e.printStackTrace();
                return "";
            }
        }
        String url = "https://free-api.heweather.net/s6/weather/"+ api
                +"?location="+ location +"&key=" + key;
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
