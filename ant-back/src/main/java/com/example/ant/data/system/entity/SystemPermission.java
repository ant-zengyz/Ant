package com.example.ant.data.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemPermission implements Serializable {
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
}