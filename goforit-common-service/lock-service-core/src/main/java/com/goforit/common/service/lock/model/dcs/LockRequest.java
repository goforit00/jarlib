package com.goforit.common.service.lock.model.dcs;

/**
 * Created by junqingfjq on 16/4/9.
 */
public class LockRequest {

    /**
     * 创建新锁的时候为非必填
     * 锁的唯一标识
     */
    private String uniqueBizId;

    /**
     * 必填
     * 要加锁的资源名称,
     * 和resourceType构成资源的唯一标识
     */
    private String resourceName;

    /**
     * 必填
     * 加锁类型,
     * 和resourceName构成资源的唯一标识
     */
    private String resourceType;

    /**
     * 非必填（默认为排他锁EXCLUSIVE）
     * 锁的类型
     */
    private LockType lockType;

    /**
     * 非必填(默认100)
     * 当锁的类型为SHARED,资源所允许最大的加锁个数
     */
    private int resourceMaxLock;

    /**
     * 非必填
     * 锁的名称,默认名称为（{uniqueBizId}）
     */
    private String lockName;

    /**
     * 必填
     * 锁的持有者（调用方的自身标示）
     */
    private String owner;

    /**
     * 非必填
     * 锁的持续时间(TTL 以秒为单位)
     */
    private int duration;

    public String getUniqueBizId() {
        return uniqueBizId;
    }

    public void setUniqueBizId(String uniqueBizId) {
        this.uniqueBizId = uniqueBizId;
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

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }

    public int getResourceMaxLock() {
        return resourceMaxLock;
    }

    public void setResourceMaxLock(int resourceMaxLock) {
        this.resourceMaxLock = resourceMaxLock;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
