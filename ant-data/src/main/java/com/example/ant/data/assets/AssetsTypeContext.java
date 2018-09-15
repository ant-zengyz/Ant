package com.example.ant.data.assets;

import lombok.Getter;

/**
 * 描述：枚举类
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 10:55
 */
@Getter
public enum AssetsTypeContext {

    //状态
    Y(1, "Y", "启用"), N(0, "N", "禁用"),

    //类型
    DZQB(1, "DZQB", "电子钱包");

    /**
     * 数字编码
     */
    private Integer intCode;
    /**
     * 字符串编码
     */
    private String strCode;
    /**
     * 说明编码
     */
    private String msgCode;

    AssetsTypeContext(Integer param1, String param2, String param3) {
        this.intCode = param1;
        this.strCode = param2;
        this.msgCode = param3;
    }

}
