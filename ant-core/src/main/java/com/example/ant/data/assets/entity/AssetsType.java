package com.example.ant.data.assets.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AssetsType implements Serializable {
    private String id;

    private String name;

    private String code;

    private String state;

    private Date createTime;

    private String createUserId;
}