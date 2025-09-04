package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.repository.rule.InMemoryRuleRepositoryAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FlowControllerV1.class)
public class FlowControllerV1Test extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private InMemoryRuleRepositoryAdapter<FlowRuleEntity> repository;
    @MockBean private AppManagement appManagement;
    @MockBean private SentinelApiClient sentinelApiClient;

    @Test
    public void apiQueryMachineRulesRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/v1/flow/rules").param("app"," ").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiQueryMachineRulesReturnsSuccess() throws Exception {
        when(appManagement.isValidMachineOfApp("demo","1.1.1.1")).thenReturn(true);
        when(sentinelApiClient.fetchFlowRuleOfMachine("demo","1.1.1.1",8719)).thenReturn(Collections.emptyList());
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/v1/flow/rules").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }
}
