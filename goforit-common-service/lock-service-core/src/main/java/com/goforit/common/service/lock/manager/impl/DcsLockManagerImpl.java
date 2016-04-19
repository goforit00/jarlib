package com.goforit.common.service.lock.manager.impl;

import java.util.List;

import com.goforit.common.service.lock.convertor.DcsLockConvertor;
import com.goforit.common.service.lock.convertor.DcsResourceConvertor;
import com.goforit.common.service.lock.manager.SequenceManager;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;
import com.goforit.common.service.lock.mapper.DcsResourceMapper;
import com.goforit.common.service.lock.model.dcs.LockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.goforit.common.service.lock.manager.DcsLockManager;
import com.goforit.common.service.lock.mapper.DcsLockMapper;
import com.goforit.common.service.lock.model.DcsResource;
import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockRequest;

/**
 * Created by junqingfjq on 16/4/9.
 */
@Service(value = "dcsLockManager")
public class DcsLockManagerImpl implements DcsLockManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DcsLockManagerImpl.class);

    @Autowired
    private SequenceManager sequenceManager;

    @Autowired
    private DcsLockMapper dcsLockMapper;

    @Autowired
    private DcsResourceMapper dcsResourceMapper;

    //~~~ public method ~~~
    @Override
    public DcsResource createSharedResourceAndLock(LockRequest lockRequest) {

        final String resourceName=lockRequest.getResourceName();
        final String resourceType=lockRequest.getResourceType();

        DcsResource dcsResource=findByResourceNameAndType(resourceName, resourceType);

        try{
            if(null==dcsResource){
                dcsResource= DcsResourceConvertor.lockRequest2Bo(lockRequest);

                createResource(dcsResource);
            }
        }catch (DuplicateKeyException de){
            //允许冲突发生 保证资源存在即可
            LOGGER.warn("xxx");
        }

        dcsResource=findByResourceNameAndType(resourceName, resourceType);
        if(null!=dcsResource){
            dcsResource=lockByResourceId(dcsResource.getId());
        }

        return dcsResource;
    }

    @Override
    public DcsResourceLock lockSharedResource(LockRequest lockRequest) {

        final String uniqueBizId=lockRequest.getUniqueBizId();
        final String resourceName=lockRequest.getResourceName();
        final String resourceType=lockRequest.getResourceType();

        DcsResourceLock dcsResourceLock= findLivedLockByBizIdForUpdate(uniqueBizId);

        //已有所，返回
        if(null!=dcsResourceLock){
            updateLock(dcsResourceLock);
            return dcsResourceLock;
        }

        //查找资源
        DcsResource sharedResource=findByResourceNameAndType(resourceName,resourceType);

        //是否允许加锁
        if(isAllowedLock(sharedResource,lockRequest.getLockType())){
            dcsResourceLock = DcsLockConvertor.lockRequest2Bo(lockRequest);
            try{
                dcsResourceLock=createLock(dcsResourceLock);
            }catch (Exception e){
                //TODO 确定外键异常
                return null;
            }
        }else {
            return null;
        }

        return dcsResourceLock;
    }

    @Override
    public DcsResourceLock update(DcsResourceLock dcsResourceLock) {
        return null;
    }

    @Override
    public void deleteLock(String uniqueBizId) {
        dcsLockMapper.deleteLockByBizId(uniqueBizId);
    }

    @Override
    public void deleteResource(String resourceId) {



    }

    @Override
    public void deleteExpiredLocks(String resourceId) {
        dcsLockMapper.deleteExpiredLocksByResourceId(resourceId);
    }

    @Override
    public DcsResource lockResourceByResourceId(String resourceId) {
        return null;
    }

    @Override
    public DcsResourceLock get(String id) {
        return null;
    }

    @Override
    public List<DcsResourceLock> findOthersLock(String ownedLockBizId,String resourceId) {

        List<DcsResourceLockDO> dcsResourceLockDOs=dcsLockMapper.findOthersLock(ownedLockBizId,resourceId);

        return DcsLockConvertor.batchDo2Bo(dcsResourceLockDOs);
    }

    @Override
    public DcsResourceLock findByUniqueBizId(String bizId) {
        return null;
    }

    @Override
    public List<DcsResourceLock> findByResourceId(String resourceId) {
        return null;
    }

    private DcsResource lockByResourceNameAndType(String resourceName, String resourceType){
        return null;
    }

    private DcsResource findByResourceNameAndType(String resourceName,String resourceType){
        return null;
    } 
    private DcsResource createResource(DcsResource resource){

        String dcsResourceId=sequenceManager.generate(SequenceManager.TableName.DCS_RESOURCE);
        dcsResourceMapper.create(DcsResourceConvertor.bo2Do(resource));

        return null;
    }

    private DcsResourceLock findLockByBizId(String uniqueBizId){
        dcsLockMapper.findLockByBizId(uniqueBizId);
        return null;
    }

    private DcsResourceLock findLivedLockByBizIdForUpdate(String uniqueBizId){
        return null;
    }

    private DcsResourceLock createLock(DcsResourceLock dcsResourceLock){
        return null;
    }

    private List<DcsResourceLock> findLivedLocksByResourceNameAndType(String resourceName, String resourceType){
        return null;
    }

    private void updateLock(DcsResourceLock dcsResourceLock){

    }

    private boolean isAllowedLock(DcsResource resource,LockType lockType){
        return true;
    }

    private DcsResource lockByResourceId(String resourceId){
        dcsResourceMapper.lockByResourceId(resourceId);
        return null;
    }

}
