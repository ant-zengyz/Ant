package com.example.ant.common.bean;

import lombok.Data;

/**
 * 描述：通用返回值
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 21:06
 */
@Data
public class ResponseModel {


    private boolean status;
    private String msg;
    private Object data;

    public ResponseModel() {
    }

    public static ResponseModel error() {
        return new ResponseModel(false, null, null);
    }

    public static ResponseModel error(String msg) {
        return new ResponseModel(false, msg, null);
    }

    public static ResponseModel success() {
        return new ResponseModel(true, null, null);
    }

    public static ResponseModel success(String msg) {
        return new ResponseModel(true, msg, null);
    }

    public static ResponseModel success(String msg, Object data) {
        return new ResponseModel(true, msg, data);
    }

    public ResponseModel(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}
