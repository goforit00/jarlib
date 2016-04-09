package com.goforit.common.service.lock.mapper;

import java.io.Serializable;

/**
 * Created by junqingfjq on 16/4/9.
 */
public interface BaseMapper<T,PK extends Serializable> {

    //~~~ 操作类 ~~~
    void create(T t);

    int update(T t);

    int delete(PK id);


    //~~~ 查询类 ~~~
    T get(PK id);


}
