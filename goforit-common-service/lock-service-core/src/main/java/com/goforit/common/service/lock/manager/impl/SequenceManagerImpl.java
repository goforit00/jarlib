package com.goforit.common.service.lock.manager.impl;

import com.goforit.common.service.lock.mapper.DOS.SequenceDO;
import com.goforit.common.service.lock.mapper.SequenceMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goforit.common.service.lock.manager.SequenceManager;



/**
 * Created by junqingfjq on 16/4/8.
 */

@Service
public class SequenceManagerImpl implements SequenceManager {

    private static final Logger LOGGER= LoggerFactory.getLogger(SequenceManagerImpl.class);

    @Autowired
    private SequenceMapper sequenceMapper;

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

    protected String fulfillElement(String object,Strategy strategy){
        if(null==strategy){
            return object;
        }

        return StringUtils.leftPad(object.toString(),strategy.getSize(),strategy.getPadString());
    }

}
