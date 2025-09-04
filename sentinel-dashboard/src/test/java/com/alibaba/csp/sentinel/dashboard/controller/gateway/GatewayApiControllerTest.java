package com.alibaba.csp.sentinel.dashboard.controller.gateway;

import com.alibaba.csp.sentinel.dashboard.controller.AbstractWebMvcAuthTest;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiPredicateItemEntity;
import com.alibaba.csp.sentinel.dashboard.repository.gateway.InMemApiDefinitionStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GatewayApiController.class)
public class GatewayApiControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private InMemApiDefinitionStore repository;
    @MockBean private SentinelApiClient sentinelApiClient;

    @Test
    public void queryApisRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/gateway/api/list.json").param("app"," ").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void queryApisReturnsSuccess() throws Exception {
        ApiDefinitionEntity e = new ApiDefinitionEntity();
        e.setApp("demo");
        e.setIp("1.1.1.1");
        e.setPort(8719);
        e.setApiName("apiA");
        e.setPredicateItems(new LinkedHashSet<ApiPredicateItemEntity>()); // empty placeholder
        e.setGmtCreate(new Date());
        e.setGmtModified(new Date());
        when(sentinelApiClient.fetchApis("demo","1.1.1.1",8719)).thenReturn(CompletableFuture.completedFuture(Collections.singletonList(e)));
        when(repository.saveAll(anyList())).thenReturn(Collections.singletonList(e));
        mockMvc.perform(get("/gateway/api/list.json").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data[0].apiName").value("apiA"));
    }

    @Test
    public void addApiFailsWhenMissingPredicateItems() throws Exception {
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"apiName\":\"apiA\",\"predicateItems\":[]}";
        mockMvc.perform(post("/gateway/api/new.json").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void addApiSucceeds() throws Exception {
        when(repository.findAllByMachine(any())).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"apiName\":\"apiA\",\"predicateItems\":[{\"matchStrategy\":0,\"pattern\":\"/a\"}]}";
        mockMvc.perform(post("/gateway/api/new.json").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.apiName").value("apiA"));
    }
}
