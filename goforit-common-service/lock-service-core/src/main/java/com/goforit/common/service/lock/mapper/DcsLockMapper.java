package com.goforit.common.service.lock.mapper;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;

import java.util.Date;
import java.util.List;

/**
 * Created by junqingfjq on 16/4/5.
 */
public interface DcsLockMapper extends BaseMapper<DcsResourceLockDO,String>{


    void deleteExpiredLocksByResourceId(String resourceId);

    void deleteLockByBizId(String uniqueBizId);

    //TODO 此处传入超时时间 变为间隔时间
    int updateExpiredOnTime(String uniqueBizId,Date expiredDate);

    DcsResourceLockDO findLockByBizId(String uniqueBizId);

    List<DcsResourceLockDO> findOthersLock(String uniqueBizId,String resourceId);

    List<DcsResourceLockDO> findLocksByResourceId(String resourceId);

    DcsResourceLockDO findLivedLockByBizIdForUpdate(String uniqueBizId);

    List<DcsResourceLockDO> findLivedLocksByResourceId(String resourceId);
}
