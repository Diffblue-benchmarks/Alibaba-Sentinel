package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.repository.rule.InMemoryRuleRepositoryAdapter;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FlowControllerV1.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class FlowControllerV1DiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private FlowControllerV1 flowControllerV1;

  @MockBean
  private InMemoryRuleRepositoryAdapter<FlowRuleEntity> inMemoryRuleRepositoryAdapter;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link FlowControllerV1#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV1#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given SentinelApiClient; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenSentinelApiClient_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(inMemoryRuleRepositoryAdapter.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());

    ClusterFlowConfig clusterConfig = new ClusterFlowConfig();
    clusterConfig.setAcquireRefuseStrategy(1);
    clusterConfig.setClientOfflineTime(1L);
    clusterConfig.setFallbackToLocalWhenFail(true);
    clusterConfig.setFlowId(1L);
    clusterConfig.setResourceTimeout(1L);
    clusterConfig.setResourceTimeoutStrategy(10);
    clusterConfig.setSampleCount(3);
    clusterConfig.setStrategy(1);
    clusterConfig.setThresholdType(1);
    clusterConfig.setWindowIntervalMs(42);

    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("App");
    flowRuleEntity.setClusterConfig(clusterConfig);
    flowRuleEntity.setClusterMode(true);
    flowRuleEntity.setControlBehavior(1);
    flowRuleEntity.setCount(10.0d);
    flowRuleEntity.setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    flowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    flowRuleEntity.setGrade(1);
    flowRuleEntity.setId(1L);
    flowRuleEntity.setIp("127.0.0.1");
    flowRuleEntity.setLimitApp("Limit App");
    flowRuleEntity.setMaxQueueingTimeMs(3);
    flowRuleEntity.setPort(8080);
    flowRuleEntity.setRefResource("Ref Resource");
    flowRuleEntity.setResource("Resource");
    flowRuleEntity.setStrategy(1);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV1)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Cannot invoke \\\"java.lang.Integer.intValue()\\\" because \\\"port\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV1#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":null,"data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV1#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":null,\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_thenContentStringSuccessFalseCode1MsgNullDataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setPort(8080);
    when(inMemoryRuleRepositoryAdapter.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(flowRuleEntity);
    when(sentinelApiClient.setFlowRuleOfMachineAsync(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<FlowRuleEntity>>any())).thenReturn(new CompletableFuture<>());

    ClusterFlowConfig clusterConfig = new ClusterFlowConfig();
    clusterConfig.setAcquireRefuseStrategy(1);
    clusterConfig.setClientOfflineTime(1L);
    clusterConfig.setFallbackToLocalWhenFail(true);
    clusterConfig.setFlowId(1L);
    clusterConfig.setResourceTimeout(1L);
    clusterConfig.setResourceTimeoutStrategy(10);
    clusterConfig.setSampleCount(3);
    clusterConfig.setStrategy(1);
    clusterConfig.setThresholdType(1);
    clusterConfig.setWindowIntervalMs(42);

    FlowRuleEntity flowRuleEntity2 = new FlowRuleEntity();
    flowRuleEntity2.setApp("App");
    flowRuleEntity2.setClusterConfig(clusterConfig);
    flowRuleEntity2.setClusterMode(true);
    flowRuleEntity2.setControlBehavior(1);
    flowRuleEntity2.setCount(10.0d);
    flowRuleEntity2.setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    flowRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    flowRuleEntity2.setGrade(1);
    flowRuleEntity2.setId(1L);
    flowRuleEntity2.setIp("127.0.0.1");
    flowRuleEntity2.setLimitApp("Limit App");
    flowRuleEntity2.setMaxQueueingTimeMs(3);
    flowRuleEntity2.setPort(8080);
    flowRuleEntity2.setRefResource("Ref Resource");
    flowRuleEntity2.setResource("Resource");
    flowRuleEntity2.setStrategy(1);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV1)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":false,\"code\":-1,\"msg\":null,\"data\":null}"));
  }
}
