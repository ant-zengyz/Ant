package com.example.ant.data.system.service;

import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.context.SystemRoleContext;
import com.example.ant.data.system.dao.SystemRoleMapper;
import com.example.ant.data.system.entity.SystemRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-04
 * Time: 23:03
 */
@Service
@Transactional(transactionManager = "systemTransactionManager", rollbackFor = {Exception.class, RuntimeException.class})
public class SystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    /**
     * 查询LIST数据，分页信息
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    public PageInfo<SystemRole> findPageList(Integer pageNum, Integer pageSize, Map params){
        PageHelper.startPage(pageNum,pageSize);
        List<SystemRole> systemRoles = systemRoleMapper.selectListByParams(params);
        PageInfo<SystemRole> systemRolePageInfo=new PageInfo<SystemRole>(systemRoles);
        return systemRolePageInfo;
    }

    /**
     * 查询LIST数据
     * @return
     */
    public List<SystemRole> findList(){
        Map params = new HashMap<>();
        params.put("state", SystemRoleContext.Y.getStrCode());
        List<SystemRole> systemRoles = systemRoleMapper.selectListByParams(params);
        return systemRoles;
    }

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    public SystemRole findOneByID(String id){
        SystemRole systemRole = systemRoleMapper.selectByPrimaryKey(id);
        return systemRole;
    }


    /**
     * 根据对象进行修改
     * @param systemRole
     * @return
     */
    public ResponseModel updateSystemRoleByID(SystemRole systemRole) throws Exception{
        int count = systemRoleMapper.updateByPrimaryKey(systemRole);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("数据库执行修改失败");
        }
    }


    /**
     * 添加角色
     * @param systemRole
     * @return
     */
    public ResponseModel createSystemRole(SystemRole systemRole) throws Exception{
        int count = systemRoleMapper.insert(systemRole);
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
    public ResponseModel deleteSystemRoleByID(String id) throws Exception{
        //检查是否为admin用户
        SystemRole systemRole = systemRoleMapper.selectByPrimaryKey(id);
        if (systemRole.getName().equals("ADMIN")){
            return ResponseModel.error("超级管理员角色不能删除");
        }

        //删除角色下依赖的权限
        int count = systemRoleMapper.deleteByPrimaryKey(id);
        int deleteCount = systemRoleMapper.deleteRolePermission(id);
        if (count==1){
            return ResponseModel.success();
        }else{
            return ResponseModel.error("数据库执行修改失败");
        }
    }

    public ResponseModel setupRolePermission(String roleId,String permissionIds) throws Exception{
        systemRoleMapper.deleteRolePermission(roleId);
        if (StringUtils.isNotEmpty(permissionIds)){
            String[] permissionId=permissionIds.substring(0,permissionIds.length()-1).split(",");
            Map params=new HashMap();
            params.put("systemRoleId",roleId);
            for (String pid:permissionId) {
                params.put("systemPermissionId",pid);
                systemRoleMapper.createRolePermission(params);
            }
        }
        return ResponseModel.success();
    }


}
