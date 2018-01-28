package com.erp.service.impl;

import com.erp.dao.DepDao;
import com.erp.entity.Dep;
import com.erp.service.DepService;


public class DepServiceImpl extends BaseServiceImpl<Dep> implements DepService{

    private DepDao depDao;

    public void setDepDao(DepDao depDao) {
        this.depDao = depDao;
        /** 注意要把depDao设置给baseDao*/
        setBaseDao(depDao);
    }
}
