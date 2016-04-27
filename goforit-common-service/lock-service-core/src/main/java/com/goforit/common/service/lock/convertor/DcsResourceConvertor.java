package com.goforit.common.service.lock.convertor;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;
import com.goforit.common.service.lock.model.DcsResource;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import org.springframework.beans.BeanUtils;

/**
 * Created by junqingfjq on 16/4/12.
 */
public class DcsResourceConvertor {

    public static DcsResourceDO bo2Do(DcsResource dcsResource){

        if(null==dcsResource){
            return null;
        }

        DcsResourceDO dcsResourceDO=new DcsResourceDO();
        BeanUtils.copyProperties(dcsResource,dcsResourceDO);

        return dcsResourceDO;
    }

    public static DcsResource do2Bo(DcsResourceDO dcsResourceDO){

        if(null==dcsResourceDO){
            return null;
        }

        DcsResource dcsResource=new DcsResource();
        BeanUtils.copyProperties(dcsResourceDO,dcsResource);

        return dcsResource;
    }

    public static DcsResource lockRequest2Bo(LockRequest lockRequest){

        if(null==lockRequest){
            return null;
        }

        DcsResource dcsResource=new DcsResource();

        return dcsResource.buildDcsResource(lockRequest);
    }
}
