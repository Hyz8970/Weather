package entity.root;

import entity.HeWeather6;
import entity.Hourly;

import java.util.List;

public class HeWeather6Hourly extends HeWeather6 {
    private List<Hourly> hourly;

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
}
