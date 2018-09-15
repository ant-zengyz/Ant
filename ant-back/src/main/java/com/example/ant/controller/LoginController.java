package com.example.ant.controller;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-04
 * Time: 10:16
 */
@Controller
public class LoginController {

    /**
     * 登陆页
     * @param request
     * @param model
     * @return
     */
    @GetMapping({"/login.html"})
    public String login(HttpServletRequest request, Model model){
        Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
        if (null!=map){
            model.addAttribute("errormsg",map.get("error").toString());
        }
        return "login";
    }

    /**
     * 登陆错误捕获
     * @param request
     * @param redirectAttributes
     * @return
     */
    @PostMapping({"/login.html"})
    public String reloadLogin(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                redirectAttributes.addFlashAttribute("error","账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                redirectAttributes.addFlashAttribute("error","密码错误");
            } else if (DisabledAccountException.class.getName().equals(exception)){
                redirectAttributes.addFlashAttribute("error","账户被冻结");
            } else {
                redirectAttributes.addFlashAttribute("error","登录失败");
            }
        }
        return "redirect:/login.html";
}
}
