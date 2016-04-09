package com.goforit.common.service.lock.model;

import java.util.Date;

/**
 * Created by junqingfjq on 16/4/9.
 */
public class DcsResourceLock {

    private String id;

    private String uniqueBizId;

    private String resourceId;

    private String lockName;

    private String lockType;

    private String owner;

    private Date expiredDate;

    private Date utcModified;

    private Date utcCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueBizId() {
        return uniqueBizId;
    }

    public void setUniqueBizId(String uniqueBizId) {
        this.uniqueBizId = uniqueBizId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getUtcModified() {
        return utcModified;
    }

    public void setUtcModified(Date utcModified) {
        this.utcModified = utcModified;
    }

    public Date getUtcCreated() {
        return utcCreated;
    }

    public void setUtcCreated(Date utcCreated) {
        this.utcCreated = utcCreated;
    }
}
