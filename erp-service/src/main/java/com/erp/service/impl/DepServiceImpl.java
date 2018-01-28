package com.erp.service.impl;

import com.erp.dao.DepDao;
import com.erp.entity.Dep;
import com.erp.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("depService")
public class DepServiceImpl implements DepService {

    @Autowired
    private DepDao depDao;

    @Override
    public Long getCount(Dep dep1, Dep dep2, Object param) {
        return depDao.getCount(dep1,dep2,param);
    }

    @Override
    public List<Dep> listByPage(Dep dep1,Dep dep2,Object param,int start,int rows) {
        return depDao.listByPage(dep1,dep2,param,start,rows);
    }

    @Override
    public void deleteById(Long uuid) {
        depDao.deleteById(uuid);
    }

    @Override
    public void add(Dep dep1) {
        depDao.add(dep1);
    }

    @Override
    public Dep get(Long uuid) {
        return depDao.get(uuid);
    }

    @Override
    public void update(Dep dep1) {
        depDao.update(dep1);
    }


}
