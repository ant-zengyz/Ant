package com.example.ant.data.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUser implements Serializable {
    private String id;

    private String account;

    private String password;

    private String state;

    private Date createTime;

    private String createUserId;
    
}