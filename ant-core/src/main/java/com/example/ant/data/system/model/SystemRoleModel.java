package com.example.ant.data.system.model;

import com.example.ant.data.system.entity.SystemUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-04
 * Time: 21:27
 */
@Data
public class SystemRoleModel implements Serializable {

    private String id;

    private String name;

    private String isCode;

    private String state;

    private Date createTime;

    private SystemUser createUser;

    private String remark;

    private List<SystemPermissionModel> systemPermissionModelList;
}
