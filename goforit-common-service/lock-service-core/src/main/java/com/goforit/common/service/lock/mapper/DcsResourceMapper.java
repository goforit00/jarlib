package com.goforit.common.service.lock.mapper;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceDO;

/**
 * Created by junqingfjq on 16/4/5.
 */
public interface DcsResourceMapper extends BaseMapper<DcsResourceDO,String>{

    DcsResourceDO findByResourceNameAndType(String name,String type);

    DcsResourceDO lockByResourceNameAndType(String name,String type);

    DcsResourceDO lockByResourceId(String resourceId);

}
