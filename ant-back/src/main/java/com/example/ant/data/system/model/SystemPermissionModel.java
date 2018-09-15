package com.example.ant.data.system.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-04
 * Time: 21:28
 */
@Data
public class SystemPermissionModel implements Serializable {

    private String id;

    private String isType;

    private String name;

    private String isCode;

    private String address;

    private String state;

    private String icon;

    private Integer sort;

    private Date createTime;

    private String createUserId;

    private String pid;

    private List<SystemPermissionModel> systemPermissionModelList;
}
