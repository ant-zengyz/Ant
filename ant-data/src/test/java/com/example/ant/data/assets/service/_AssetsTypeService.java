package com.example.ant.data.assets.service;

import com.example.ant.common.bean.ResponseModel;
import com.example.ant.common.tools.UUIDTools;
import com.example.ant.data.assets.context.AssetsTypeContext;
import com.example.ant.data.assets.entity.AssetsType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 描述：service测试类
 * User: 曾远征
 * Date: 2018-09-01
 * Time: 11:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class _AssetsTypeService {

    @Autowired
    public AssetsTypeService assetsTypeService;

}
