package entity.root;

import java.util.List;

public class ForecastRoot {
    private List<HeWeather6Forecast> HeWeather6;

    public void setHeWeather6(List<HeWeather6Forecast> HeWeather6){
        this.HeWeather6 = HeWeather6;
    }
    public List<HeWeather6Forecast> getHeWeather6(){
        return this.HeWeather6;
    }
}