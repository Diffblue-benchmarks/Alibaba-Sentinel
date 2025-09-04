package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
@WebMvcTest(controllers = AuthorityRuleController.class)
public class AuthorityRuleControllerTest extends AbstractWebMvcAuthTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean private SentinelApiClient sentinelApiClient;
    @MockBean private RuleRepository<AuthorityRuleEntity, Long> repository;
    @MockBean private AppManagement appManagement;

    @Test
    public void apiQueryAllRulesForMachineRejectsInvalidParams() throws Exception {
        mockMvc.perform(get("/authority/rules").param("app", "").param("ip", "1.1.1.1").param("port", "8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiQueryAllRulesForMachineReturnsRules() throws Exception {
        when(appManagement.isValidMachineOfApp("appA", "1.1.1.1")).thenReturn(true);
        when(sentinelApiClient.fetchAuthorityRulesOfMachine("appA", "1.1.1.1", 8719)).thenReturn(Collections.emptyList());
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/authority/rules").param("app", "appA").param("ip", "1.1.1.1").param("port", "8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void apiAddAuthorityRuleFailsValidationForBlankApp() throws Exception {
        String body = "{\"app\":\"\",\"ip\":\"1.1.1.1\",\"port\":8719,\"resource\":\"r\",\"limitApp\":\"default\",\"strategy\":0,\"rule\":{}}";
        mockMvc.perform(post("/authority/rule").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }
}
