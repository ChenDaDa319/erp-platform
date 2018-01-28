package com.erp.service;

import java.util.List;

public interface BaseService<T> {

    Long getCount(T t1, T t2, Object param);

    List<T> listByPage(T t1, T t2, Object param, int start, int rows);

    void deleteById(Long uuid);

    void add(T t1);

    T get(Long uuid);

    void update(T t1);
}
