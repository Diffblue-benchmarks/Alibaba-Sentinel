package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.command.vo.NodeVo;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ResourceController.class)
public class ResourceControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SentinelApiClient httpFetcher;

    @Test
    public void fetchResourceChainListOfMachineRejectsMissingParams() throws Exception {
        mockMvc.perform(get("/resource/machineResource.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void fetchResourceChainListOfMachineReturnsTreeForRootType() throws Exception {
        NodeVo n1 = new NodeVo();
        n1.setResource("/a");
        NodeVo n2 = new NodeVo();
        n2.setResource("/b");
        when(httpFetcher.fetchResourceOfMachine("127.0.0.1", 8719, "root"))
            .thenReturn(Arrays.asList(n1, n2));
        mockMvc.perform(get("/resource/machineResource.json").param("ip","127.0.0.1").param("port","8719").param("type","root"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void fetchResourceChainListOfMachineReturnsClusterNodesWhenTypeCluster() throws Exception {
        NodeVo n = new NodeVo();
        n.setResource("/c");
        when(httpFetcher.fetchClusterNodeOfMachine(eq("127.0.0.1"), eq(8719), anyBoolean()))
            .thenReturn(Collections.singletonList(n));
        mockMvc.perform(get("/resource/machineResource.json").param("ip","127.0.0.1").param("port","8719").param("type","cluster"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data[0].resource").value("/c"));
    }
}
