package com.example.ant.data.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-13
 * Time: 22:29
 */
@Data
public class SystemRolePermissionModel implements Serializable {

    private String roleId;
    private String id;
    private String pid;
    private String name;
    private String isCode;
    private Boolean checked;

}
