package com.example.ant.common.exception;

import com.example.ant.common.bean.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：捕获全局异常
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 21:03
 */
@ControllerAdvice
@Slf4j
public class DataExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel dataRestExceptionHandler(Exception ex) {
        log.error("捕获到异常信息", ex);
        return ResponseModel.error(ex.getMessage());
    }

}
