package com.example.ant.rest.data.system.user.service;

import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.rest.RestContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：调用SYSTEM数据中心
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 8:57
 */
@FeignClient(value = "ANT-DATA" ,fallback=SystemUserRestHystric.class)
public interface SystemUserRest {

    /**
     * 根据账号查询用户-关联角色
     * @param account
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/user/findSystemUserModelByAccount.json")
    ResponseModel findSystemUserModelByAccount(@RequestParam("account") String account);

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/user/findSystemUserModelListByParams.json")
    ResponseLayuiTableModel findSystemUserModelListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params );

    /**
     * 根据ID进行查询
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/user/findSystemUserByID.json")
    ResponseModel findSystemUserByID(@RequestParam("id") String id);

    /**
     * 修改
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/user/updateSystemUserByID.json")
    ResponseModel updateSystemUserByID(@RequestParam("params") String params);

    /**
     * 删除
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/user/deleteSystemUserByID.json")
    ResponseModel deleteSystemUserByID(@RequestParam("params") String params);

    /**
     * 创建
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/user/createSystemUser.json")
    ResponseModel createSystemUser(@RequestParam("params") String params);

    /**
     * 配置角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/user/setupUserRole.json")
    ResponseModel setupUserRole(@RequestParam("userId") String userId,@RequestParam("roleIds") String roleIds );
}
