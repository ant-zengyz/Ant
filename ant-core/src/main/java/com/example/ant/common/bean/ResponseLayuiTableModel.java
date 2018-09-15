package com.example.ant.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：表格分页数据返回
 * User: 曾远征
 * Date: 2018-09-08
 * Time: 15:51
 */
@Data
public class ResponseLayuiTableModel implements Serializable {

    private Integer code;
    private String  msg;
    private Long count;
    private Object data;
}
