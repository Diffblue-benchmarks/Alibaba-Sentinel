package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.repository.rule.RuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DegradeController.class)
public class DegradeControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private RuleRepository<DegradeRuleEntity, Long> repository;
    @MockBean private SentinelApiClient sentinelApiClient;
    @MockBean private AppManagement appManagement;

    @Test
    public void apiQueryMachineRulesRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/degrade/rules.json").param("app", "").param("ip", "1.1.1.1").param("port", "8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiQueryMachineRulesReturnsEmptyList() throws Exception {
        when(appManagement.isValidMachineOfApp("demo","1.1.1.1")).thenReturn(true);
        when(sentinelApiClient.fetchDegradeRuleOfMachine("demo","1.1.1.1",8719)).thenReturn(Collections.emptyList());
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/degrade/rules.json").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void apiAddRuleFailsValidationForMissingResource() throws Exception {
        String json = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"limitApp\":\"default\",\"count\":0.5,\"grade\":1,\"timeWindow\":10,\"minRequestAmount\":5,\"statIntervalMs\":1000}";
        when(appManagement.isValidMachineOfApp("demo","1.1.1.1")).thenReturn(true);
        mockMvc.perform(post("/degrade/rule").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }
}
