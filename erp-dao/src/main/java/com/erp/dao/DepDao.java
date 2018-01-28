package com.erp.dao;

import com.erp.entity.Dep;

import java.util.List;

public interface DepDao {

    /**
     * 统计查询个数
     * @param dep1
     * @param dep2
     * @param param
     * @return
     */
    Long getCount(Dep dep1, Dep dep2, Object param);
    /**
     * 分页查询
     * @param dep1
     * @param dep2
     * @param param
     * @param start
     * @param rows
     * @return
     */
    List<Dep> listByPage(Dep dep1,Dep dep2,Object param,int start,int rows);

    /**
     * 根据id删除信息
     * @param uuid
     */
    void deleteById(Long uuid);

    /**
     * 增加数据
     * @param dep1
     */
    void add(Dep dep1);

    /**
     * 根据id获取数据
     * @param uuid
     */
    Dep get(Long uuid);

    void update(Dep dep1);
}
