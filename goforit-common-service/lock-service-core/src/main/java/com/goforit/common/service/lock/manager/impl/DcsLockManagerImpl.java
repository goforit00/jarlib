package com.goforit.common.service.lock.manager.impl;

import com.goforit.common.service.lock.manager.DcsLockManager;
import com.goforit.common.service.lock.mapper.DcsLockMapper;
import com.goforit.common.service.lock.model.DcsResourceLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by junqingfjq on 16/4/9.
 */
@Service
public class DcsLockManagerImpl implements DcsLockManager {

    @Autowired
    private DcsLockMapper dcsLockMapper;

    @Override
    public DcsResourceLock create(DcsResourceLock lock) {
        return null;
    }

    @Override
    public DcsResourceLock update(DcsResourceLock dcsResourceLock) {
        return null;
    }

    @Override
    public void delete(DcsResourceLock lock) {

    }

    @Override
    public DcsResourceLock get(String id) {
        return null;
    }
}
