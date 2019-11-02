package com.hyz.weather.service;

import com.hyz.weather.dao.RegionDao;
import com.hyz.weather.entity.Region;

import java.util.List;

public class RegionService {
    private RegionDao regionDao=new RegionDao();
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

    /**
     * 修改天气预报使用的Cid
     * */
    public boolean updateCid(Region region,String cid){
        region.setCid(cid);
        boolean b = regionDao.updateCid(region);
        if (!b){
            System.out.println("update cid fail");
        }
        return b;
    }
}
