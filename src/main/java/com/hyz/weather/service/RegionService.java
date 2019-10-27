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
    /**
     * 市列表
     * @param province 省ID
     * */
    public List<Region> cityList(int province){
        return regionDao.getListByParentId(province);
    }
    /**
     * 县列表
     * @param city 市ID
     * */
    public List<Region> countyList(int city){
        return regionDao.getListByParentId(city);
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
