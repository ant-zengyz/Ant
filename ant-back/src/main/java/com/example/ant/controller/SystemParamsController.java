package com.example.ant.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.annotation.ControllerLog;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemParams;
import com.example.ant.rest.data.system.user.service.SystemParamsRest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：后台管理操作类
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 21:27
 */
@Controller
@RequestMapping("/system/params")
public class SystemParamsController {

    @Autowired
    private SystemParamsRest systemParamsRest;

    /**
     * 参数首页
     *
     * @return
     */
    @ControllerLog(description = "参数配置列表页")
    @GetMapping("/params-index.html")
    public String systemParamsIndex() {
        return "system/params-index";
    }

    /**
     * 参数列表数据
     * @param limit
     * @param page
     * @param key
     * @return
     */
    @ControllerLog(description = "获取参数列表数据")
    @ResponseBody
    @GetMapping("/params-list.json")
    public ResponseLayuiTableModel systemParamsList(Integer limit, Integer page,String key) {
        //查询参数
        Map pm = new HashMap();
        pm.put("key", StringUtils.isEmpty(key) ? null : "%" + key + "%");
        ResponseLayuiTableModel systemUserModelList = systemParamsRest.findSystemParamsListByParams(null == limit ? 1 : limit, null == page ? 10 : page, JSON.toJSONString(pm));
        return systemUserModelList;
    }

    /**
     * 修改参数
     * @param systemParams
     * @return
     */
    @ControllerLog(description = "修改参数")
    @ResponseBody
    @PostMapping("/params-update.json")
    public ResponseModel systemParamsUpdate(SystemParams systemParams) {
        return systemParamsRest.updateSystemParams(JSON.toJSONString(systemParams));
    }

}
