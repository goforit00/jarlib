package com.goforit.common.service.lock;

import com.goforit.common.service.lock.manager.SequenceManager;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;
import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;
import com.goforit.common.service.lock.mapper.DcsLockMapper;
import com.goforit.common.service.lock.mapper.DcsResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by junqingfjq on 16/4/24.
 */
public abstract class LockBaseTest {

    @Autowired
    protected DcsLockMapper dcsLockMapper;

    @Autowired
    protected DcsResourceMapper dcsResourceMapper;

    @Autowired
    protected SequenceManager sequenceManager;

    public static final String RESOURCE_NAME="test_resource_name";

    public static final String RESOURCE_TYPE="test_resource_type";

    public static final String OWNER="test_lock_owner";

    public static final String LOCK_NAME="test_lock_name";

    public static final int MAX_LOCK_NUM=10;

    public static final int EXPIRED_DATE=30;

    public String baseResourceId="";

    public String baseLockId="";

    protected void preTestCleanResource(){
        DcsResourceDO dcsResourceDO=dcsResourceMapper.findByResourceNameAndType(RESOURCE_NAME,RESOURCE_TYPE);
        if(null!=dcsResourceDO){
            String resourceId=dcsResourceDO.getId();
            List<DcsResourceLockDO> lockDOs= dcsLockMapper.findLocksByResourceId(resourceId);

            for(DcsResourceLockDO lockDO:lockDOs){
                dcsLockMapper.deleteLockByBizId(lockDO.getUniqueBizId());
            }

            dcsResourceMapper.delete(dcsResourceDO.getId());
        }
    }


    protected void prepareResource(){

        DcsResourceDO dcsResourceDO=new DcsResourceDO();
        baseResourceId=sequenceManager.generate(SequenceManager.TableName.DCS_RESOURCE);
        dcsResourceDO.setId(baseResourceId);
        dcsResourceDO.setMaxLock(MAX_LOCK_NUM);
        dcsResourceDO.setName(RESOURCE_NAME);
        dcsResourceDO.setType(RESOURCE_TYPE);
        dcsResourceDO.setUniqueBizId(UUID.randomUUID().toString());

        dcsResourceMapper.create(dcsResourceDO);
    }

    protected Date getExpiredTime(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.SECOND,EXPIRED_DATE);

        return calendar.getTime();
    }
}
