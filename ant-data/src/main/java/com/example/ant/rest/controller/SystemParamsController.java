package com.example.ant.rest.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemParams;
import com.example.ant.data.system.service.SystemParamsService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 描述：后台-管理员相关接口
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 21:17
 */
@RestController
@RequestMapping("/system/params")
@Slf4j
public class SystemParamsController {

    @Autowired
    private SystemParamsService systemParamsService;


    /**
     * 分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @GetMapping("/findSystemParamsListByParams.json")
    public ResponseLayuiTableModel findSystemParamsListByParams(@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum") Integer pageNum,@RequestParam("params") String params ){
        ResponseLayuiTableModel responseLayuiTableModel=new ResponseLayuiTableModel();
        try {
            PageInfo<SystemParams> pageList=new PageInfo<>();
            if (StringUtils.isEmpty(params)){
                pageList = systemParamsService.findPageList(pageNum, pageSize, null);
            }else{
                pageList = systemParamsService.findPageList(pageNum, pageSize, JSON.parseObject(params, Map.class));
            }

            responseLayuiTableModel.setCode(0);//成功是0
            responseLayuiTableModel.setData(pageList.getList());
            responseLayuiTableModel.setCount(pageList.getTotal());
            return responseLayuiTableModel;
        }catch (Exception e){
            responseLayuiTableModel.setCode(1);//成功是0
            responseLayuiTableModel.setMsg("请求异常，可能服务器网络波动，请联系开发人员查看原因");
            return responseLayuiTableModel;
        }
    }

    /**
     * 修改
     * @param params
     * @return
     */
    @PostMapping("/updateSystemParamsByID.json")
    public ResponseModel updateSystemParams(String params){
        SystemParams systemParams = JSON.parseObject(params, SystemParams.class);
        return systemParamsService.updateSystemParams(systemParams);
    }

    /**
     * 根据KEY进行查询
     * @param params
     * @return
     */
    @GetMapping("/findSystemParamsByKEY.json")
    public ResponseModel findSystemParamsByKEY(@RequestParam("params") String params ){
        ResponseModel responseModel = systemParamsService.findOneByKEY(params);
        return responseModel;
    }
}
