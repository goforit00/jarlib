package com.goforit.common.service.lock.mapper;

import com.goforit.common.service.lock.mapper.DOS.DcsResourceLockDO;

import java.util.List;

/**
 * Created by junqingfjq on 16/4/5.
 */
public interface DcsLockMapper extends BaseMapper<DcsResourceLockDO,String>{


    void deleteExpiredLocksByResourceId(String resourceId);

    void deleteLockByBizId(String lockBizId);

    int updateExpiredOnTime(String uniqueBizId);

    DcsResourceLockDO findLockByBizId(String uniqueBizId);

    List<DcsResourceLockDO> findOthersLock(String lockBizId,String resourceId);

    List<DcsResourceLockDO> findLocksByResourceId(String resourceId);

    DcsResourceLockDO findLivedLockByBizIdForUpdate(String lockBizId);

    List<DcsResourceLockDO> findLivedLocksByResourceId(String resourceId);
}
