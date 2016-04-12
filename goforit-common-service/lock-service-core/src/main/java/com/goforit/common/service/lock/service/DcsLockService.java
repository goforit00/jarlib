package com.goforit.common.service.lock.service;

import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockQuery;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import com.goforit.common.service.lock.model.dcs.LockResult;
import com.goforit.common.service.lock.model.dcs.LockType;

import java.util.List;

/**
 * Created by junqingfjq on 16/4/9.
 */
public interface DcsLockService {

    /**
     * 对资源进行加锁
     * @param lockRequest
     * @return
     */
    LockResult lockResource(LockRequest lockRequest);

    /**
     * 根据uniqueBizId释放锁
     * @param uniqueBizId
     */
    void releaseLockByBizId(String uniqueBizId);


    /**
     * 是否允许对资源加锁
     *
     * @param resourceName
     * @param resourceType
     * @param type
     * @return
     */
    boolean isLockAllowed(String resourceName,String resourceType,LockType type);

    /**
     * 强制释放资源的所有锁
     */
    void releaseLockByResource(String resourceName,String resourceType);


    /**
     * 查询资源下某个owner的锁
     * @param resourceName
     * @param resourceType
     * @param owner 为空时表示查询这个资源下的所有锁
     * @return
     */
    List<DcsResourceLock> findLocks(String resourceName,String resourceType,String owner);

    boolean isLockedByOwner(String resourceName,String resource,String owner);

    /**
     * 查询锁
     * @param query
     * @return
     */
    List<DcsResourceLock> query(LockQuery query);

}
