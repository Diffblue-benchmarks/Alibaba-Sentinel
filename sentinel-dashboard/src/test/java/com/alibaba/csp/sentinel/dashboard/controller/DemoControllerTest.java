package com.alibaba.csp.sentinel.dashboard.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DemoController.class)
public class DemoControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;

    @Test
    @Ignore
    public void greetingReturnsIndexViewName() throws Exception {
        mockMvc.perform(get("/demo/greeting"))
            .andExpect(status().isOk())
            .andExpect(content().string("index"));
    }

    @Test
    public void linkReturnsSuccessMessage() throws Exception {
        mockMvc.perform(get("/demo/link"))
            .andExpect(status().isOk())
            .andExpect(content().string("successfully create a call link"));
    }

    @Test
    public void loopStartsThreadsAndReturnsMessage() throws Exception {
        mockMvc.perform(get("/demo/loop").param("name","res").param("time","1"))
            .andExpect(status().isOk())
            .andExpect(content().string("successfully create a loop thread"));
    }
}
