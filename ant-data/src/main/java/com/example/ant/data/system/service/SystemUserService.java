package com.example.ant.data.system.service;

import com.example.ant.common.bean.ResponseModel;
import com.example.ant.common.tools.DateTools;
import com.example.ant.data.system.dao.SystemUserMapper;
import com.example.ant.data.system.entity.SystemUser;
import com.example.ant.data.system.model.SystemUserModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
public class SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 根据账号查询信息
     * @param account
     * @return
     */
    public SystemUserModel findByAccount(String account){
        SystemUserModel systemUserModel = systemUserMapper.selectSystemUserCorrelationByAccount(account);
        return systemUserModel;
    }

    /**
     * 查询LIST数据，分页信息
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    public PageInfo<SystemUserModel> findPageList(Integer pageNum, Integer pageSize, Map params){
        PageHelper.startPage(pageNum,pageSize);
        List<SystemUserModel> systemUsers = systemUserMapper.selectListCorrelationByParams(params);
        for (SystemUserModel systemUserModel:systemUsers) {
            systemUserModel.setCreateTimeStr(DateTools.parseDateToStr(systemUserModel.getCreateTime(),DateTools.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI));
        }
        PageInfo<SystemUserModel> systemUsersPageInfo=new PageInfo<SystemUserModel>(systemUsers);
        return systemUsersPageInfo;
    }

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    public SystemUser findOneByID(String id){
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        return systemUser;
    }

    /**
     * 根据对象进行修改
     * @param systemUser
     * @return
     */
    public ResponseModel updateSystemUserByID(SystemUser systemUser) throws Exception{
        int count = systemUserMapper.updateByPrimaryKey(systemUser);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("数据库执行修改失败");
        }
    }

    /**
     * 根据ID删除对象
     * @param id
     * @return
     */
    public ResponseModel deleteSystemUserByID(String id) throws Exception{
        //查询是否是管理员
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        if (systemUser.getAccount().equals("admin")){
            return ResponseModel.error("admin用户不能删除");
        }
        int count = systemUserMapper.deleteByPrimaryKey(id);
        //删除完用户，将用户依赖的角色也删除
        int deleteCount = systemUserMapper.deleteUserRole(id);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("数据库执行修改失败");
        }
    }

    /**
     * 添加用户
     * @param systemUser
     * @return
     */
    public ResponseModel createSystemUser(SystemUser systemUser) throws Exception{
        int count = systemUserMapper.insert(systemUser);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("数据库执行修改失败");
        }
    }

    /**
     * 配置用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    public ResponseModel setupUserRole(String userId,String roleIds) throws Exception {
        String[] roleIdSplit = roleIds.split(",");
        systemUserMapper.deleteUserRole(userId);
        Map params=new HashMap();
        params.put("systemUserId",userId);
        for (String roleId:roleIdSplit) {
            params.put("systemRoleId",roleId );
            systemUserMapper.createUserRole(params);
        }
        return ResponseModel.success();
    }

}
