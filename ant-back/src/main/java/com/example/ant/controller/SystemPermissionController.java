package com.example.ant.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemPermission;
import com.example.ant.rest.data.system.user.service.SystemPermissionRest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-12
 * Time: 22:07
 */
@Controller
@RequestMapping("/system/permission")
public class SystemPermissionController {

    @Autowired
    private SystemPermissionRest systemPermissionRest;

    /**
     * 权限首页
     *
     * @return
     */
    @GetMapping("/permission-index.html")
    public String systemPermissionIndex(Model model) {
        ResponseModel responseModel = systemPermissionRest.findSystemPermissionListNotPage();
        if (responseModel.isStatus()){
            if (null!=responseModel.getData()){
                List<SystemPermission> systemPermissions = JSON.parseArray(responseModel.getData().toString(), SystemPermission.class);
                model.addAttribute("systemPermissionList",systemPermissions);
            }
        }
        return "system/permission-index";
    }

}
