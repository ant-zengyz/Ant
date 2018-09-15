package com.example.ant.rest.data.system.user.service;

import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-14
 * Time: 22:40
 */
@Component
public class SystemRoleRestHystric implements SystemRoleRest{
    @Override
    public ResponseLayuiTableModel findSystemRoleListByParams(Integer pageSize, Integer pageNum, String params) {
        return null;
    }

    @Override
    public ResponseModel findSystemRoleList() {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel findSystemRoleByID(String id) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel updateSystemRoleByID(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel createSystemRole(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel deleteSystemRoleByID(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel setupRolePermission(String roleId, String permissionIds) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }
}
