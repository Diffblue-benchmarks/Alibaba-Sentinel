package com.alibaba.csp.sentinel.dashboard.controller.cluster;

import com.alibaba.csp.sentinel.dashboard.controller.AbstractWebMvcAuthTest;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.ClusterAppAssignResultVO;
import com.alibaba.csp.sentinel.dashboard.service.ClusterAssignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClusterAssignController.class)
public class ClusterAssignControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClusterAssignService clusterAssignService;

    @Test
    public void apiAssignAllClusterServersOfAppFailsOnBadBody() throws Exception {
        mockMvc.perform(post("/cluster/assign/all_server/demo").contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiAssignAllClusterServersOfAppReturnsSuccess() throws Exception {
        when(clusterAssignService.applyAssignToApp(eq("demo"), anyList(), anySet()))
            .thenReturn(new ClusterAppAssignResultVO());
        mockMvc.perform(post("/cluster/assign/all_server/demo").contentType(MediaType.APPLICATION_JSON)
                .content("{\"clusterMap\":[],\"remainingList\":[]}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void apiUnbindClusterServersOfAppRejectsEmptyBody() throws Exception {
        mockMvc.perform(post("/cluster/assign/unbind_server/demo").contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void apiUnbindClusterServersOfAppReturnsSuccess() throws Exception {
        when(clusterAssignService.unbindClusterServers(eq("demo"), anySet()))
            .thenReturn(new ClusterAppAssignResultVO());
        mockMvc.perform(post("/cluster/assign/unbind_server/demo").contentType(MediaType.APPLICATION_JSON)
                .content("[\"ip@8719\"]"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }
}
