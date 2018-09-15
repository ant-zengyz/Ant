package com.example.ant.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.common.tools.UUIDTools;
import com.example.ant.context.SessionContext;
import com.example.ant.data.system.context.SystemRoleContext;
import com.example.ant.data.system.context.SystemUserContext;
import com.example.ant.data.system.entity.SystemPermission;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.model.SystemPermissionModel;
import com.example.ant.data.system.model.SystemRolePermissionModel;
import com.example.ant.data.system.model.SystemUserModel;
import com.example.ant.rest.data.system.user.service.SystemPermissionRest;
import com.example.ant.rest.data.system.user.service.SystemRoleRest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 描述：后台管理操作类
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 21:27
 */
@Controller
@RequestMapping("/system/role")
public class SystemRoleController {

    @Autowired
    private SystemRoleRest systemRoleRest;

    @Autowired
    private SystemPermissionRest systemPermissionRest;

    /**
     * 角色首页
     *
     * @return
     */
    @GetMapping("/role-index.html")
    public String systemRoleIndex() {
        return "system/role-index";
    }

    /**
     * 角色列表
     * @param limit
     * @param page
     * @param isCode
     * @return
     */
    @ResponseBody
    @GetMapping("/role-list.json")
    public ResponseLayuiTableModel systemRoleList(Integer limit, Integer page, String isCode) {
        //查询参数
        Map pm = new HashMap();
        pm.put("isCode", StringUtils.isEmpty(isCode) ? null : "%" + isCode + "%");
        ResponseLayuiTableModel systemUserModelList = systemRoleRest.findSystemRoleListByParams(null == limit ? 1 : limit, null == page ? 10 : page, JSON.toJSONString(pm));
        return systemUserModelList;
    }

    /**
     * 修改角色状态
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/role-updateState.json")
    public ResponseModel ststemUserUpdateState(String id) {
        ResponseModel systemUserResponseModel = systemRoleRest.findSystemRoleByID(id);
        if (!systemUserResponseModel.isStatus()) {
            return ResponseModel.error("查询数据失败，操作失败");
        } else {
            if (StringUtils.isEmpty(systemUserResponseModel.getData().toString())) {
                return ResponseModel.error("没有查询到该条数据，操作失败");
            } else {
                SystemRole systemRole = JSON.parseObject(systemUserResponseModel.getData().toString(), SystemRole.class);
                if (systemRole.getState().equals(SystemUserContext.Y.getStrCode())){
                    systemRole.setState(SystemRoleContext.N.getStrCode());
                }else{
                    systemRole.setState(SystemRoleContext.Y.getStrCode());
                }
                return systemRoleRest.updateSystemRoleByID(JSON.toJSONString(systemRole));
            }
        }
    }

    /**
     * 创建角色
     * @return
     */
    @GetMapping("/role-create.html")
    public String systemUserCreate() {
        return "system/role-add";
    }

    /**
     * 创建角色提交
     * @param systemRole
     * @return
     */
    @ResponseBody
    @PostMapping("/role-create.json")
    public ResponseModel systemRoleCreateSub(SystemRole systemRole) {
        SystemUserModel systemUserModel= (SystemUserModel) SecurityUtils.getSubject().getSession().getAttribute(SessionContext.LOGINUSER);
        if (null==systemUserModel){
            return ResponseModel.error("请先登陆");
        }
        systemRole.setCreateTime(new Date());
        systemRole.setCreateUserId(systemUserModel.getId());
        systemRole.setId(UUIDTools.getUUID32());
        return systemRoleRest.createSystemRole(JSON.toJSONString(systemRole));
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/role-delete.json")
    public ResponseModel ststemRoleDelete(String id) {
        ResponseModel responseModel = systemRoleRest.deleteSystemRoleByID(id);
        return responseModel;
    }

    @GetMapping("/role-setupPermission.html")
    public String systemRoleSetupPermission(Model model, String roleId) {
        List<SystemRolePermissionModel> systemRolePermissionModels=new ArrayList<>();
        ResponseModel responseModel = systemPermissionRest.findPermissionListToTree();
        ResponseModel responseModelByRole = systemPermissionRest.findPermissionListBySystemRoleId(roleId);
       if (responseModelByRole.isStatus()){
           List<SystemPermission> systemPermissions = JSON.parseArray(responseModelByRole.getData().toString(), SystemPermission.class);
           if (responseModel.isStatus()){
               if (null!=responseModel.getData()){
                   List<SystemPermissionModel> systemPermissionModels = JSON.parseArray(responseModel.getData().toString(), SystemPermissionModel.class);
                   for (SystemPermissionModel menuTemp:systemPermissionModels) {
                       SystemRolePermissionModel systemRolePermissionModel = getSystemRolePermissionModel(roleId, systemPermissions, menuTemp);
                       systemRolePermissionModels.add(systemRolePermissionModel);
                       for (SystemPermissionModel pageTemp:menuTemp.getSystemPermissionModelList()) {
                           SystemRolePermissionModel systemRolePermissionPageModel = getSystemRolePermissionModel(roleId, systemPermissions, pageTemp);
                           systemRolePermissionModels.add(systemRolePermissionPageModel);
                       }
                   }
               }
           }
       }
       model.addAttribute("systemPermissionList",systemRolePermissionModels);
        model.addAttribute("roleId",roleId);
       return "system/role-setup";
    }

    @ResponseBody
    @PostMapping("/role-setupPermission.json")
    public ResponseModel systemRoleSetupPermission(String roleId,String ids) {
        return systemRoleRest.setupRolePermission(roleId,ids);
    }

    private SystemRolePermissionModel getSystemRolePermissionModel(String roleId, List<SystemPermission> systemPermissions, SystemPermissionModel menuTemp) {
        SystemRolePermissionModel systemRolePermissionModel=new SystemRolePermissionModel();
        systemRolePermissionModel.setId(menuTemp.getId());
        systemRolePermissionModel.setRoleId(roleId);
        systemRolePermissionModel.setPid(menuTemp.getPid());
        systemRolePermissionModel.setName(menuTemp.getName());
        systemRolePermissionModel.setIsCode(menuTemp.getIsCode());
        systemRolePermissionModel.setChecked(false);
        for (SystemPermission roleToPermission:systemPermissions) {
            if (menuTemp.getId().equals(roleToPermission.getId())){
                systemRolePermissionModel.setChecked(true);
            }
        }
        return systemRolePermissionModel;
    }
}
