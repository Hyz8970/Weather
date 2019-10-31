package com.hyz.weather.dao;

import com.hyz.weather.entity.Region;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

/**
 * 省市县数据
 * */
public class RegionDao extends CommonDao{
    /**
     * 按等级和上级ID查地区列表
     */
    public List<Region> getListByParentId(int parentId) {
        try (SqlSession session = getSessionFactory().openSession()) {
            return session.selectList(getMapping() + "listByParentId", parentId);
        }
    }

    /**
     * 修改Cid
     */
    public boolean updateCid(Region region) {
        try (SqlSession session = getSessionFactory().openSession()) {
            int update = session.update(getMapping() + "updateCid", region);
            session.commit();
            return update >= 1;
        }
    }
}
