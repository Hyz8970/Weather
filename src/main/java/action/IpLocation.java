package action;

import service.AMapIPLocation;

public class IpLocation {
    AMapIPLocation aMapIPLocation=new AMapIPLocation();
    /**
     * 获取当前访问主机ip地区
     * */
    public String locationByIP(){
        return aMapIPLocation.getLocation().getCity();
    }
}
