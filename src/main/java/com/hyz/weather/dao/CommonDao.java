package com.hyz.weather.dao;

import com.hyz.weather.entity.Region;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class CommonDao {
    private SqlSessionFactory sessionFactory;
    private String mapping = "com.hyz.weather.entity.mapping.";

    public CommonDao() {
        InputStream is = Region.class.getClassLoader().getResourceAsStream("./cfg.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
    }
    public SqlSessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public String getMapping() {
        return mapping;
    }
}
