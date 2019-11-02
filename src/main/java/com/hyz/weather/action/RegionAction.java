package com.hyz.weather.action;

import com.hyz.weather.service.RegionService;
import com.hyz.weather.entity.Region;

import java.util.List;

public class RegionAction {
    private RegionService regionService=new RegionService();

    public List<Region> provinceList(){
        return regionService.provinceList();
    }

    public List<Region> nextRegionList(int lastId){
        return regionService.nextRegionList(lastId);
    }
}
