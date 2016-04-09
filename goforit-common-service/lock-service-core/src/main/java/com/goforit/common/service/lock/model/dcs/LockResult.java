package com.goforit.common.service.lock.model.dcs;

import com.goforit.common.service.lock.model.DcsResourceLock;

import java.util.List;

/**
 * Created by junqingfjq on 16/4/9.
 */
public class LockResult {

    private DcsResourceLock ownedDcsResourceLock;

    private List<DcsResourceLock> othersDcsResourceLock;

    private String errMsg;

    public LockResult(){

    }

    public DcsResourceLock getOwnedDcsResourceLock() {
        return ownedDcsResourceLock;
    }

    public void setOwnedDcsResourceLock(DcsResourceLock ownedDcsResourceLock) {
        this.ownedDcsResourceLock = ownedDcsResourceLock;
    }

    public List<DcsResourceLock> getOthersDcsResourceLock() {
        return othersDcsResourceLock;
    }

    public void setOthersDcsResourceLock(List<DcsResourceLock> othersDcsResourceLock) {
        this.othersDcsResourceLock = othersDcsResourceLock;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
