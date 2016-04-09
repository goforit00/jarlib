package com.goforit.common.service.lock.model.dcs;

/**
 * Created by junqingfjq on 16/4/9.
 */
public class LockQuery {

    private String lockName;

    private String lockType;

    private String resourceName;

    private String resourceType;

    private String owner;

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
