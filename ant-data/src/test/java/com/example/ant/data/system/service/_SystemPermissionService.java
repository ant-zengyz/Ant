package com.example.ant.data.system.service;

import com.example.ant.common.tools.UUIDTools;
import com.example.ant.data.system.context.SystemPermissionContext;
import com.example.ant.data.system.entity.SystemPermission;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/5 下午 2:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class _SystemPermissionService {

    @Autowired
    private SystemPermissionService systemPermissionService;

    @Test
    public void add(){
        /*SystemPermission sp=new SystemPermission();
        sp.setId(UUIDTools.getUUID32());
        sp.setName("系统设置");
        sp.setIsCode("SYSTEM");
        sp.setIsType(SystemPermissionContext.MENU.getStrCode());
        sp.setSort(1);
        sp.setAddress("javascript:void(0)");
        sp.setState(SystemPermissionContext.Y.getStrCode());
        sp.setCreateTime(new Date());
        sp.setCreateUserId("2817a4b39cec41249276cec66e18be34");
        sp.setPid("0");
        boolean isBoolean = systemPermissionService.add(sp);
        if (isBoolean){
            log.debug("添加成功");
        }else{
            log.debug("添加失败");
        }*/

        /*SystemPermission sp=new SystemPermission();
        sp.setId(UUIDTools.getUUID32());
        sp.setName("管理员");
        sp.setIsCode("SYSTEM.USER");
        sp.setIsType(SystemPermissionContext.PAGE.getStrCode());
        sp.setSort(1);
        sp.setAddress("javascript:void(0)");
        sp.setState(SystemPermissionContext.Y.getStrCode());
        sp.setCreateTime(new Date());
        sp.setCreateUserId("2817a4b39cec41249276cec66e18be34");
        sp.setPid("e1ce76507e964f8d9bf4848948dae1ec");
        boolean isBoolean = systemPermissionService.add(sp);
        if (isBoolean){
            log.debug("添加成功");
        }else{
            log.debug("添加失败");
        }*/

        /*SystemPermission sp=new SystemPermission();
        sp.setId(UUIDTools.getUUID32());
        sp.setName("角色");
        sp.setIsCode("SYSTEM.ROLE");
        sp.setIsType(SystemPermissionContext.PAGE.getStrCode());
        sp.setSort(1);
        sp.setAddress("javascript:void(0)");
        sp.setState(SystemPermissionContext.Y.getStrCode());
        sp.setCreateTime(new Date());
        sp.setCreateUserId("2817a4b39cec41249276cec66e18be34");
        sp.setPid("e1ce76507e964f8d9bf4848948dae1ec");
        boolean isBoolean = systemPermissionService.add(sp);
        if (isBoolean){
            log.debug("添加成功");
        }else{
            log.debug("添加失败");
        }*/

        /*SystemPermission sp=new SystemPermission();
        sp.setId(UUIDTools.getUUID32());
        sp.setName("权限");
        sp.setIsCode("SYSTEM.PERMISSION");
        sp.setIsType(SystemPermissionContext.PAGE.getStrCode());
        sp.setSort(1);
        sp.setAddress("javascript:void(0)");
        sp.setState(SystemPermissionContext.Y.getStrCode());
        sp.setCreateTime(new Date());
        sp.setCreateUserId("2817a4b39cec41249276cec66e18be34");
        sp.setPid("e1ce76507e964f8d9bf4848948dae1ec");
        boolean isBoolean = systemPermissionService.add(sp);
        if (isBoolean){
            log.debug("添加成功");
        }else{
            log.debug("添加失败");
        }*/

        /*SystemPermission sp=new SystemPermission();
        sp.setId(UUIDTools.getUUID32());
        sp.setName("会员管理");
        sp.setIsCode("MEMBER");
        sp.setIsType(SystemPermissionContext.MENU.getStrCode());
        sp.setSort(1);
        sp.setAddress("javascript:void(0)");
        sp.setState(SystemPermissionContext.Y.getStrCode());
        sp.setCreateTime(new Date());
        sp.setCreateUserId("2817a4b39cec41249276cec66e18be34");
        sp.setPid("0");
        boolean isBoolean = systemPermissionService.add(sp);
        if (isBoolean){
            log.debug("添加成功");
        }else{
            log.debug("添加失败");
        }*/
    }
}
