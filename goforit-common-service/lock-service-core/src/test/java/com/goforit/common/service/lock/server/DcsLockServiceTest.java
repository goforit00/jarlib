package com.goforit.common.service.lock.server;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goforit.common.service.lock.LockBaseTest;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import com.goforit.common.service.lock.model.dcs.LockResult;

/**
 * Created by junqingfjq on 16/5/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DcsLockServiceTest extends LockBaseTest {

    @Test
    @DirtiesContext
    @Transactional
    public void testBase(){

        preTestCleanResource();

        LockRequest request=LockRequest.buildRequest(RESOURCE_NAME,RESOURCE_TYPE,LOCK_NAME,OWNER);
        LockResult result=dcsLockService.lockResource(request);
        Assert.assertTrue(null!=result);
        Assert.assertTrue(null!=result.getOwnedDcsResourceLock());

        baseResourceId=result.getOwnedDcsResourceLock().getResourceId();
        baseLockId=result.getOwnedDcsResourceLock().getId();
        String uniqueBizId=result.getOwnedDcsResourceLock().getUniqueBizId();

        Assert.assertTrue(StringUtils.isNotBlank(uniqueBizId));
        Assert.assertTrue(StringUtils.isNotBlank(baseResourceId));
        Assert.assertTrue(StringUtils.isNotBlank(baseLockId));

//        dcsLockService.findLocks(RESOURCE_NAME,RESOURCE_TYPE,OWNER);

        DcsResourceLockDO lockDO=dcsLockMapper.findLockByBizId(uniqueBizId);
        Assert.assertTrue(null!=lockDO);

        DcsResourceDO resourceDO=dcsResourceMapper.findByResourceNameAndType(RESOURCE_NAME,RESOURCE_TYPE);
        Assert.assertTrue(null!=resourceDO);

        dcsLockService.releaseLockByBizId(uniqueBizId);
        lockDO=dcsLockMapper.findLockByBizId(uniqueBizId);
        Assert.assertTrue(null==lockDO);

        resourceDO=dcsResourceMapper.findByResourceNameAndType(RESOURCE_NAME,RESOURCE_TYPE);
        Assert.assertTrue(null == resourceDO);

    }

}
