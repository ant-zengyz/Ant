package com.example.ant.rest.data.system.user.service;

import com.example.ant.annotation.RestLog;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.rest.RestContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/5 下午 3:09
 */
@FeignClient(value = "ANT-DATA" ,fallback=SystemPermissionRestHystric.class)
public interface SystemPermissionRest {

    /**
     * 根据角色ID查询权限
     * @param systemRoleId
     * @return
     */
    @RestLog(description = "权限-根据角色ID查询权限")
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findPermissionListBySystemRoleId.json")
    ResponseModel findPermissionListBySystemRoleId(@RequestParam("systemRoleId") String systemRoleId);

    /**
     * 查询菜单树
     * @return
     */
    @RestLog(description = "权限-查询菜单树")
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findPermissionListToTree.json")
    ResponseModel findPermissionListToTree();

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @RestLog(description = "权限-查询分页数据")
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findSystemPermissionListByParams.json")
    ResponseLayuiTableModel findSystemPermissionListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params);

    @RestLog(description = "权限-查询，不分页")
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findSystemPermissionListNotPage.json")
    ResponseModel findSystemPermissionListNotPage();

}
