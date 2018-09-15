package com.example.ant.rest.data.system.user.service;

import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-14
 * Time: 22:42
 */
@Component
public class SystemUserRestHystric implements SystemUserRest{

    @Override
    public ResponseModel findSystemUserModelByAccount(String account) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseLayuiTableModel findSystemUserModelListByParams(Integer pageSize, Integer pageNum, String params) {
        return null;
    }

    @Override
    public ResponseModel findSystemUserByID(String id) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel updateSystemUserByID(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel deleteSystemUserByID(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel createSystemUser(String params) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel setupUserRole(String userId, String roleIds) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }
}
