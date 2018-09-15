package com.example.ant.data.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-12
 * Time: 11:34
 */
@Data
public class SystemUserRoleModel implements Serializable {

    private String userId;
    private String roleId;
    private String checkbox;
    private String roleName;

}
