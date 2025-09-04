package com.alibaba.csp.sentinel.dashboard.controller.v2;

import com.alibaba.csp.sentinel.dashboard.controller.AbstractWebMvcAuthTest;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.repository.rule.InMemoryRuleRepositoryAdapter;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FlowControllerV2.class)
public class FlowControllerV2Test extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private InMemoryRuleRepositoryAdapter<FlowRuleEntity> repository;
    @MockBean(name = "flowRuleDefaultProvider") private DynamicRuleProvider<List<FlowRuleEntity>> ruleProvider;
    @MockBean(name = "flowRuleDefaultPublisher") private DynamicRulePublisher<List<FlowRuleEntity>> rulePublisher;

    @Test
    @Ignore
    public void apiQueryMachineRulesRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/v2/flow/rules").param("app", " "))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiQueryMachineRulesReturnsSuccess() throws Exception {
        FlowRuleEntity e = new FlowRuleEntity();
        e.setLimitApp("default");
        e.setResource("res");
        e.setGrade(1);
        e.setCount(1d);
        e.setStrategy(0);
        e.setControlBehavior(0);
        when(ruleProvider.getRules("demo")).thenReturn(Collections.singletonList(e));
        when(repository.saveAll(any())).thenReturn(Collections.singletonList(e));
        mockMvc.perform(get("/v2/flow/rules").param("app", "demo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void apiAddFlowRuleFailsValidationForMissingApp() throws Exception {
        String body = "{\"limitApp\":\"default\",\"resource\":\"res\",\"grade\":1,\"count\":1,\"strategy\":0,\"controlBehavior\":0}";
        mockMvc.perform(post("/v2/flow/rule").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiAddFlowRuleSucceeds() throws Exception {
        doNothing().when(rulePublisher).publish(eq("demo"), any());
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        String body = "{\"app\":\"demo\",\"limitApp\":\"default\",\"resource\":\"res\",\"grade\":1,\"count\":1,\"strategy\":0,\"controlBehavior\":0}";
        mockMvc.perform(post("/v2/flow/rule").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.app").value("demo"));
    }
}
