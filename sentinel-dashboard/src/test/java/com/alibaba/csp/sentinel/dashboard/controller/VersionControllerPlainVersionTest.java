package com.alibaba.csp.sentinel.dashboard.controller;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VersionController.class)
@TestPropertySource(properties = "sentinel.dashboard.version=2.0.0")
public class VersionControllerPlainVersionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void returnsExactVersionWhenNoDashPresent() throws Exception {
        mockMvc.perform(get("/version"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").value("2.0.0"))
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.msg").value("success"));
    }
}

