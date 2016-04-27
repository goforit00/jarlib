package com.goforit.common.service.lock.mapper.DOS;

import java.util.Date;

/**
 * Created by junqingfjq on 16/4/5.
 */
public class DcsResourceDO {

    private String id;

    private String uniqueBizId;

    private String name;

    private String type;

    private int maxLock;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxLock() {
        return maxLock;
    }

    public void setMaxLock(int maxLock) {
        this.maxLock = maxLock;
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
