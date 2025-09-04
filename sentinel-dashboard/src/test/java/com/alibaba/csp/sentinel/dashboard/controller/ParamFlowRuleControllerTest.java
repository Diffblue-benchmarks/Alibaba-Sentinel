package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ParamFlowRuleController.class)
public class ParamFlowRuleControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SentinelApiClient sentinelApiClient;
    @MockBean private AppManagement appManagement;
    @MockBean private RuleRepository<ParamFlowRuleEntity, Long> repository;

    @Test
    public void apiQueryAllRulesForMachineRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/paramFlow/rules").param("app"," ").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiQueryAllRulesForMachineReturnsSuccess() throws Exception {
        when(appManagement.isValidMachineOfApp("demo","1.1.1.1")).thenReturn(true);
        when(sentinelApiClient.fetchParamFlowRulesOfMachine("demo","1.1.1.1",8719))
            .thenReturn(CompletableFuture.completedFuture(Collections.emptyList()));
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/paramFlow/rules").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void apiAddParamFlowRuleFailsValidationForNegativeCount() throws Exception {
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"resource\":\"res\",\"count\":-1,\"grade\":1,\"paramIdx\":0,\"durationInSec\":1,\"controlBehavior\":0,\"rule\":{}}";
        mockMvc.perform(post("/paramFlow/rule").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }
}
