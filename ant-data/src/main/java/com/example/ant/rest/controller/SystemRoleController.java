package com.example.ant.rest.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseLayuiTableModel;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.entity.SystemUser;
import com.example.ant.data.system.model.SystemUserModel;
import com.example.ant.data.system.service.SystemRoleService;
import com.example.ant.data.system.service.SystemUserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 描述：后台-管理员相关接口
 * User: 曾远征
 * Date: 2018-09-02
 * Time: 21:17
 */
@RestController
@RequestMapping("/system/role")
@Slf4j
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    /**
     * 分页数据查询
     * @param pageSize
     * @param pageNum
     * @param params
     * @return
     */
    @GetMapping("/findSystemRoleListByParams.json")
    public ResponseLayuiTableModel findSystemRoleListByParams(@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum") Integer pageNum,@RequestParam("params") String params ){
        ResponseLayuiTableModel responseLayuiTableModel=new ResponseLayuiTableModel();
        try {
            PageInfo<SystemRole> pageList=new PageInfo<>();
            if (StringUtils.isEmpty(params)){
                pageList = systemRoleService.findPageList(pageNum, pageSize, null);
            }else{
                pageList = systemRoleService.findPageList(pageNum, pageSize, JSON.parseObject(params, Map.class));
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
    @GetMapping("/findSystemRoleByID.json")
    public ResponseModel findSystemRoleByID(@RequestParam("id") String id){
        SystemRole systemRole = systemRoleService.findOneByID(id);
        return ResponseModel.success(systemRole);
    }

    /**
     * 修改
     * @param params
     * @return
     */
    @PostMapping("/updateSystemRoleByID.json")
    public ResponseModel updateSystemRoleByID(@RequestParam("params") String params){
        SystemRole systemRole = JSON.parseObject(params, SystemRole.class);
        return systemRoleService.updateSystemRoleByID(systemRole);
    }

    /**
     * 创建
     * @param params
     * @return
     */
    @PostMapping("/createSystemRole.json")
    ResponseModel createSystemRole(@RequestParam("params") String params){
        SystemRole systemRole = JSON.parseObject(params, SystemRole.class);
        return systemRoleService.createSystemRole(systemRole);
    }

    /**
     * 删除
     * @param params
     * @return
     */
    @PostMapping("/deleteSystemRoleByID.json")
    ResponseModel deleteSystemRoleByID(@RequestParam("params") String params){
        return systemRoleService.deleteSystemRoleByID(params);
    }

    /**
     * 查询全部角色
     * @return
     */
    @GetMapping("/findSystemRoleList.json")
    ResponseModel findSystemRoleList(){
        List<SystemRole> list = systemRoleService.findList();
        return ResponseModel.success(list);
    }

    /**
     * 配置权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PostMapping("/setupRolePermission.json")
    public ResponseModel setupRolePermission(@RequestParam("roleId") String roleId,@RequestParam("permissionIds") String permissionIds ){
        return systemRoleService.setupRolePermission(roleId,permissionIds);
    }
}
