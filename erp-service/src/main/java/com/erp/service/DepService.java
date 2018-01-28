package com.erp.service;

import com.erp.entity.Dep;

import java.util.List;

public interface DepService {

    Long getCount(Dep dep1, Dep dep2, Object param);

    List<Dep> listByPage(Dep dep1,Dep dep2,Object param,int start,int rows);

    void deleteById(Long uuid);

    void add(Dep dep1);

    Dep get(Long uuid);

    void update(Dep dep1);
}
