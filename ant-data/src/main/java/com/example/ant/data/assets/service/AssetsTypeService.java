package com.example.ant.data.assets.service;

import com.example.ant.data.assets.dao.AssetsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：资产类型表操作类
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 11:42
 */
@Service
@Transactional(transactionManager = "assetsTransactionManager", rollbackFor = {Exception.class, RuntimeException.class})
public class AssetsTypeService {

    @Autowired
    private AssetsTypeMapper assetsTypeMapper;

}
