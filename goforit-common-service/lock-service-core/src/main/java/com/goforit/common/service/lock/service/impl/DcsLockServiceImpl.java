package com.goforit.common.service.lock.service.impl;

import com.goforit.common.service.lock.manager.DcsLockManager;
import com.goforit.common.service.lock.model.DcsResource;
import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockQuery;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import com.goforit.common.service.lock.model.dcs.LockResult;
import com.goforit.common.service.lock.model.dcs.LockType;
import com.goforit.common.service.lock.service.DcsLockService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created by junqingfjq on 16/4/9.
 */

@Service(value = "dcsLockService")
public class DcsLockServiceImpl implements DcsLockService{

    @Autowired
    private DcsLockManager dcsLockManager;

    @Autowired
    private TransactionTemplate transactionTemplate;



    @Override
    public LockResult lockResource(final LockRequest lockRequest) {
        //TODO
        Preconditions.checkNotNull(lockRequest,"lockRequest");
        Preconditions.checkArgument(StringUtils.isNotBlank(lockRequest.getResourceName()),"resourceName");
        Preconditions.checkArgument(StringUtils.isNotBlank(lockRequest.getResourceType()),"resourceType");
        Preconditions.checkArgument(StringUtils.isNotBlank(lockRequest.getOwner()),"type");

        LockResult lockResult=null;

        //创建资源（内部包掉存在的情况）
        DcsResource dcsResource=dcsLockManager.createSharedResource(lockRequest);

        DcsResourceLock ownedLock=dcsLockManager.lockSharedResource(lockRequest);

        List<DcsResourceLock> othersLock=null;
        if(null==ownedLock){
            othersLock=dcsLockManager.findOthersLock(lockRequest.getUniqueBizId(),dcsResource.getId());
        }

        LockResult result=new LockResult(ownedLock,othersLock);

        return result;
    }

    @Override
    public void releaseLockByBizId(final String uniqueBizId) {

        Preconditions.checkArgument(StringUtils.isNotBlank(uniqueBizId));

        DcsResourceLock lock=dcsLockManager.findByUniqueBizId(uniqueBizId);
        if(null==lock){
            //TODO logger
        }

        final String resourceId=lock.getResourceId();
        //删除过期的锁
        dcsLockManager.deleteExpiredLocks(resourceId);

        //释放资源锁
        dcsLockManager.deleteLock(uniqueBizId);

        //释放资源
        dcsLockManager.deleteResource(resourceId);

    }



    @Override
    public boolean isLockAllowed(String resourceName, String resourceType, LockType type) {
        return false;
    }

    @Override
    public void releaseLockByResource(String resourceName, String resourceType) {

    }

    @Override
    public List<DcsResourceLock> findLocks(String resourceName, String resourceType, String owner) {
        return null;
    }

    @Override
    public boolean isLockedByOwner(String resourceName, String resource, String owner) {
        return false;
    }

    @Override
    public List<DcsResourceLock> query(LockQuery query) {
        return null;
    }

}
