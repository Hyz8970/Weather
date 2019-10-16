package entity.root;

import entity.HeWeather6;
import entity.Lifestyle;

import java.util.List;

public class HeWeather6Lifestyle extends HeWeather6 {
    private List<Lifestyle> lifestyle;

    public List<Lifestyle> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<Lifestyle> lifestyle) {
        this.lifestyle = lifestyle;
    }
}