package com.alibaba.csp.sentinel.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.repository.rule.RuleRepository;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SystemController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class SystemControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private RuleRepository<SystemRuleEntity, Long> ruleRepository;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  @Autowired
  private SystemController systemController;

  /**
   * Test {@link SystemController#apiQueryMachineRules(String, String, Integer)}.
   * <p>
   * Method under test: {@link SystemController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
    when(ruleRepository.saveAll(Mockito.<List<SystemRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"given ip does not belong to given app\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiQueryMachineRules(String, String, Integer)}.
   * <p>
   * Method under test: {@link SystemController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules2() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<SystemRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(0));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port should be in (0, 65535)\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

    ArrayList<SystemRuleEntity> systemRuleEntityList = new ArrayList<>();
    systemRuleEntityList.add(new SystemRuleEntity());
    when(ruleRepository.saveAll(Mockito.<List<SystemRuleEntity>>any())).thenReturn(systemRuleEntityList);
    when(sentinelApiClient.fetchSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}]}"));
  }

  /**
   * Test {@link SystemController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<SystemRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/rules.json")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link SystemController#apiQueryMachineRules(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiQueryMachineRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryMachineRules(String, String, Integer); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiQueryMachineRules_whenEmptyString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<SystemRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/rules.json")
        .param("app", "")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiAdd(String, String, Integer, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiAdd() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/new.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("port", String.valueOf(1));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"given ip does not belong to given app\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"port should be in (0, 65535)","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiAdd(String, String, Integer, Double, Double, Long, Long, Double); then content string '{\"success\":false,\"code\":-1,\"msg\":\"port should be in (0, 65535)\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiAdd_thenContentStringSuccessFalseCode1MsgPortShouldBeIn065535DataNull() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/new.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("port", String.valueOf(0));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port should be in (0, 65535)\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiAdd(String, String, Integer, Double, Double, Long, Long, Double); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiAdd_whenEmptyString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/new.json").param("app", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("port", String.valueOf(1));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code app} is {@code foo}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiAdd(String, String, Integer, Double, Double, Long, Long, Double); when param(String, String[]) 'app' is 'foo'; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAdd_whenParamAppIsFoo_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/new.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("port", String.valueOf(1));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"only one of [highestSystemLoad, avgRt, maxThread, qps,highestCpuUsage]"
                    + " value must be set > 0, but 5 values get\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When valueOf minus one.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiAdd(String, String, Integer, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiAdd(String, String, Integer, Double, Double, Long, Long, Double); when valueOf minus one; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiAdd_whenValueOfMinusOne_thenContentStringAString() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/new.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(-1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("port", String.valueOf(1));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"only one of [highestSystemLoad, avgRt, maxThread, qps,highestCpuUsage]"
                    + " value must be set > 0, but 4 values get\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"highestCpuUsage must <= 1\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull2() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(null);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"id 1 dose not exist\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull3() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(-0.5d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"highestCpuUsage must >= 0\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull4() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(-0.5d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"highestSystemLoad must >= 0\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull5() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(-1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"maxThread must >= 0\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#setSystemRuleOfMachine(String, String, int, List)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); given SentinelApiClient setSystemRuleOfMachine(String, String, int, List) return 'false'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_givenSentinelApiClientSetSystemRuleOfMachineReturnFalse() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(false);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_thenContentStringAString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"avgRt must >= 0","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); then content string '{\"success\":false,\"code\":-1,\"msg\":\"avgRt must >= 0\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_thenContentStringSuccessFalseCode1MsgAvgRtMust0DataNull() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(-1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"avgRt must >= 0\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"id can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); then content string '{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_thenContentStringSuccessFalseCode1MsgIdCanTBeNullDataNull() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d))
        .param("id", "");
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"qps must >= 0","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); then content string '{\"success\":false,\"code\":-1,\"msg\":\"qps must >= 0\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_thenContentStringSuccessFalseCode1MsgQpsMust0DataNull() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(-0.5d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"qps must >= 0\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code app} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'app' is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamAppIsEmptyString() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult6 = paramResult5.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult6.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"highestCpuUsage must <= 1\",\"data\":null}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code avgRt} is empty string.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'avgRt' is empty string; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamAvgRtIsEmptyString_thenContentStringAString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json")
        .param("app", "foo")
        .param("avgRt", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code highestCpuUsage} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'highestCpuUsage' is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamHighestCpuUsageIsEmptyString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L))
        .param("highestCpuUsage", "");
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code highestSystemLoad} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'highestSystemLoad' is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamHighestSystemLoadIsEmptyString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d))
        .param("highestSystemLoad", "");
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("maxThread", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code maxThread} is empty string.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'maxThread' is empty string; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamMaxThreadIsEmptyString_thenContentStringAString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L)).param("maxThread", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("qps", String.valueOf(10.0d));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code qps} is empty string.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double)}
   */
  @Test
  @DisplayName("Test apiUpdateIfNotNull(Long, String, Double, Double, Long, Long, Double); when param(String, String[]) 'qps' is empty string; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateIfNotNull_whenParamQpsIsEmptyString_thenContentStringAString() throws Exception {
    // Arrange
    SystemRuleEntity systemRuleEntity = new SystemRuleEntity();
    systemRuleEntity.setPort(8080);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<SystemRuleEntity>any())).thenReturn(systemRuleEntity);
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new SystemRuleEntity());
    when(sentinelApiClient.setSystemRuleOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<SystemRuleEntity>>any())).thenReturn(true);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/system/save.json").param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("avgRt", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("highestCpuUsage", String.valueOf(1.0d));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("highestSystemLoad", String.valueOf(10.0d));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("id", String.valueOf(1L));
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("maxThread", String.valueOf(1L)).param("qps", "");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(systemController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,"
                    + "\"highestSystemLoad\":null,\"avgRt\":null,\"maxThread\":null,\"qps\":null,\"highestCpuUsage\":null,\"gmtCreate\""
                    + ":null,\"gmtModified\":null}}"));
  }

  /**
   * Test {@link SystemController#delete(Long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code id can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemController#delete(Long)}
   */
  @Test
  @DisplayName("Test delete(Long); when 'null'; then return Msg is 'id can't be null'")
  @Tag("MaintainedByDiffblue")
  void testDelete_whenNull_thenReturnMsgIsIdCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<?> actualDeleteResult = new SystemController().delete(null);

    // Assert
    assertEquals("id can't be null", actualDeleteResult.getMsg());
    assertNull(actualDeleteResult.getData());
    assertEquals(-1, actualDeleteResult.getCode());
    assertFalse(actualDeleteResult.isSuccess());
  }
}
