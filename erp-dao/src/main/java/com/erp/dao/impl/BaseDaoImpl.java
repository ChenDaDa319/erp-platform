package com.erp.dao.impl;


import com.erp.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> entityClass;

    public BaseDaoImpl() {
        //得到父类字节码对象
        Type type = this.getClass().getGenericSuperclass();
        //得到范型type
        ParameterizedType ptype = (ParameterizedType) type;
        //得到所有范型
        Type[] types = ptype.getActualTypeArguments();
        entityClass = (Class<T>) types[0];

    }

    @Override
    public Long getCount(T t1, T t2, Object param) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }

    @Override
    public List<T> listByPage(T t1,T t2,Object param,int start,int rows) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc,start,rows);
        return list;
    }

    @Override
    public void deleteById(Long uuid) {
        T t = get(uuid);
        getHibernateTemplate().delete(t);
    }


    @Override
    public void add(T t1) {
        getHibernateTemplate().save(t1);
    }

    @Override
    public T get(Long uuid) {
        return getHibernateTemplate().get(entityClass,uuid);
    }

    @Override
    public void update(T t1) {
        getHibernateTemplate().update(t1);
    }


    /**
     * 离线查询对象
     * @param t1
     * @param t2
     * @param param
     * @return
     */
    public DetachedCriteria getDetachedCriteria(T t1, T t2, Object param){

        return null;
    }
}
