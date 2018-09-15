package com.example.ant.data.system.service;

import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.dao.SystemParamsMapper;
import com.example.ant.data.system.entity.SystemParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 描述：管理员表操作类
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 10:42
 */
@Service
@Transactional(transactionManager = "systemTransactionManager", rollbackFor = {Exception.class, RuntimeException.class})
public class SystemParamsService {

    @Autowired
    private SystemParamsMapper systemParamsMapper;


    /**
     * 查询LIST数据，分页信息
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    public PageInfo<SystemParams> findPageList(Integer pageNum, Integer pageSize, Map params){
        PageHelper.startPage(pageNum,pageSize);
        List<SystemParams> systemParams = systemParamsMapper.selectListByParams(params);
        PageInfo<SystemParams> systemUsersPageInfo=new PageInfo<SystemParams>(systemParams);
        return systemUsersPageInfo;
    }

    /**
     * 修改数据
     * @param systemParams
     * @return
     */
    public ResponseModel updateSystemParams(SystemParams systemParams){
        int count = systemParamsMapper.updateByPrimaryKey(systemParams);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("修改失败，请检查数据的准确性");
        }
    }

    /**
     * 根据KEY查询参数
     * @param key
     * @return
     */
    public ResponseModel findOneByKEY(String key){
        SystemParams systemParams = systemParamsMapper.selectOneByKEY(key);
        return ResponseModel.success(systemParams);
    }


}
