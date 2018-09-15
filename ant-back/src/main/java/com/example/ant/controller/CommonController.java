package com.example.ant.controller;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.configuration.redis.RedisManager;
import com.example.ant.context.SessionContext;
import com.example.ant.data.system.model.SystemPermissionModel;
import com.example.ant.rest.data.system.user.service.SystemPermissionRest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/6 上午 9:39
 */
@Controller
public class CommonController {

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private SystemPermissionRest systemPermissionRest;

    /**
     * 首页
     * @param request
     * @param model
     * @return
     */
    @GetMapping({"/","/index.html"})
    public String index(HttpServletRequest request, Model model){
        List<SystemPermissionModel> systemPermissionModels=new ArrayList<>();
        String menuTreeStr = redisManager.get(SessionContext.MENUTREE);
        if (StringUtils.isEmpty(menuTreeStr)){
            ResponseModel responseModel = systemPermissionRest.findPermissionListToTree();
            if (responseModel.isStatus()){
                if (StringUtils.isNotEmpty(responseModel.getData().toString())){
                    systemPermissionModels = JSON.parseArray(responseModel.getData().toString(), SystemPermissionModel.class);
                    redisManager.set(SessionContext.MENUTREE,responseModel.getData().toString());
                }
            }
        }else{
            systemPermissionModels = JSON.parseArray(menuTreeStr, SystemPermissionModel.class);
        }
        model.addAttribute("menuTree",systemPermissionModels);
        return "index";
    }

    /**
     * 图标页
     * @return
     */
    @GetMapping("/system/unicode.html")
    public String unicode(){
        return "system/unicode";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/welcome.html")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/signout.html")
    public String signout(HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
