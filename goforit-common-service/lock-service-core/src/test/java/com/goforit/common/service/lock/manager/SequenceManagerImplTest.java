package com.goforit.common.service.lock.manager;

import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by junqingfjq on 16/4/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SequenceManagerImplTest{

    @Autowired
    protected SequenceManager sequenceManager;

    @Test
    @DirtiesContext
//    @Transactional
    public void testBase(){

        String id=sequenceManager.generate(SequenceManager.TableName.DCS_RESOURCE);
        Assert.assertTrue(StringUtils.isNotBlank(id));

    }
}
