package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.domain.Result;
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
@TestPropertySource(properties = "sentinel.dashboard.version=1.8.8-SNAPSHOT")
public class VersionControllerVersionPresentTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void returnsVersionPartBeforeDashWhenVersionContainsDash() throws Exception {
        mockMvc.perform(get("/version"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.msg").value("success"))
            .andExpect(jsonPath("$.data").value("1.8.8"))
            .andExpect(jsonPath("$.code").value(0));
    }
}

