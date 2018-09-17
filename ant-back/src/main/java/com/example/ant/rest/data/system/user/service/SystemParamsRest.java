package com.example.ant.rest.data.system.user.service;

import com.example.ant.annotation.RestLog;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：调用SYSTEM数据中心
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 8:57
 */
@FeignClient(value = "ANT-DATA" ,fallback=SystemParamsRestHystric.class)
public interface SystemParamsRest {

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @RestLog(description = "参数-查询分页数据")
    @RequestMapping(method = RequestMethod.GET,value = "/system/params/findSystemParamsListByParams.json")
    ResponseLayuiTableModel findSystemParamsListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params );

    /**
     * 修改参数
     * @param params
     * @return
     */
    @RestLog(description = "参数-修改参数")
    @RequestMapping(method = RequestMethod.POST,value = "/system/params/updateSystemParamsByID.json")
    ResponseModel updateSystemParams(@RequestParam("params") String params);

    /**
     * 根据KEY进行查询
     * @param params
     * @return
     */
    @RestLog(description = "参数-根据KEY进行查询")
    @RequestMapping(method = RequestMethod.GET,value = "/system/params/findSystemParamsByKEY.json")
    ResponseModel findSystemParamsByKEY(@RequestParam("params") String params );


}
