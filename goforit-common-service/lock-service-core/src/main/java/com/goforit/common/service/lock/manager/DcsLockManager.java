package com.goforit.common.service.lock.manager;

import com.goforit.common.service.lock.model.DcsResource;
import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockRequest;

import java.util.List;

/**
 * Created by junqingfjq on 16/4/9.
 */
public interface DcsLockManager {

    //~~~ 操作类 ~~~
    DcsResource createSharedResource(LockRequest lockRequest);

    DcsResourceLock lockSharedResource(LockRequest lockRequest);




    DcsResourceLock update(DcsResourceLock dcsResourceLock);

    void delete(String uniqueBizId);

    void deleteResource(String resourceId);

    void deleteExpiredLocks(String resourceId);

    //~~~ 查询类 ~~~

    DcsResourceLock get(String id);

    List<DcsResourceLock> findOthersLock(LockRequest lockRequest);

    DcsResourceLock findByUniqueBizId(String bizId);

}
