package dao;

import entity.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RegionDao {
    private Configuration cfg = new Configuration();
    private SessionFactory sf = cfg.configure().buildSessionFactory();
//    private Session session = sf.openSession();
    private Class regionClass = Region.class;

    protected Session getCurrentSession() {
        return sf.openSession();
    }

    /**
     * 按等级和上级ID查地区列表
     * */
    public List<Region> getList(int level,int parent){
        return (List<Region>)getCurrentSession()
                .createQuery("FROM " + regionClass + " r WHERE r.level=:level AND r.parentId=:parent")
                .setParameter("level", level)
                .setParameter("parent", parent)
                .list();
    }

}
