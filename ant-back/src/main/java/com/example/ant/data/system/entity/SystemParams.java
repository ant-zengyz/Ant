package com.example.ant.data.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SystemParams implements Serializable {
    private String id;

    private String name;

    private String key;

    private String value;

    private String remark;

}