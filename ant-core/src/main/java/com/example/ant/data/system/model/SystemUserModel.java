package com.example.ant.data.system.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.ant.data.system.entity.SystemRole;
import com.example.ant.data.system.entity.SystemUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 * User: 曾远征
 * Date: 2018-09-04
 * Time: 21:26
 */
@Data
public class SystemUserModel implements Serializable {

    private String id;

    private String account;

    private String password;

    private String state;

    private Date createTime;

    private SystemUser createUser;

    private List<SystemRole> systemRoleList;

    private String createTimeStr;
}
