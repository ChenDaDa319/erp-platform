package com.erp.service.impl;

import com.erp.dao.BaseDao;
import com.erp.service.BaseService;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Long getCount(T t1, T t2, Object param) {
        return baseDao.getCount(t1,t2,param);
    }

    @Override
    public List<T> listByPage(T t1, T t2, Object param, int start, int rows) {
        return baseDao.listByPage(t1,t2,param,start,rows);
    }

    @Override
    public void deleteById(Long uuid) {
        baseDao.deleteById(uuid);
    }

    @Override
    public void add(T t1) {
        baseDao.add(t1);
    }

    @Override
    public T get(Long uuid) {
        return baseDao.get(uuid);
    }

    @Override
    public void update(T t1) {
        baseDao.update(t1);
    }


}
