package com.hyz.weather.service;

import com.hyz.weather.dao.RegionDao;
import com.hyz.weather.entity.Region;

import java.util.List;

public class RegionService {
    private RegionDao regionDao=new RegionDao();
    /**
     * 单个
     * */
    public Region getRegion(int id){
        return regionDao.getRegion(id);
    }

    /**
     * 省列表
     * */
    public List<Region> provinceList(){
        return regionDao.getListByParentId(0);
    }
    /*下级地区列表*/
    public List<Region> nextRegionList(int lastId) {
        return regionDao.getListByParentId(lastId);
    }
}
