package com.example.ant.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 描述：
 *
 * @author zengyz
 * @date 2018/9/5 上午 9:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class _SystemUserController {

    @Autowired
    private SystemUserController systemUserController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(systemUserController).build();
    }

    @Test
    public void findByAccount() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/system/user/findByAccount.json").accept(MediaType.APPLICATION_JSON)
                .param("account","admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info("输出结果：{}",mvcResult.getResponse().getContentAsString());
    }

}
