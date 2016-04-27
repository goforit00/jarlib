package com.goforit.common.service.lock.convertor;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;
import com.goforit.common.service.lock.model.DcsResourceLock;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import com.goforit.common.service.lock.model.dcs.LockType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junqingfjq on 16/4/12.
 */
public class DcsLockConvertor {

    public static DcsResourceLock do2Bo(DcsResourceLockDO dcsResourceLockDO){

        if(null==dcsResourceLockDO){
            return null;
        }

        DcsResourceLock dcsResourceLock=new DcsResourceLock();
        BeanUtils.copyProperties(dcsResourceLockDO,dcsResourceLock,new String[]{"lockType"});
        dcsResourceLock.setLockType(LockType.valueOf(dcsResourceLockDO.getLockType()));

        return dcsResourceLock;
    }

    public static DcsResourceLockDO bo2Do(DcsResourceLock dcsResourceLock){

        if(null==dcsResourceLock){
            return null;
        }

        DcsResourceLockDO dcsResourceLockDO=new DcsResourceLockDO();
        BeanUtils.copyProperties(dcsResourceLock,dcsResourceLockDO,new String[]{"lockType"});
        dcsResourceLockDO.setLockType(dcsResourceLock.getLockType().name());

        return dcsResourceLockDO;
    }

    public static DcsResourceLock lockRequest2Bo(LockRequest lockRequest){

        if(null==lockRequest){
            return null;
        }

        DcsResourceLock dcsResourceLock=new DcsResourceLock();
        dcsResourceLock.buildResourceLock(lockRequest);

        return dcsResourceLock;
    }

    public static List<DcsResourceLock> batchDo2Bo(List<DcsResourceLockDO> dcsResourceLockDOs){

        List<DcsResourceLock> dcsResourceLocks= new ArrayList<DcsResourceLock>();

        if(null!=dcsResourceLockDOs){
            for(DcsResourceLockDO dcsResourceLockDO:dcsResourceLockDOs){
                dcsResourceLocks.add(do2Bo(dcsResourceLockDO));
            }
        }

        return dcsResourceLocks;
    }

}
