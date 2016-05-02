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


//    DcsResourceLock update(DcsResourceLock dcsResourceLock);

    void deleteLock(String uniqueBizId);

    void deleteResource(String resourceId);

    void deleteExpiredLocks(String resourceId);

    //~~~ 查询类 ~~~

//    DcsResource lockResourceByResourceId(String resourceId);

//    DcsResourceLock get(String id);

    DcsResource findResourceForUpdate(String resourceName,String resourceType);

    List<DcsResourceLock> findOthersLock(String ownedLockBizId,String resourceId);

    DcsResourceLock findByUniqueBizId(String bizId);

//    List<DcsResourceLock> findByResourceId(String resourceId);


}
