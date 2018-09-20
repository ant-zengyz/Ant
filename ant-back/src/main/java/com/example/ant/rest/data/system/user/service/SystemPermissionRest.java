package com.example.ant.rest.data.system.user.service;

import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findPermissionListBySystemRoleId.json")
    ResponseModel findPermissionListBySystemRoleId(@RequestParam("systemRoleId") String systemRoleId);

    /**
     * 查询菜单树
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findPermissionListToTree.json")
    ResponseModel findPermissionListToTree();

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findSystemPermissionListByParams.json")
    ResponseLayuiTableModel findSystemPermissionListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params);

    @RequestMapping(method = RequestMethod.GET,value = "/system/permission/findSystemPermissionListNotPage.json")
    ResponseModel findSystemPermissionListNotPage();

}
