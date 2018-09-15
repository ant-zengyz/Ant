package com.example.ant.rest.data.system.user.service;

import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-14
 * Time: 22:39
 */
@Component
public class SystemPermissionRestHystric implements  SystemPermissionRest{

    @Override
    public ResponseModel findPermissionListBySystemRoleId(String systemRoleId) {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseModel findPermissionListToTree() {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }

    @Override
    public ResponseLayuiTableModel findSystemPermissionListByParams(Integer pageSize, Integer pageNum, String params) {
        return null;
    }

    @Override
    public ResponseModel findSystemPermissionListNotPage() {
        return ResponseModel.error("服务未启动，请联系开发人员");
    }
}
