package com.goforit.common.service.lock.model.dcs;

/**
 * Created by junqingfjq on 16/4/9.
 */
public enum LockType {

    SHARED("SHARED","lock the resource in share"),
    EXCLUSIVE("EXCLUSIVE","lock the resource in exclusive");

    private String code;

    private String description;

    private LockType(String code,String description){
        this.code=code;
        this.description=description;
    }

}
