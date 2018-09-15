package com.example.ant.data.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemRole implements Serializable {
    private String id;

    private String name;

    private String isCode;

    private String state;

    private Date createTime;

    private String createUserId;

    private String remark;

}