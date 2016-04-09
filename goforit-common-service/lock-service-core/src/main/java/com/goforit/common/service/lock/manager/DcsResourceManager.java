package com.goforit.common.service.lock.manager;

import com.goforit.common.service.lock.model.DcsResource;

/**
 * Created by junqingfjq on 16/4/9.
 */
public interface DcsResourceManager {

    //~~~ 操作类 ~~~
    DcsResource create(DcsResource dcsResource);

    DcsResource update(DcsResource dcsResource);

    void delete(String id);

    //~~~ 查询类 ~~~

    DcsResource get(String id);
}
