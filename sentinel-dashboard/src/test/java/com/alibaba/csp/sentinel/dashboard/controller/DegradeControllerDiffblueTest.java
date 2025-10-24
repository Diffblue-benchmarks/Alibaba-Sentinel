package com.alibaba.csp.sentinel.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.repository.rule.RuleRepository;
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

@ContextConfiguration(classes = {DegradeController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class DegradeControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private DegradeController degradeController;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private RuleRepository<DegradeRuleEntity, Long> ruleRepository;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link DegradeController#apiQueryMachineRules(String, String, Integer)}.
   * <p>
   * Method under test: {@link DegradeController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
    when(ruleRepository.saveAll(Mockito.<List<DegradeRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/degrade/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"given ip does not belong to given app\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    ArrayList<DegradeRuleEntity> degradeRuleEntityList = new ArrayList<>();
    degradeRuleEntityList.add(new DegradeRuleEntity());
    when(ruleRepository.saveAll(Mockito.<List<DegradeRuleEntity>>any())).thenReturn(degradeRuleEntityList);
    when(sentinelApiClient.fetchDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/degrade/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"resource"
                    + "\":null,\"limitApp\":null,\"count\":null,\"timeWindow\":null,\"grade\":null,\"minRequestAmount\":null,"
                    + "\"slowRatioThreshold\":null,\"statIntervalMs\":null,\"gmtCreate\":null,\"gmtModified\":null}]}"));
  }

  /**
   * Test {@link DegradeController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<DegradeRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/degrade/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link DegradeController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_whenEmptyString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<DegradeRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/degrade/rules.json")
        .param("app", "")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("App");
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Ratio threshold should be in range: [0.0, 1.0]\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule2() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("App");
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"given ip does not belong to given app\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule3() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(3);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid circuit breaker strategy: 3\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule4() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(0);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"SlowRatioThreshold should be in range: [0.0, 1.0]\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule5() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(null);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"circuit breaker strategy cannot be null\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule6() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(-1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid circuit breaker strategy: -1\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule7() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"limitApp can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule8() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#setDegradeRuleOfMachine(String, String, int, List)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); given SentinelApiClient setDegradeRuleOfMachine(String, String, int, List) return 'false'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_givenSentinelApiClientSetDegradeRuleOfMachineReturnFalse() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(false);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,\"resource"
                    + "\":null,\"limitApp\":null,\"count\":null,\"timeWindow\":null,\"grade\":null,\"minRequestAmount\":null,"
                    + "\"slowRatioThreshold\":null,\"statIntervalMs\":null,\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Given two.</li>
   *   <li>When {@link DegradeRuleEntity} (default constructor) Grade is two.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); given two; when DegradeRuleEntity (default constructor) Grade is two; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_givenTwo_whenDegradeRuleEntityGradeIsTwo_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(2);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,\"resource"
                    + "\":null,\"limitApp\":null,\"count\":null,\"timeWindow\":null,\"grade\":null,\"minRequestAmount\":null,"
                    + "\"slowRatioThreshold\":null,\"statIntervalMs\":null,\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid port: 0","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid port: 0\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_thenContentStringSuccessFalseCode1MsgInvalidPort0DataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(0);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid port: 0\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid port: null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid port: null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_thenContentStringSuccessFalseCode1MsgInvalidPortNullDataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(null);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid port: null\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid threshold: -0.5","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid threshold: -0.5\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_thenContentStringSuccessFalseCode1MsgInvalidThreshold05DataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(-0.5d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid threshold: -0.5\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid threshold: null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid threshold: null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_thenContentStringSuccessFalseCode1MsgInvalidThresholdNullDataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(null);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid threshold: null\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"ip can't be null or empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_thenContentStringSuccessFalseCode1MsgIpCanTBeNullOrEmptyDataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("");
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be blank\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityAppIsNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp(null);
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be blank\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) Count is one.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) Count is one; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityCountIsOne_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,\"resource"
                    + "\":null,\"limitApp\":null,\"count\":null,\"timeWindow\":null,\"grade\":null,\"minRequestAmount\":null,"
                    + "\"slowRatioThreshold\":null,\"statIntervalMs\":null,\"gmtCreate\":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) MinRequestAmount is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) MinRequestAmount is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityMinRequestAmountIsNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(null);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid minRequestAmount\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) MinRequestAmount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) MinRequestAmount is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityMinRequestAmountIsZero() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(0);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid minRequestAmount\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) StatIntervalMs is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) StatIntervalMs is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityStatIntervalMsIsNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(null);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid statInterval\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) StatIntervalMs is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) StatIntervalMs is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityStatIntervalMsIsZero() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(0);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid statInterval\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) TimeWindow is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) TimeWindow is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityTimeWindowIsNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(null);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"recoveryTimeout should be positive\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiAddRule(DegradeRuleEntity)}.
   * <ul>
   *   <li>When {@link DegradeRuleEntity} (default constructor) TimeWindow is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiAddRule(DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddRule(DegradeRuleEntity); when DegradeRuleEntity (default constructor) TimeWindow is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddRule_whenDegradeRuleEntityTimeWindowIsZero() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<DegradeRuleEntity>any())).thenReturn(degradeRuleEntity);
    when(sentinelApiClient.setDegradeRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<DegradeRuleEntity>>any())).thenReturn(true);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(1.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(0);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/degrade/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"recoveryTimeout should be positive\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateRule(Long, DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateRule() throws Exception {
    // Arrange
    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("?");
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(degradeRuleEntity);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(10.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/degrade/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}.
   * <p>
   * Method under test: {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateRule(Long, DegradeRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateRule2() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(null);

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("App");
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/degrade/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Degrade rule does not exist, id=1\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}.
   * <ul>
   *   <li>Given {@link DegradeRuleEntity} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateRule(Long, DegradeRuleEntity); given DegradeRuleEntity (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateRule_givenDegradeRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("");
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(degradeRuleEntity);

    DegradeRuleEntity degradeRuleEntity2 = new DegradeRuleEntity();
    degradeRuleEntity2.setApp("App");
    degradeRuleEntity2.setCount(10.0d);
    degradeRuleEntity2
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity2.setGrade(1);
    degradeRuleEntity2.setId(1L);
    degradeRuleEntity2.setIp("127.0.0.1");
    degradeRuleEntity2.setLimitApp("Limit App");
    degradeRuleEntity2.setMinRequestAmount(1);
    degradeRuleEntity2.setPort(8080);
    degradeRuleEntity2.setResource("Resource");
    degradeRuleEntity2.setSlowRatioThreshold(10.0d);
    degradeRuleEntity2.setStatIntervalMs(42);
    degradeRuleEntity2.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/degrade/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be blank\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"app can't be blank","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#apiUpdateRule(Long, DegradeRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateRule(Long, DegradeRuleEntity); then content string '{\"success\":false,\"code\":-1,\"msg\":\"app can't be blank\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateRule_thenContentStringSuccessFalseCode1MsgAppCanTBeBlankDataNull() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new DegradeRuleEntity());

    DegradeRuleEntity degradeRuleEntity = new DegradeRuleEntity();
    degradeRuleEntity.setApp("App");
    degradeRuleEntity.setCount(10.0d);
    degradeRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    degradeRuleEntity.setGrade(1);
    degradeRuleEntity.setId(1L);
    degradeRuleEntity.setIp("127.0.0.1");
    degradeRuleEntity.setLimitApp("Limit App");
    degradeRuleEntity.setMinRequestAmount(1);
    degradeRuleEntity.setPort(8080);
    degradeRuleEntity.setResource("Resource");
    degradeRuleEntity.setSlowRatioThreshold(10.0d);
    degradeRuleEntity.setStatIntervalMs(42);
    degradeRuleEntity.setTimeWindow(1);
    String content = new ObjectMapper().writeValueAsString(degradeRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/degrade/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(degradeController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be blank\",\"data\":null}"));
  }

  /**
   * Test {@link DegradeController#delete(Long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code id can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DegradeController#delete(Long)}
   */
  @Test
  @DisplayName("Test delete(Long); when 'null'; then return Msg is 'id can't be null'")
  @Tag("MaintainedByDiffblue")
  void testDelete_whenNull_thenReturnMsgIsIdCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<Long> actualDeleteResult = new DegradeController().delete(null);

    // Assert
    assertEquals("id can't be null", actualDeleteResult.getMsg());
    assertNull(actualDeleteResult.getData());
    assertEquals(-1, actualDeleteResult.getCode());
    assertFalse(actualDeleteResult.isSuccess());
  }
}
