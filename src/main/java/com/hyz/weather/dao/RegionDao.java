package com.hyz.weather.dao;

import com.hyz.weather.entity.Region;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionDao {
    private SqlSessionFactory sessionFactory;
    private String mapping = "com.hyz.weather.entity.mapping.";

    public RegionDao() {
        InputStream is = null;
        is=Region.class.getClassLoader().getResourceAsStream("./cfg.xml");
//        try {
//            is = new BufferedInputStream(
//                    new FileInputStream(RegionDao.class.getResource("").getPath() +
//                            "/../../../../cfg.xml"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 按等级和上级ID查地区列表
     */
    public List<Region> getListByParentId(int parentId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return session.selectList(mapping + "listByParentId", parentId);
        }
    }

    /**
     * 修改Cid
     */
    public boolean updateCid(Region region) {
        try (SqlSession session = sessionFactory.openSession()) {
            int update = session.update(mapping + "updateCid", region);
            return update >= 1;
        }
    }
}
