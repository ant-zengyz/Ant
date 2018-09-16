package com.example.ant.rest.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemUser;
import com.example.ant.data.system.model.SystemUserModel;
import com.example.ant.data.system.service.SystemUserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：后台-管理员相关接口
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 21:17
 */
@RestController
@RequestMapping("/system/user")
@Slf4j
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 根据账号关联查询
     * @param account
     * @return
     */
    @GetMapping("/findSystemUserModelByAccount.json")
    public ResponseModel findSystemUserModelByAccount(@RequestParam("account") String account){
        SystemUserModel systemUserModel = systemUserService.findByAccount(account);
        return ResponseModel.success(systemUserModel);
    }

    /**
     * 分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @GetMapping("/findSystemUserModelListByParams.json")
    public ResponseLayuiTableModel findSystemUserModelListByParams(@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum") Integer pageNum,@RequestParam("params") String params ){
        ResponseLayuiTableModel responseLayuiTableModel=new ResponseLayuiTableModel();
        try {
            PageInfo<SystemUserModel> pageList=new PageInfo<>();
            if (StringUtils.isEmpty(params)){
                pageList = systemUserService.findPageList(pageNum, pageSize, null);
            }else{
                pageList = systemUserService.findPageList(pageNum, pageSize, JSON.parseObject(params, Map.class));
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
     * 主键查询
     * @param id
     * @return
     */
    @GetMapping("/findSystemUserByID.json")
    public ResponseModel findSystemUserByID(@RequestParam("id") String id){
        SystemUser systemUser = systemUserService.findOneByID(id);
        return ResponseModel.success(systemUser);
    }

    /**
     * 修改
     * @param params
     * @return
     */
    @PostMapping("/updateSystemUserByID.json")
    public ResponseModel updateSystemUserByID(@RequestParam("params") String params){
        SystemUser systemUser = JSON.parseObject(params, SystemUser.class);
        try {
            return systemUserService.updateSystemUserByID(systemUser);
        } catch (Exception e) {
            return ResponseModel.error("数据服务层异常，请联系开发人员");
        }
    }

    /**
     * 删除
     * @param params
     * @return
     */
    @PostMapping("/deleteSystemUserByID.json")
    public ResponseModel deleteSystemUserByID(@RequestParam("params") String params){
        try {
            return systemUserService.deleteSystemUserByID(params);
        } catch (Exception e) {
            return ResponseModel.error("数据服务层异常，请联系开发人员");
        }
    }

    /**
     * 创建用户
     * @param params
     * @return
     */
    @PostMapping("/createSystemUser.json")
    public ResponseModel createSystemUser(@RequestParam("params") String params){
        SystemUser systemUser = JSON.parseObject(params, SystemUser.class);
        try {
            return systemUserService.createSystemUser(systemUser);
        } catch (Exception e) {
            return ResponseModel.error("数据服务层异常，请联系开发人员");
        }
    }

    /**
     * 配置用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("/setupUserRole.json")
    ResponseModel setupUserRole(@RequestParam("userId") String userId,@RequestParam("roleIds") String roleIds ){
        try {
            return systemUserService.setupUserRole(userId,roleIds);
        } catch (Exception e) {
            return ResponseModel.error("数据服务层异常，请联系开发人员");
        }
    }

}
