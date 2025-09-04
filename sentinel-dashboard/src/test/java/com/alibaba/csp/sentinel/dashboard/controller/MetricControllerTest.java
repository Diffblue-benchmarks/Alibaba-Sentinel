package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.repository.metric.MetricsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MetricController.class)
public class MetricControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private MetricsRepository<MetricEntity> metricStore;

    @Test
    public void queryTopResourceMetricRejectsEmptyApp() throws Exception {
        mockMvc.perform(get("/metric/queryTopResourceMetric.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void queryTopResourceMetricReturnsNullDataWhenNoResources() throws Exception {
        when(metricStore.listResourcesOfApp("demo")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/metric/queryTopResourceMetric.json").param("app","demo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void queryByAppAndResourceRejectsMissingIdentity() throws Exception {
        mockMvc.perform(get("/metric/queryByAppAndResource.json").param("app","demo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }
}
