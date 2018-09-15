package com.example.ant.rest.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemPermission;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.model.SystemPermissionModel;
import com.example.ant.data.system.service.SystemPermissionService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/5 上午 9:31
 */
@RestController
@RequestMapping("/system/permission")
@Slf4j
public class SystemPermissionController {

    @Autowired
    private SystemPermissionService systemPermissionService;

    /**
     * 查询角色所拥有的权限
     * @param systemRoleId
     * @return
     */
    @GetMapping("/findPermissionListBySystemRoleId.json")
    public ResponseModel findPermissionListBySystemRoleId(@RequestParam("systemRoleId") String systemRoleId){
        List<SystemPermission> systemPermissionList = systemPermissionService.findPermissionListBySystemRoleId(systemRoleId);
        return ResponseModel.success(systemPermissionList);
    }

    /**
     * 查询菜单树
     * @return
     */
    @GetMapping("/findPermissionListToTree.json")
    public ResponseModel findPermissionListToTree(){
        List<SystemPermissionModel> menuTree = systemPermissionService.findMenuTree();
        return ResponseModel.success(menuTree);
    }

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @GetMapping("/findSystemPermissionListByParams.json")
    public ResponseLayuiTableModel findSystemPermissionListByParams(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestParam("params") String params){
        ResponseLayuiTableModel responseLayuiTableModel=new ResponseLayuiTableModel();
        try {
            PageInfo<SystemPermission> pageList=new PageInfo<>();
            if (StringUtils.isEmpty(params)){
                pageList = systemPermissionService.findPageList(pageNum, pageSize, null);
            }else{
                pageList = systemPermissionService.findPageList(pageNum, pageSize, JSON.parseObject(params, Map.class));
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

    @GetMapping("/findSystemPermissionListNotPage.json")
    public ResponseModel findSystemPermissionListNotPage(){
        return systemPermissionService.findPageList();
    }
}
