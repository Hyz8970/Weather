package entity.root;

import java.util.List;

public class HourlyRoot {
    private List<HeWeather6Hourly> HeWeather6;

    public void setHeWeather6(List<HeWeather6Hourly> HeWeather6){
        this.HeWeather6 = HeWeather6;
    }
    public List<HeWeather6Hourly> getHeWeather6(){
        return this.HeWeather6;
    }
}
