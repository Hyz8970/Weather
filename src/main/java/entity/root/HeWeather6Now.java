package entity.root;

import entity.HeWeather6;
import entity.Now;

public class HeWeather6Now extends HeWeather6 {
    private entity.Now now;

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }
}
