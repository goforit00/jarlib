package com.goforit.common.service.lock.manager;

import com.goforit.common.service.lock.model.DcsResourceLock;

/**
 * Created by junqingfjq on 16/4/9.
 */
public interface DcsLockManager {

    //~~~ 操作类 ~~~
    DcsResourceLock create(DcsResourceLock lock);

    DcsResourceLock update(DcsResourceLock dcsResourceLock);

    void delete(DcsResourceLock lock);

    //~~~ 查询类 ~~~
    DcsResourceLock get(String id);

}
