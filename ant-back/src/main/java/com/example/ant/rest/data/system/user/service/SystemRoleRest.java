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
@FeignClient(value = "ANT-DATA" ,fallback=SystemRoleRestHystric.class)
public interface SystemRoleRest {

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/role/findSystemRoleListByParams.json")
    ResponseLayuiTableModel findSystemRoleListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params);

    /**
     * 查询全部角色
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/role/findSystemRoleList.json")
    ResponseModel findSystemRoleList();

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/role/findSystemRoleByID.json")
    ResponseModel findSystemRoleByID(@RequestParam("id") String id);

    /**
     * 修改角色
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/role/updateSystemRoleByID.json")
    ResponseModel updateSystemRoleByID(@RequestParam("params") String params);

    /**
     * 创建角色
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/role/createSystemRole.json")
    ResponseModel createSystemRole(@RequestParam("params") String params);

    /**
     * 删除角色
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/role/deleteSystemRoleByID.json")
    ResponseModel deleteSystemRoleByID(@RequestParam("params") String params);

    /**
     * 配置权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/system/role/setupRolePermission.json")
    ResponseModel setupRolePermission(@RequestParam("roleId") String roleId,@RequestParam("permissionIds") String permissionIds );
}
