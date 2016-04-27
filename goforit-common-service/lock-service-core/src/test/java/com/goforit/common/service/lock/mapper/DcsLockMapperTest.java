package com.goforit.common.service.lock.mapper;

import com.goforit.common.service.lock.LockBaseTest;
import com.goforit.common.service.lock.manager.SequenceManager;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;
import com.goforit.common.service.lock.model.dcs.LockType;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by junqingfjq on 16/4/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DcsLockMapperTest extends LockBaseTest{

    @Test
    @DirtiesContext
    @Transactional
    public void testBase(){

        preTestCleanResource();
        prepareResource();

        DcsResourceLockDO dcsResourceLockDO=new DcsResourceLockDO();
        baseLockId=sequenceManager.generate(SequenceManager.TableName.DCS_RESOURCE_LOCK);
        dcsResourceLockDO.setId(baseLockId);
        dcsResourceLockDO.setExpiredDate(getExpiredTime());
        dcsResourceLockDO.setLockName(LOCK_NAME);
        dcsResourceLockDO.setLockType(LockType.EXCLUSIVE.name());
        dcsResourceLockDO.setUniqueBizId(UUID.randomUUID().toString());
        dcsResourceLockDO.setResourceId(baseResourceId);
        dcsResourceLockDO.setOwner(OWNER);

        dcsLockMapper.create(dcsResourceLockDO);

        DcsResourceLockDO getR=dcsLockMapper.get(baseLockId);
        Assert.assertTrue(null!=getR);
        Assert.assertTrue(StringUtils.equals(getR.getLockName(), LOCK_NAME));
        String uniqueBizId=getR.getUniqueBizId();

        getR=dcsLockMapper.findLockByBizId(uniqueBizId);
        Assert.assertTrue(null!=getR);
        Assert.assertTrue(StringUtils.equals(getR.getLockName(), LOCK_NAME));

        List<DcsResourceLockDO> dcsResourceLockDOs= dcsLockMapper.findLivedLocksByResourceId(baseResourceId);
        Assert.assertTrue(!CollectionUtils.isEmpty(dcsResourceLockDOs));
        Assert.assertTrue(StringUtils.equals(dcsResourceLockDOs.get(0).getLockName(),LOCK_NAME));
        Assert.assertTrue(StringUtils.equals(dcsResourceLockDOs.get(0).getUniqueBizId(),uniqueBizId));

        DcsResourceLockDO forUpdateLock= dcsLockMapper.findLivedLockByBizIdForUpdate(uniqueBizId);
        Assert.assertTrue(StringUtils.equals(forUpdateLock.getId(),baseLockId));
        Assert.assertTrue(StringUtils.equals(forUpdateLock.getResourceId(),baseResourceId));

        dcsResourceLockDOs=dcsLockMapper.findLivedLocksByResourceId(baseResourceId);
        Assert.assertTrue(!CollectionUtils.isEmpty(dcsResourceLockDOs));
        Assert.assertTrue(StringUtils.equals(dcsResourceLockDOs.get(0).getLockName(),LOCK_NAME));

        dcsResourceLockDOs =dcsLockMapper.findOthersLock(uniqueBizId,baseResourceId);
        Assert.assertTrue(CollectionUtils.isEmpty(dcsResourceLockDOs));

        Date oldTime=getR.getExpiredDate();
        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        Date newTime=sequenceManager.generateSystemTime();
        dcsLockMapper.updateExpiredOnTime(uniqueBizId,newTime);
        getR=dcsLockMapper.get(baseLockId);
        Assert.assertTrue(getR.getExpiredDate().equals(newTime));

        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        getR=dcsLockMapper.findLivedLockByBizIdForUpdate(uniqueBizId);
        Assert.assertTrue(null==getR);

        dcsLockMapper.deleteExpiredLocksByResourceId(baseResourceId);
        getR=dcsLockMapper.findLockByBizId(uniqueBizId);
        Assert.assertTrue(null==getR);


    }
}
