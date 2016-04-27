package com.goforit.common.service.lock.mapper;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goforit.common.service.lock.LockBaseTest;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;

/**
 * Created by junqingfjq on 16/4/28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DcsResourceMapperTest extends LockBaseTest{

    @Test
    @DirtiesContext
    @Transactional
    public void testBase(){

        preTestCleanResource();
        prepareResource();

        DcsResourceDO resourceDO=dcsResourceMapper.findByResourceNameAndType(RESOURCE_NAME, RESOURCE_NAME);
        Assert.assertTrue(null!=resourceDO);
        Assert.assertTrue(StringUtils.equals(resourceDO.getId(),baseResourceId));

        resourceDO=dcsResourceMapper.findResourceForUpdate(baseResourceId);
        Assert.assertTrue(null!=resourceDO);
        Assert.assertTrue(StringUtils.equals(resourceDO.getName(),RESOURCE_NAME));
    }
}
