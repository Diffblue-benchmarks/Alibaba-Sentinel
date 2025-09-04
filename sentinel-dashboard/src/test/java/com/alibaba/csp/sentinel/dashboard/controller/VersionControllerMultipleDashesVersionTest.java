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
@TestPropertySource(properties = "sentinel.dashboard.version=3.0.0-custom-build-123")
public class VersionControllerMultipleDashesVersionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void returnsVersionBeforeFirstDashWhenMultipleDashesPresent() throws Exception {
        mockMvc.perform(get("/version"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").value("3.0.0"))
            .andExpect(jsonPath("$.code").value(0));
    }
}

