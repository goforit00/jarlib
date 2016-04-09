package com.goforit.common.service.lock.manager.impl;

import com.goforit.common.service.lock.manager.DcsResourceManager;
import com.goforit.common.service.lock.model.DcsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by junqingfjq on 16/4/9.
 */
@Service
public class DcsResourceManagerImpl implements DcsResourceManager {

    @Autowired
    private DcsResourceManager dcsResourceManager;

    @Override
    public DcsResource create(DcsResource dcsResource) {
        return null;
    }

    @Override
    public DcsResource update(DcsResource dcsResource) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public DcsResource get(String id) {
        return null;
    }
}
