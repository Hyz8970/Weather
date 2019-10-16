package service;

import com.google.gson.Gson;
import entity.root.AMapIPRoot;
import utils.NetTool;

public class AMapIPLocation {
    private NetTool netTool=new NetTool();
    private Gson gson = new Gson();
    /**
     * 高德IP定位
     * */
    public AMapIPRoot getLocation(){
        String location = netTool.AMapIpLocation();
        if (!location.equals("")){
            AMapIPRoot root = gson.fromJson(location,AMapIPRoot.class);
            if (root.getStatus().equals("1")){
                return root;
            }
        }
        return null;
    }
}
