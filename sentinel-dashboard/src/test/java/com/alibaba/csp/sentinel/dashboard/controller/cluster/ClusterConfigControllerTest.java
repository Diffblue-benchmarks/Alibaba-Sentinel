package com.alibaba.csp.sentinel.dashboard.controller.cluster;

import com.alibaba.csp.sentinel.dashboard.controller.AbstractWebMvcAuthTest;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.request.ClusterClientModifyRequest;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.state.ClusterUniversalStateVO;
import com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClusterConfigController.class)
public class ClusterConfigControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClusterConfigService clusterConfigService;

    @Test
    @Ignore
    public void apiModifyClusterConfigRejectsEmptyBody() throws Exception {
        mockMvc.perform(post("/cluster/config/modify_single").contentType(MediaType.APPLICATION_JSON).content(""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiModifyClusterConfigRejectsInvalidMode() throws Exception {
        mockMvc.perform(post("/cluster/config/modify_single").contentType(MediaType.APPLICATION_JSON).content("{\"mode\":99}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    @Ignore
    public void apiModifyClusterConfigAcceptsClientMode() throws Exception {
        ClusterClientModifyRequest req = new ClusterClientModifyRequest();
        req.setApp("demo");
        req.setIp("1.1.1.1");
        req.setPort(8719);
        req.setMode(1);
        when(clusterConfigService.modifyClusterClientConfig(any())).thenReturn(CompletableFuture.completedFuture(null));
        String body = "{\"app\":\"demo\",\"ip\":\"1.1.1.1\",\"port\":8719,\"mode\":1}";
        mockMvc.perform(post("/cluster/config/modify_single").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    public void apiGetClusterStateRejectsBlankApp() throws Exception {
        mockMvc.perform(get("/cluster/state_single").param("app"," ").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiGetClusterStateReturnsSuccess() throws Exception {
        when(clusterConfigService.getClusterUniversalState("demo","1.1.1.1",8719))
            .thenReturn(CompletableFuture.completedFuture(new ClusterUniversalStateVO()));
        mockMvc.perform(get("/cluster/state_single").param("app","demo").param("ip","1.1.1.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }
}
