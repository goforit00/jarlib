package com.goforit.common.service.lock.manager.impl;

import com.goforit.common.service.lock.mapper.DOS.SequenceDO;
import com.goforit.common.service.lock.mapper.SequenceMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.goforit.common.service.lock.manager.SequenceManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Created by junqingfjq on 16/4/8.
 */

@Service
public class SequenceManagerImpl implements SequenceManager {

    private static final Logger LOGGER= LoggerFactory.getLogger(SequenceManagerImpl.class);

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private DataSource dataSource;

    @Override
    public String generate(TableName tableName) {
        return generate(tableName,Strategy.DEFAULT);
    }

    @Override
    public String generate(TableName tableName, Strategy strategy) {
        SequenceDO sequenceDO=new SequenceDO(tableName.name());
        sequenceMapper.insert(sequenceDO);

        return fulfillElement(Long.toString(sequenceDO.getId()), strategy);

    }


    public Date generateSystemTime() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Timestamp systemTime = null;
        try {
            conn = DataSourceUtils.doGetConnection(dataSource);
            pst = conn.prepareStatement("select CURRENT_TIMESTAMP() ");
            rs = pst.executeQuery();
            while (rs.next()) {
                systemTime = rs.getTimestamp(1);
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR ## get UTC_TIMESTAMP from mysql db has an error",
                    e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                DataSourceUtils.doReleaseConnection(conn, dataSource);
            } catch (Exception e) {
                LOGGER.error("ERROR ## close the jdbc resource has an error", e);
            }
        }
        if (systemTime != null) {
            return new Date(systemTime.getTime());
        }
        return null;
    }

    protected String fulfillElement(String object,Strategy strategy){
        if(null==strategy){
            return object;
        }

        return StringUtils.leftPad(object.toString(),strategy.getSize(),strategy.getPadString());
    }

}
