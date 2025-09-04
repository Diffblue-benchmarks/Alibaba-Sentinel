package com.alibaba.csp.sentinel.dashboard.controller.gateway;

import com.alibaba.csp.sentinel.dashboard.controller.AbstractWebMvcAuthTest;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.repository.gateway.InMemGatewayFlowRuleStore;
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
@WebMvcTest(controllers = GatewayFlowRuleController.class)
public class GatewayFlowRuleControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private InMemGatewayFlowRuleStore repository;
    @MockBean private SentinelApiClient sentinelApiClient;

    @Test
    public void queryFlowRulesRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/gateway/flow/list.json").param("app"," ").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void queryFlowRulesReturnsSuccess() throws Exception {
        when(sentinelApiClient.fetchGatewayFlowRules("demo","1.1.1.1",8719)).thenReturn(CompletableFuture.completedFuture(Collections.emptyList()));
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/gateway/flow/list.json").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void addFlowRuleFailsValidationForMissingResource() throws Exception {
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"resourceMode\":0,\"resource\":\"\",\"grade\":1,\"count\":1,\"interval\":1,\"intervalUnit\":0,\"controlBehavior\":0,\"burst\":0}";
        mockMvc.perform(post("/gateway/flow/new.json").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void addFlowRuleSucceeds() throws Exception {
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(repository.findAllByMachine(any())).thenReturn(Collections.emptyList());
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"resourceMode\":0,\"resource\":\"routeA\",\"grade\":1,\"count\":1,\"interval\":1,\"intervalUnit\":0,\"controlBehavior\":0,\"burst\":0}";
        mockMvc.perform(post("/gateway/flow/new.json").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.resource").value("routeA"));
    }
}
