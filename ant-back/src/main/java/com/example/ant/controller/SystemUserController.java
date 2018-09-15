package com.example.ant.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.common.tools.UUIDTools;
import com.example.ant.context.SessionContext;
import com.example.ant.data.system.context.SystemUserContext;
import com.example.ant.data.system.entity.SystemParams;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.entity.SystemUser;
import com.example.ant.data.system.model.SystemUserModel;
import com.example.ant.data.system.model.SystemUserRoleModel;
import com.example.ant.rest.data.system.user.service.SystemParamsRest;
import com.example.ant.rest.data.system.user.service.SystemRoleRest;
import com.example.ant.rest.data.system.user.service.SystemUserRest;
import org.apache.commons.codec.digest.DigestUtils;
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
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    private SystemUserRest systemUserRest;

    @Autowired
    private SystemParamsRest systemParamsRest;

    @Autowired
    private SystemRoleRest systemRoleRest;

    /**
     * 管理员首页
     *
     * @return
     */
    @GetMapping("/user-index.html")
    public String systemUserIndex() {
        return "system/user-index";
    }

    @ResponseBody
    @GetMapping("/user-list.json")
    public ResponseLayuiTableModel systemUserList(Integer limit, Integer page, String startDate, String endDate, String account) {

        //查询参数
        Map pm = new HashMap();
        pm.put("startDate", StringUtils.isEmpty(startDate) ? null : startDate);
        pm.put("endDate", StringUtils.isEmpty(endDate) ? null : endDate);
        pm.put("account", StringUtils.isEmpty(account) ? null : "%" + account + "%");

        ResponseLayuiTableModel systemUserModelList = systemUserRest.findSystemUserModelListByParams(null == limit ? 1 : limit, null == page ? 10 : page, JSON.toJSONString(pm));
        return systemUserModelList;
    }

    /**
     * 创建管理员
     * @return
     */
    @GetMapping("/user-create.html")
    public String systemUserCreate() {
        return "system/user-add";
    }

    /**
     * 创建管理员提交
     * @param systemUser
     * @return
     */
    @ResponseBody
    @PostMapping("/user-create.json")
    public ResponseModel systemUserCreateSub(SystemUser systemUser) {
        SystemUserModel systemUserModel = (SystemUserModel) SecurityUtils.getSubject().getSession().getAttribute(SessionContext.LOGINUSER);
        if (null == systemUserModel) {
            return ResponseModel.error("请先登陆");
        }
        systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
        systemUser.setCreateTime(new Date());
        systemUser.setCreateUserId(systemUserModel.getId());
        systemUser.setId(UUIDTools.getUUID32());
        return systemUserRest.createSystemUser(JSON.toJSONString(systemUser));
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/user-recPassword.json")
    public ResponseModel ststemUserRecPassword(String id) {
        ResponseModel systemParamsRsponseModel = systemParamsRest.findSystemParamsByKEY("RESET_PASSWORD");
        if (!systemParamsRsponseModel.isStatus()) {
            return ResponseModel.error("网络波动，参数查询错误");
        } else {
            if (StringUtils.isEmpty(systemParamsRsponseModel.getData().toString())) {
                return ResponseModel.error("缺少必要参数，操作失败");
            } else {
                SystemParams systemParams = JSON.parseObject(systemParamsRsponseModel.getData().toString(), SystemParams.class);
                ResponseModel systemUserResponseModel = systemUserRest.findSystemUserByID(id);
                if (!systemUserResponseModel.isStatus()) {
                    return ResponseModel.error("查询数据失败，操作失败");
                } else {
                    if (StringUtils.isEmpty(systemUserResponseModel.getData().toString())) {
                        return ResponseModel.error("没有查询到该条数据，操作失败");
                    } else {
                        SystemUser systemUser = JSON.parseObject(systemUserResponseModel.getData().toString(), SystemUser.class);
                        systemUser.setPassword(DigestUtils.md5Hex(systemParams.getValue().trim()));
                        return systemUserRest.updateSystemUserByID(JSON.toJSONString(systemUser));
                    }
                }
            }
        }
    }

    /**
     * 修改管理员状态
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/user-updateState.json")
    public ResponseModel ststemUserUpdateState(String id) {
        ResponseModel systemUserResponseModel = systemUserRest.findSystemUserByID(id);
        if (!systemUserResponseModel.isStatus()) {
            return ResponseModel.error("查询数据失败，操作失败");
        } else {
            if (StringUtils.isEmpty(systemUserResponseModel.getData().toString())) {
                return ResponseModel.error("没有查询到该条数据，操作失败");
            } else {
                SystemUser systemUser = JSON.parseObject(systemUserResponseModel.getData().toString(), SystemUser.class);
                if (systemUser.getState().equals(SystemUserContext.Y.getStrCode())) {
                    systemUser.setState(SystemUserContext.N.getStrCode());
                } else {
                    systemUser.setState(SystemUserContext.Y.getStrCode());
                }
                return systemUserRest.updateSystemUserByID(JSON.toJSONString(systemUser));
            }
        }
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/user-delete.json")
    public ResponseModel ststemUserDelete(String id) {
        ResponseModel responseModel = systemUserRest.deleteSystemUserByID(id);
        return responseModel;
    }

    /**
     * 配置管理员角色提交
     * @param userId
     * @param roleIds
     * @return
     */
    @ResponseBody
    @PostMapping("/user-setupRole.json")
    public ResponseModel systemUserSetupRole(String userId,String roleIds) {
        return systemUserRest.setupUserRole(userId,roleIds);
    }

    /**
     * 配置管理员角色
     * @param model
     * @param account
     * @return
     */
    @GetMapping("/user-setupRole.html")
    public String systemUserSetupRole(Model model,String account) {
        ResponseModel responseModelSystemUser = systemUserRest.findSystemUserModelByAccount(account);
        ResponseModel responseModelSystemRoleList = systemRoleRest.findSystemRoleList();
        if (responseModelSystemUser.isStatus()&&responseModelSystemRoleList.isStatus()){
            SystemUserModel systemUserModel = JSON.parseObject(responseModelSystemUser.getData().toString(), SystemUserModel.class);
            List<SystemUserRoleModel> systemUserRoleModels=new ArrayList<>();
            if (null!=responseModelSystemRoleList.getData()){
                List<SystemRole> systemRoles = JSON.parseArray(responseModelSystemRoleList.getData().toString(), SystemRole.class);
                for (SystemRole systemRoleTemp:systemRoles) {
                    SystemUserRoleModel systemUserRoleModel=new SystemUserRoleModel();
                    systemUserRoleModel.setUserId(systemUserModel.getId());
                    systemUserRoleModel.setRoleName(systemRoleTemp.getName());
                    systemUserRoleModel.setRoleId(systemRoleTemp.getId());
                    systemUserRoleModel.setCheckbox("");
                    for (SystemRole systemUserRoleTemp:systemUserModel.getSystemRoleList()) {
                        if (systemUserRoleTemp.getId().equals(systemRoleTemp.getId())){
                            systemUserRoleModel.setCheckbox("checked");
                        }
                    }
                    systemUserRoleModels.add(systemUserRoleModel);
                }
                model.addAttribute("systemRoleList",systemUserRoleModels);
                model.addAttribute("userId",systemUserModel.getId());

            }else{
                model.addAttribute("systemRoleList",systemUserRoleModels);
            }
        }else{
            return "error";
        }
        return "system/user-setup";
    }

}
