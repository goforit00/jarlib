package com.goforit.common.service.lock.mapper.DOS;

/**
 * Created by junqingfjq on 16/4/5.
 */
public class SequenceDO {

    private String tableName;

    private long id;

    public SequenceDO(String tableName){
        this.tableName=tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SequenceDO{" +
                "tableName='" + tableName + '\'' +
                ", id=" + id +
                '}';
    }
}
