package com.hyz.weather.dao;

import com.hyz.weather.entity.History;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 天气历史数据
 */
public class HistoryDao extends CommonDao {
    public List<History> getList() {
        try (SqlSession sqlSession = getSessionFactory().openSession()) {
            return sqlSession.selectList(getMapping() + "historyList");
        }
    }

    public boolean updateCurrent(History history) {
        try (SqlSession sqlSession = getSessionFactory().openSession()) {
            History result = sqlSession.selectOne(getMapping() + "historyByCid", history.getCid());
            if (result != null) {
                history.setId(result.getId());
                //存在即更新
                int update = sqlSession.update(getMapping() + "historyCurrent", history);
                sqlSession.commit();
                return update == 1;
            } else {
                //不存在即插入
                int insert = sqlSession.insert(getMapping() + "historyInsert", history);
                sqlSession.commit();
                return insert == 1;
            }
        }
    }

    public History getHistory() {
        try (SqlSession sqlSession = getSessionFactory().openSession()) {
            return sqlSession.selectOne(getMapping() + "historyIsUse");
        }
    }
}