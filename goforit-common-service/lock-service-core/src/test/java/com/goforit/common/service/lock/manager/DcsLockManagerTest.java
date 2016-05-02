package com.goforit.common.service.lock.manager;

import com.goforit.common.service.lock.LockBaseTest;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;
import com.goforit.common.service.lock.model.DcsResource;
import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by junqingfjq on 16/4/28.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DcsLockManagerTest extends LockBaseTest {

    @Test
    @DirtiesContext
//    @Transactional
    public void baseTest(){
        preTestCleanResource();

        LockRequest request=LockRequest.buildRequest(RESOURCE_NAME,RESOURCE_TYPE,LOCK_NAME,OWNER);

        DcsResource resource=dcsLockManager.createSharedResource(request);
        Assert.assertTrue(null!=resource);
        Assert.assertTrue(StringUtils.isNotBlank(resource.getId()));
        Assert.assertTrue(StringUtils.equals(resource.getName(),RESOURCE_NAME));

        baseResourceId=resource.getId();
        DcsResourceDO getDO=dcsResourceMapper.get(baseResourceId);
        Assert.assertTrue(null!=getDO);
        Assert.assertTrue(StringUtils.equals(getDO.getName(),RESOURCE_NAME));

        DcsResourceLock resourceLock=dcsLockManager.lockSharedResource(request);
        Assert.assertTrue(null!=resourceLock);
        Assert.assertTrue(resourceLock.getExpiredDate().after(new Date()));
        Assert.assertTrue(StringUtils.isNotBlank(resourceLock.getUniqueBizId()));
        Assert.assertTrue(StringUtils.equals(resourceLock.getLockName(),LOCK_NAME));

        String uniqueBizId=resourceLock.getUniqueBizId();

        resourceLock=dcsLockManager.findByUniqueBizId(uniqueBizId);
        Assert.assertTrue(null!=resourceLock);
        Assert.assertTrue(StringUtils.equals(LOCK_NAME,resourceLock.getLockName()));

        baseLockId=resourceLock.getId();

        List<DcsResourceLock> locks= dcsLockManager.findOthersLock(uniqueBizId,baseResourceId);
        Assert.assertTrue(CollectionUtils.isEmpty(locks));

        dcsLockManager.deleteExpiredLocks(baseResourceId);

        resourceLock=dcsLockManager.findByUniqueBizId(uniqueBizId);
        Assert.assertTrue(null!=resourceLock);

        dcsLockManager.deleteLock(uniqueBizId);
        resourceLock=dcsLockManager.findByUniqueBizId(uniqueBizId);
        Assert.assertTrue(null==resourceLock);

        dcsLockManager.deleteResource(baseResourceId);
        getDO=dcsResourceMapper.get(baseResourceId);
        Assert.assertTrue(null==getDO);
    }
}
