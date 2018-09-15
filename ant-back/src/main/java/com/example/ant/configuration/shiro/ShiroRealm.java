package com.example.ant.configuration.shiro;

import com.alibaba.fastjson.JSON;
import com.example.ant.common.bean.ResponseModel;
import com.example.ant.context.SessionContext;
import com.example.ant.data.system.context.SystemUserContext;
import com.example.ant.data.system.entity.SystemPermission;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.model.SystemUserModel;
import com.example.ant.rest.data.system.user.service.SystemPermissionRest;
import com.example.ant.rest.data.system.user.service.SystemUserRest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 描述：自定义Realm
 *
 * @author zengyz
 * @date 2018/9/4 下午 3:29
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired(required=false)
    private SystemUserRest systemUserRest;

    @Autowired(required=false)
    private SystemPermissionRest systemPermissionRest;

    /**
     * 权限配置
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SystemUserModel user = (SystemUserModel) principals.getPrimaryPrincipal();
        log.info("【{}】用户配置权限开始", user.getAccount());
        List<SystemRole> systemRoleList = user.getSystemRoleList();
        if (CollectionUtils.isEmpty(systemRoleList)) {
            log.info("【{}】用户没有所属的角色，用户配置权限结束");
            return authorizationInfo;
        }
        for (SystemRole systemRole : systemRoleList) {
            log.info("【{}】用户配置权限,拥有角色：{}", user.getAccount(), systemRole.getName());
            authorizationInfo.addRole(systemRole.getIsCode());
            if (systemRole.getIsCode().toUpperCase().equals("ADMIN")){
                log.info("【{}】用户配置权限,拥有角色：{},ADMIN是超级管理员账户，拥有全部权限", user.getAccount(), systemRole.getName());
                ResponseModel responseModel = systemPermissionRest.findSystemPermissionListNotPage();
                if (responseModel.isStatus()) {
                    List<SystemPermission> systemPermissions = JSON.parseArray(responseModel.getData().toString(), SystemPermission.class);
                    for (SystemPermission systemPermission : systemPermissions) {
                        log.info("【{}】用户配置权限,拥有角色：{}，该角色拥有权限：{}", user.getAccount(), systemRole.getName(), systemPermission.getName());
                        authorizationInfo.addStringPermission(systemPermission.getIsCode());
                    }
                }
            }else{
                ResponseModel responseModel = systemPermissionRest.findPermissionListBySystemRoleId(systemRole.getId());
                if (responseModel.isStatus()) {
                    List<SystemPermission> systemPermissions = JSON.parseArray(responseModel.getData().toString(), SystemPermission.class);
                    if (CollectionUtils.isEmpty(systemPermissions)) {
                        log.info("【{}】用户配置权限,拥有角色：{}，该角色无配置权限", user.getAccount(), systemRole.getName());
                        continue;
                    }
                    for (SystemPermission systemPermission : systemPermissions) {
                        log.info("【{}】用户配置权限,拥有角色：{}，该角色拥有权限：{}", user.getAccount(), systemRole.getName(), systemPermission.getName());
                        authorizationInfo.addStringPermission(systemPermission.getIsCode());
                    }
                } else {
                    log.info("【{}】用户，查询角色权限失败");
                    continue;
                }
            }
        }
        log.info("【{}】用户配置权限结束", user.getAccount());
        return authorizationInfo;
    }

    /**
     * 登录配置
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = String.valueOf(token.getPrincipal());
        log.info("登录验证：{}", account);
        ResponseModel responseModel = systemUserRest.findSystemUserModelByAccount(account);
        if (!responseModel.isStatus()) {
            return null;
        } else {

            if (null==responseModel.getData()||"null".equals(responseModel.getData().toString())) {
                throw new UnknownAccountException();
            } else {
                SystemUserModel systemUserModel = JSON.parseObject(responseModel.getData().toString(), SystemUserModel.class);
                if (systemUserModel.getState().equals(SystemUserContext.N.getStrCode())) {
                    throw new DisabledAccountException();
                } else {
                    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(systemUserModel, systemUserModel.getPassword(), getName());
                    Subject subject = SecurityUtils.getSubject();
                    subject.getSession().setAttribute(SessionContext.LOGINUSER, systemUserModel);
                    subject.getSession().setTimeout(600000);
                    return simpleAuthenticationInfo;
                }
            }
        }
    }
}
