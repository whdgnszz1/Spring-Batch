package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@SpringBootTest
public class DemoControllerTest {

    @Autowired DemoController target;

    @Test
    public void runTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(target).build();
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
