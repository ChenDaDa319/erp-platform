package com.erp.dao;


import java.util.List;

public interface BaseDao<T> {

    /**
     * 统计查询个数
     * @param t1
     * @param t2
     * @param param
     * @return
     */
    Long getCount(T t1, T t2, Object param);
    /**
     * 分页查询
     * @param t1
     * @param t2
     * @param param
     * @param start
     * @param rows
     * @return
     */
    List<T> listByPage(T t1, T t2, Object param, int start, int rows);

    /**
     * 根据id删除信息
     * @param t1
     */
    void deleteById(Long uuid);

    /**
     * 增加数据
     * @param t1
     */
    void add(T t1);

    /**
     * 根据id获取数据
     * @param uuid
     */
    T get(Long uuid);

    void update(T t1);
}
