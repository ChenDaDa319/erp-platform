package com.erp.dao.impl;

import com.erp.dao.DepDao;
import com.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


public class DepDaoImpl extends BaseDaoImpl<Dep> implements DepDao{

    public DetachedCriteria getDetachedCriteria(Dep dep1, Dep dep2, Object param){

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
