package com.example.ant.data.system.service;

import com.alibaba.fastjson.JSON;
import com.example.ant.data.system.model.SystemUserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述：service测试类
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class _SystemUserService {

    @Autowired
    private SystemUserService systemUserService;

    @Test
    public void findByAccount(){
        SystemUserModel admin = systemUserService.findByAccount("admin");
        String adminStr = JSON.toJSONString(admin);
        System.out.printf(adminStr);
    }

}
