package com.goforit.common.service.lock.manager;

import java.util.Date;

/**
 * Created by junqingfjq on 16/4/8.
 */
public interface SequenceManager {

    /**
     * 生成策略
     */
    public enum Strategy{
        DEFAULT(10,"0"),
        FLOW(16,"0");

        private final int size;

        private final String padString;

        private Strategy(int size,String padString){
            this.size=size;
            this.padString=padString;
        }

        public int getSize(){
            return size;
        }

        public String getPadString(){
            return padString;
        }
    }

    /**
     * 表名
     */
    public enum TableName{
        DCS_RESOURCE,

        DCS_RESOURCE_LOCK;
    }

    String generate(TableName tableName);

    String generate(TableName tableName,Strategy strategy);

    public Date generateSystemTime();

}
