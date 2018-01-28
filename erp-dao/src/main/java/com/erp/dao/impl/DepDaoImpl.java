package com.erp.dao.impl;

import com.erp.dao.BaseDaoImpl;
import com.erp.dao.DepDao;
import com.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("depDao")
public class DepDaoImpl extends BaseDaoImpl implements DepDao{

    @Override
    public Long getCount(Dep dep1, Dep dep2, Object param) {
        DetachedCriteria dc = getDetachedCriteria(dep1, dep2, param);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dc);
        return list.get(0);
    }

    @Override
    public List<Dep> listByPage(Dep dep1,Dep dep2,Object param,int start,int rows) {
        DetachedCriteria dc = getDetachedCriteria(dep1, dep2, param);
        List<Dep> list = (List<Dep>) hibernateTemplate.findByCriteria(dc,start,rows);
        return list;
    }

    @Override
    public void deleteById(Long uuid) {
        Dep dep = new Dep();
        dep.setUuid(uuid);
        hibernateTemplate.delete(dep);
    }

    @Override
    public void add(Dep dep1) {
        hibernateTemplate.save(dep1);
    }

    @Override
    public Dep get(Long uuid) {
        return hibernateTemplate.get(Dep.class,uuid);
    }

    @Override
    public void update(Dep dep1) {
        hibernateTemplate.update(dep1);
    }


    /**
     * 离线查询对象
     * @param dep1
     * @param dep2
     * @param param
     * @return
     */
    private DetachedCriteria getDetachedCriteria(Dep dep1, Dep dep2, Object param){

        DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
        if (dep1 != null){
            if (dep1.getName().length()>0){
                dc.add(Restrictions.like("name",dep1.getName(), MatchMode.ANYWHERE));
            }
            if (dep1.getTele().length()>0){
                dc.add(Restrictions.like("tele",dep1.getTele(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }
}
