package com.example.ant.data.system.service;

import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.context.SystemPermissionContext;
import com.example.ant.data.system.dao.SystemPermissionMapper;
import com.example.ant.data.system.entity.SystemPermission;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.model.SystemPermissionModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/5 上午 9:21
 */
@Service
public class SystemPermissionService {

    @Autowired
    private SystemPermissionMapper systemPermissionMapper;

    /**
     * 添加权限
     *
     * @param systemPermission
     * @return
     */
    public boolean add(SystemPermission systemPermission) {
        int count = systemPermissionMapper.insert(systemPermission);
        if (count == 1) {
            return true;
        }
        return false;
    }

    /**
     * 根据角色ID查询该角色的权限
     *
     * @param systemRoleId
     * @return
     */
    public List<SystemPermission> findPermissionListBySystemRoleId(String systemRoleId) {
        return systemPermissionMapper.selectListBySystemRoleId(systemRoleId);
    }

    /**
     * 查询菜单树
     * @return
     */
    public List<SystemPermissionModel> findMenuTree() {
        Map params = new HashMap();
        params.put("isType", SystemPermissionContext.MENU.getStrCode());
        params.put("state", SystemPermissionContext.Y.getStrCode());
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectListByParams(params);
        List<SystemPermissionModel> systemPermissionModels = new ArrayList<>();
        for (SystemPermission systemPermissionIsTypeMenu : systemPermissions) {
            SystemPermissionModel menu = setSystemPermissionModel(systemPermissionIsTypeMenu);
            List<SystemPermissionModel> systemPermissionModelPage = new ArrayList<>();
            params.put("pid", systemPermissionIsTypeMenu.getId());
            params.put("isType", SystemPermissionContext.PAGE.getStrCode());
            List<SystemPermission> systemPermissionPages = systemPermissionMapper.selectListByParams(params);
            for (SystemPermission systemPermissionIsTypePage : systemPermissionPages) {
                SystemPermissionModel page = setSystemPermissionModel(systemPermissionIsTypePage);
                menu.getSystemPermissionModelList().add(page);
            }
            systemPermissionModels.add(menu);
        }
        return systemPermissionModels;
    }


    /**
     * 查询LIST数据，分页信息
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    public PageInfo<SystemPermission> findPageList(Integer pageNum, Integer pageSize, Map params){
        PageHelper.startPage(pageNum,pageSize);
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectListByParams(params);
        PageInfo<SystemPermission> systemPermissionPageInfo=new PageInfo<SystemPermission>(systemPermissions);
        return systemPermissionPageInfo;
    }

    public ResponseModel findPageList(){
        List<SystemPermission> resultList=new ArrayList<>();
        Map param=new HashMap();
        param.put("pid","0");
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectListByParams(param);
        for (SystemPermission trmpMenu:systemPermissions) {
            resultList.add(trmpMenu);
            param.put("pid",trmpMenu.getId());
            List<SystemPermission> pageList = systemPermissionMapper.selectListByParams(param);
            resultList.addAll(pageList);
        }
        return ResponseModel.success(resultList);
    }

    private SystemPermissionModel setSystemPermissionModel(SystemPermission systemPermission) {
        SystemPermissionModel model = new SystemPermissionModel();
        model.setId(systemPermission.getId());
        model.setAddress(systemPermission.getAddress());
        model.setCreateTime(systemPermission.getCreateTime());
        model.setCreateUserId(systemPermission.getCreateUserId());
        model.setIcon(systemPermission.getIcon());
        model.setIsCode(systemPermission.getIsCode());
        model.setIsType(systemPermission.getIsType());
        model.setName(systemPermission.getName());
        model.setSort(systemPermission.getSort());
        model.setState(systemPermission.getState());
        model.setPid(systemPermission.getPid());
        model.setSystemPermissionModelList(new ArrayList<SystemPermissionModel>());
        return model;
    }
}
