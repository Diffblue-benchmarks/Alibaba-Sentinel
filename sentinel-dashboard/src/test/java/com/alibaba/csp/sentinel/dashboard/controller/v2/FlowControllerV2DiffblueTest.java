package com.alibaba.csp.sentinel.dashboard.controller.v2;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.repository.rule.InMemoryRuleRepositoryAdapter;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
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

@ContextConfiguration(classes = {FlowControllerV2.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class FlowControllerV2DiffblueTest {
  @MockBean(name = "flowRuleDefaultProvider")
  private DynamicRuleProvider<List<FlowRuleEntity>> dynamicRuleProvider;

  @MockBean(name = "flowRuleDefaultPublisher")
  private DynamicRulePublisher<List<FlowRuleEntity>> dynamicRulePublisher;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private FlowControllerV2 flowControllerV2;

  @MockBean
  private InMemoryRuleRepositoryAdapter<FlowRuleEntity> inMemoryRuleRepositoryAdapter;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenThrow(new Exception("?"));
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"java.lang.Exception, ?\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules2() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link FlowRuleEntity} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String); given ArrayList() add FlowRuleEntity (default constructor)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_givenArrayListAddFlowRuleEntity() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());

    ArrayList<FlowRuleEntity> flowRuleEntityList = new ArrayList<>();
    flowRuleEntityList.add(new FlowRuleEntity());
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(flowRuleEntityList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>When {@code foo}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String); given ArrayList() add 'null'; when 'foo'; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_givenArrayListAddNull_whenFoo_thenContentStringAString() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());

    ArrayList<FlowRuleEntity> flowRuleEntityList = new ArrayList<>();
    flowRuleEntityList.add(null);
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(flowRuleEntityList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"java.lang.NullPointerException, Cannot invoke \\\"com.alibaba.csp"
                    + ".sentinel.dashboard.datasource.entity.rule.FlowRuleEntity.setApp(String)\\\" because \\\"entity\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <ul>
   *   <li>Given {@link ClusterFlowConfig} (default constructor) FlowId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String); given ClusterFlowConfig (default constructor) FlowId is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_givenClusterFlowConfigFlowIdIsNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());

    ClusterFlowConfig clusterConfig = new ClusterFlowConfig();
    clusterConfig.setAcquireRefuseStrategy(3);
    clusterConfig.setClientOfflineTime(3L);
    clusterConfig.setFallbackToLocalWhenFail(true);
    clusterConfig.setFlowId(null);
    clusterConfig.setResourceTimeout(3L);
    clusterConfig.setResourceTimeoutStrategy(10);
    clusterConfig.setSampleCount(3);
    clusterConfig.setStrategy(3);
    clusterConfig.setThresholdType(3);
    clusterConfig.setWindowIntervalMs(42);

    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setClusterConfig(clusterConfig);

    ArrayList<FlowRuleEntity> flowRuleEntityList = new ArrayList<>();
    flowRuleEntityList.add(flowRuleEntity);
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(flowRuleEntityList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <ul>
   *   <li>Given {@link ClusterFlowConfig} (default constructor) FlowId is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String); given ClusterFlowConfig (default constructor) FlowId is one")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_givenClusterFlowConfigFlowIdIsOne() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());

    ClusterFlowConfig clusterConfig = new ClusterFlowConfig();
    clusterConfig.setAcquireRefuseStrategy(3);
    clusterConfig.setClientOfflineTime(3L);
    clusterConfig.setFallbackToLocalWhenFail(true);
    clusterConfig.setFlowId(1L);
    clusterConfig.setResourceTimeout(3L);
    clusterConfig.setResourceTimeoutStrategy(10);
    clusterConfig.setSampleCount(3);
    clusterConfig.setStrategy(3);
    clusterConfig.setThresholdType(3);
    clusterConfig.setWindowIntervalMs(42);

    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setClusterConfig(clusterConfig);

    ArrayList<FlowRuleEntity> flowRuleEntityList = new ArrayList<>();
    flowRuleEntityList.add(flowRuleEntity);
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(flowRuleEntityList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link FlowControllerV2#apiQueryMachineRules(String)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiQueryMachineRules(String)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.saveAll(Mockito.<List<FlowRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(dynamicRuleProvider.getRules(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/flow/rules").param("app", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doThrow(new Exception("?")).when(dynamicRulePublisher)
        .publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"java.lang.Exception, ?\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule2() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setControlBehavior(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"controlBehavior can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule3() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setGrade(10);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"grade must be 0 or 1, but 10 got\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule4() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setLimitApp(null);
    flowRuleEntity.setMaxQueueingTimeMs(3);
    flowRuleEntity.setPort(8080);
    flowRuleEntity.setRefResource("Ref Resource");
    flowRuleEntity.setResource("Resource");
    flowRuleEntity.setStrategy(1);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"limitApp can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule5() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setRefResource(null);
    flowRuleEntity.setResource("Resource");
    flowRuleEntity.setStrategy(1);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"refResource can't be null or empty when strategy!=0\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule6() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setResource(null);
    flowRuleEntity.setStrategy(1);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule7() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setStrategy(null);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"strategy can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@code -0.5}.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Count is {@code -0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given '-0.5'; when FlowRuleEntity (default constructor) Count is '-0.5'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_given05_whenFlowRuleEntityCountIs05() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setCount(-0.5d);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"count should be at lease zero\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given empty string; when FlowRuleEntity (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenEmptyString_whenFlowRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setApp("");
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) ClusterMode is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given 'false'; when FlowRuleEntity (default constructor) ClusterMode is 'false'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenFalse_whenFlowRuleEntityClusterModeIsFalse() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setClusterMode(false);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link InMemoryRuleRepositoryAdapter} {@link InMemoryRuleRepositoryAdapter#save(RuleEntity)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given InMemoryRuleRepositoryAdapter save(RuleEntity) return 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenInMemoryRuleRepositoryAdapterSaveReturnNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(null);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"java.lang.NullPointerException, Cannot invoke \\\"com.alibaba.csp"
                    + ".sentinel.dashboard.datasource.entity.rule.FlowRuleEntity.getApp()\\\" because \\\"entity\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Grade is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given zero; when FlowRuleEntity (default constructor) Grade is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenZero_whenFlowRuleEntityGradeIsZero() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setGrade(0);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Strategy is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); given zero; when FlowRuleEntity (default constructor) Strategy is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_givenZero_whenFlowRuleEntityStrategyIsZero() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setStrategy(0);
    flowRuleEntity.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"grade can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"grade can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_thenContentStringSuccessFalseCode1MsgGradeCanTBeNullDataNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setGrade(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"grade can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); when FlowRuleEntity (default constructor) App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_whenFlowRuleEntityAppIsNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setApp(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) ControlBehavior is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); when FlowRuleEntity (default constructor) ControlBehavior is ten")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_whenFlowRuleEntityControlBehaviorIsTen() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setControlBehavior(10);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) Count is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); when FlowRuleEntity (default constructor) Count is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_whenFlowRuleEntityCountIsNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setCount(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"count should be at lease zero\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) Grade is one.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); when FlowRuleEntity (default constructor) Grade is one; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_whenFlowRuleEntityGradeIsOne_thenContentStringAString() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) WarmUpPeriodSec is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiAddFlowRule(FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddFlowRule(FlowRuleEntity); when FlowRuleEntity (default constructor) WarmUpPeriodSec is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddFlowRule_whenFlowRuleEntityWarmUpPeriodSecIsNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity.setWarmUpPeriodSec(null);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/flow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"warmUpPeriodSec can't be null when controlBehavior==1\","
                + "\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doThrow(new Exception("?")).when(dynamicRulePublisher)
        .publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"java.lang.Exception, ?\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule2() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(null);
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"save entity fail\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule3() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setControlBehavior(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"controlBehavior can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule4() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setGrade(10);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"grade must be 0 or 1, but 10 got\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule5() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setGrade(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"grade can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule6() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setLimitApp(null);
    flowRuleEntity2.setMaxQueueingTimeMs(3);
    flowRuleEntity2.setPort(8080);
    flowRuleEntity2.setRefResource("Ref Resource");
    flowRuleEntity2.setResource("Resource");
    flowRuleEntity2.setStrategy(1);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"limitApp can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule7() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setRefResource(null);
    flowRuleEntity2.setResource("Resource");
    flowRuleEntity2.setStrategy(1);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"refResource can't be null or empty when strategy!=0\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule8() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setResource(null);
    flowRuleEntity2.setStrategy(1);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule9() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setStrategy(null);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"strategy can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@code -0.5}.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Count is {@code -0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given '-0.5'; when FlowRuleEntity (default constructor) Count is '-0.5'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_given05_whenFlowRuleEntityCountIs05() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setCount(-0.5d);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"count should be at lease zero\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given Bean Name{flowRuleDefaultPublisher}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given Bean Name{flowRuleDefaultPublisher}")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_givenBeanNameFlowRuleDefaultPublisher() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) ClusterMode is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given 'false'; when FlowRuleEntity (default constructor) ClusterMode is 'false'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_givenFalse_whenFlowRuleEntityClusterModeIsFalse() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setClusterMode(false);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link FlowRuleEntity} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given FlowRuleEntity (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_givenFlowRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Grade is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given zero; when FlowRuleEntity (default constructor) Grade is zero")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_givenZero_whenFlowRuleEntityGradeIsZero() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setGrade(0);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link FlowRuleEntity} (default constructor) Strategy is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); given zero; when FlowRuleEntity (default constructor) Strategy is zero")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_givenZero_whenFlowRuleEntityStrategyIsZero() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setStrategy(0);
    flowRuleEntity2.setWarmUpPeriodSec(1);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) ControlBehavior is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); when FlowRuleEntity (default constructor) ControlBehavior is ten")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_whenFlowRuleEntityControlBehaviorIsTen() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setControlBehavior(10);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) Count is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); when FlowRuleEntity (default constructor) Count is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_whenFlowRuleEntityCountIsNull() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setCount(null);
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"count should be at lease zero\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) Grade is one.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); when FlowRuleEntity (default constructor) Grade is one; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_whenFlowRuleEntityGradeIsOne_thenContentStringAString() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"limitApp"
                    + "\":null,\"resource\":null,\"grade\":null,\"count\":null,\"strategy\":null,\"refResource\":null,\"controlBehavior"
                    + "\":null,\"warmUpPeriodSec\":null,\"maxQueueingTimeMs\":null,\"clusterMode\":false,\"clusterConfig\":null,"
                    + "\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}.
   * <ul>
   *   <li>When {@link FlowRuleEntity} (default constructor) WarmUpPeriodSec is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiUpdateFlowRule(Long, FlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateFlowRule(Long, FlowRuleEntity); when FlowRuleEntity (default constructor) WarmUpPeriodSec is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateFlowRule_whenFlowRuleEntityWarmUpPeriodSecIsNull() throws Exception {
    // Arrange
    FlowRuleEntity flowRuleEntity = new FlowRuleEntity();
    flowRuleEntity.setApp("?");
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.save(Mockito.<FlowRuleEntity>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(flowRuleEntity);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());

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
    flowRuleEntity2.setWarmUpPeriodSec(null);
    String content = new ObjectMapper().writeValueAsString(flowRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/flow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"warmUpPeriodSec can't be null when controlBehavior==1\","
                + "\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiDeleteRule(Long)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"?","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiDeleteRule(Long)}
   */
  @Test
  @DisplayName("Test apiDeleteRule(Long); then content string '{\"success\":false,\"code\":-1,\"msg\":\"?\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiDeleteRule_thenContentStringSuccessFalseCode1MsgDataNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.delete(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());
    doThrow(new Exception("?")).when(dynamicRulePublisher)
        .publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v2/flow/rule/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":false,\"code\":-1,\"msg\":\"?\",\"data\":null}"));
  }

  /**
   * Test {@link FlowControllerV2#apiDeleteRule(Long)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":1}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiDeleteRule(Long)}
   */
  @Test
  @DisplayName("Test apiDeleteRule(Long); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":1}'")
  @Tag("MaintainedByDiffblue")
  void testApiDeleteRule_thenContentStringSuccessTrueCode0MsgSuccessData1() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.delete(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v2/flow/rule/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":1}"));
  }

  /**
   * Test {@link FlowControllerV2#apiDeleteRule(Long)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FlowControllerV2#apiDeleteRule(Long)}
   */
  @Test
  @DisplayName("Test apiDeleteRule(Long); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiDeleteRule_thenContentStringSuccessTrueCode0MsgSuccessDataNull() throws Exception {
    // Arrange
    when(inMemoryRuleRepositoryAdapter.delete(Mockito.<Long>any())).thenReturn(new FlowRuleEntity());
    when(inMemoryRuleRepositoryAdapter.findAllByApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    when(inMemoryRuleRepositoryAdapter.findById(Mockito.<Long>any())).thenReturn(null);
    doNothing().when(dynamicRulePublisher).publish(Mockito.<String>any(), Mockito.<List<FlowRuleEntity>>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v2/flow/rule/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(flowControllerV2)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }
}
